package net.quenchnetworks.sassybarista;

import java.io.*;
import java.util.*;

import net.quenchnetworks.sassybarista.value.*;

public class CssSerializer
{
	private PrintStream writer;
	private Map<String, IPropertyValue> variables = null;
	private Map<String, Mixin> mixins = null;

	public CssSerializer(PrintStream writer)
	{
		this.writer = writer;
	}
	
	public void render(Reader reader)
	throws ParseException, SerializationException
	{
		SassParser parser = new SassParser(reader);
		SassParser.ParseResult result = parser.Start();
		
		this.variables = result.variables;
		this.mixins = result.mixins;
		
		for (Rule rule : result.rules) {
			renderRule(rule, false);
		}
	}
	
	private void renderRule(Rule rule, boolean indent)
	throws SerializationException
	{
		// Fetch all includes and just copy all the subrules
		// of each mixin to this one.
		for (IncludeDirective include : rule.getIncludes()) {
			Mixin mixin = mixins.get(include.getMixinName());
			if (mixin == null) {
				throw new SerializationException("Mixin " + include.getMixinName() + " was not found.");
			}
			rule.addSubRules(mixin.getSubRules());
		}
	
		// Render all selectors
		int i = 0;
		List<SelectorChain> selectorChains = rule.getSelectorChains();
		for (SelectorChain selectorChain : selectorChains) {
			i++;
			if (indent) { 
				writer.print("\t");
			}
			writer.print(selectorChain.toString());
			if (i == selectorChains.size()) {
				writer.println(" {");
			} else {
				writer.println(",");
			}
		}
		
		// Render all properties
		renderProperties(rule.getProperties(), variables, indent);
		for (IncludeDirective include : rule.getIncludes()) {
			Mixin mixin = mixins.get(include.getMixinName());
			renderProperties(mixin.getProperties(), 
				mixin.getParameterMap(include), 
				indent);
		}

		if (indent) { 
			writer.print("\t");
		}
		writer.println("}");
		writer.println();
		
		// Render subrules
		for (Rule subrule : rule.getSubRules()) {
			// Permute all combinations of this level and the next levels selector
			List<SelectorChain> newChains = new ArrayList<SelectorChain>();
			for (SelectorChain selectorChain : selectorChains) {
				for (SelectorChain subSelectorChain : subrule.getSelectorChains()) {
					SelectorChain newChain = new SelectorChain();
					newChain.addSelectors(selectorChain.getSelectors());
					newChain.addSelectors(subSelectorChain.getSelectors());
					newChains.add(newChain);
				}
			}
			
			// Replace all the current selectors of the sub rule with the new
			// permutations. This will be done again for each level, which will
			// generate all the needed permutations regardless of the number of
			// lvels
			subrule.setSelectorChains(newChains);
			
			renderRule(subrule, indent);
		}
	}
	
	private void renderProperties(List<Property> properties, Map<String, IPropertyValue> scope, boolean indent) 
	throws SerializationException
	{
		for (Property property : properties) {
			if (indent) { 
				writer.print("\t");
			}
			writer.print("\t" + property.getKey() + ": ");
			List<IPropertyValue> values = property.getValues();
			int i = 0;
			for (IPropertyValue value : values) {
				writer.print(value.serialize(scope));
				if (i < values.size() - 1) {
					writer.print(" ");
				}
				i++;
			}
			writer.println(";");				
		}
	}
	
	public static void main(String[] args)
	throws Exception
	{
		CssSerializer serializer = new CssSerializer(System.out);
		
		serializer.render(new FileReader(args[0]));
	}
}
