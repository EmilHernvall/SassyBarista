package net.quenchnetworks.sassybarista;

import java.io.*;
import java.util.*;

import net.quenchnetworks.sassybarista.value.*;

public class Render
{
	public static void main(String[] args)
	throws Exception
	{
		FileReader reader = new FileReader(args[0]);
		SassParser parser = new SassParser(reader);
		SassParser.ParseResult result = parser.Start();
		
		for (Map.Entry<String, IPropertyValue> entry : result.variables.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
		
		for (Mixin mixin : result.mixins.values()) {
			printMixin(mixin);
		}
		
		for (Rule rule : result.rules) {
			printRule(rule, result.mixins, false);
		}
	}
	
	public static void printMixin(Mixin mixin)
	{
		System.out.println("@mixin " + mixin.getName() + " {");
		
		printProperties(mixin.getProperties(), false);
		
		for (Rule rule : mixin.getSubRules()) {
			printRule(rule, new HashMap<String, Mixin>(), true);
		}
		
		System.out.println("}");
	}
	
	public static void printRule(Rule rule, Map<String, Mixin> mixins, boolean indent)
	{
		for (String include : rule.getIncludes()) {
			Mixin mixin = mixins.get(include);
			rule.addProperties(mixin.getProperties());
			rule.addSubRules(mixin.getSubRules());
		}
	
		int i = 0;
		List<SelectorChain> selectorChains = rule.getSelectorChains();
		for (SelectorChain selectorChain : selectorChains) {
			i++;
			if (indent) { 
				System.out.print("\t");
			}
			System.out.print(selectorChain.toString());
			if (i == selectorChains.size()) {
				System.out.println(" {");
			} else {
				System.out.println(",");
			}
		}
		
		printProperties(rule.getProperties(), indent);

		if (indent) { 
			System.out.print("\t");
		}
		System.out.println("}");
		System.out.println();
		
		for (Rule subrule : rule.getSubRules()) {
			List<SelectorChain> newChains = new ArrayList<SelectorChain>();
			for (SelectorChain selectorChain : selectorChains) {
				for (SelectorChain subSelectorChain : subrule.getSelectorChains()) {
					SelectorChain newChain = new SelectorChain();
					newChain.addSelectors(selectorChain.getSelectors());
					newChain.addSelectors(subSelectorChain.getSelectors());
					newChains.add(newChain);
				}
			}
			
			subrule.setSelectorChains(newChains);
			printRule(subrule, mixins, indent);
		}
	}
	
	public static void printProperties(List<Property> properties, boolean indent) 
	{
		for (Property property : properties) {
			if (indent) { 
				System.out.print("\t");
			}
			System.out.print("\t" + property.getKey() + ": ");
			List<IPropertyValue> values = property.getValues();
			int i = 0;
			for (IPropertyValue value : values) {
				System.out.print(value);
				if (i < values.size() - 1) {
					System.out.print(" ");
				}
				i++;
			}
			System.out.println(";");				
		}
	}
}
