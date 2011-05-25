package net.quenchnetworks.sassybarista;

import java.io.*;
import java.util.*;

public class Render
{
	public static void main(String[] args)
	throws Exception
	{
		FileReader reader = new FileReader(args[0]);
		SassParser parser = new SassParser(reader);
		List<Rule> rules = parser.Start();
		
		for (Rule rule : rules) {
			printRule(rule, "");
		}
	}
	
	public static void printRule(Rule rule, String path)
	{
		int i = 0;
		List<Selector> selectors = rule.getSelectors();
		for (Selector selector : selectors) {
			i++;
			System.out.print(path + selector.toString());
			if (i == selectors.size()) {
				System.out.println("{");
			} else {
				System.out.println(",");
			}
		}
		
		for (Property property : rule.getProperties()) {
			System.out.print("\t" + property.getKey() + ": ");
			for (String value : property.getValues()) {
				System.out.print(value + " ");
			}
			System.out.println(";");				
		}
		
		System.out.println("}");
		System.out.println();
		
		for (Selector selector : selectors) {
			for (Rule subrule : rule.getSubRules()) {
				printRule(subrule, path + selector + " ");
			}
		}
	}
}
