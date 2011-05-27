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
		
		for (Rule rule : result.rules) {
			printRule(rule, "");
		}
	}
	
	public static void printRule(Rule rule, String path)
	{
		int i = 0;
		List<SelectorChain> selectorChains = rule.getSelectorChains();
		for (SelectorChain selectorChain : selectorChains) {
			i++;
			System.out.print(path + selectorChain.toString());
			if (i == selectorChains.size()) {
				System.out.println(" {");
			} else {
				System.out.println(",");
			}
		}
		
		for (Property property : rule.getProperties()) {
			System.out.print("\t" + property.getKey() + ": ");
			List<IPropertyValue> values = property.getValues();
			i = 0;
			for (IPropertyValue value : values) {
				System.out.print(value);
				if (i < values.size() - 1) {
					System.out.print(" ");
				}
				i++;
			}
			System.out.println(";");				
		}
		
		System.out.println("}");
		System.out.println();
		
		for (SelectorChain selectorChain : selectorChains) {
			for (Rule subrule : rule.getSubRules()) {
				printRule(subrule, path + selectorChain + " ");
			}
		}
	}
}
