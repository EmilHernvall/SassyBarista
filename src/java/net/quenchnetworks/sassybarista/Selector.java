package net.quenchnetworks.sassybarista;

import java.util.*;

public class Selector
{
	private List<String> rules;

	public Selector()
	{
		this.rules = new ArrayList<String>();
	}
	
	public void addSelectorRule(String selectorRule)
	{
		rules.add(selectorRule);
	}
	
	public List<String> getSelectorRules()
	{
		return rules;
	}
	
	@Override
	public String toString()
	{
		StringBuilder buffer = new StringBuilder();
		for (String rule : rules) {
			buffer.append(rule);
			buffer.append(" ");
		}
		
		return buffer.toString();
	}
}
