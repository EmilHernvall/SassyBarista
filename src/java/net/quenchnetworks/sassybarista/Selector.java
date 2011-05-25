package net.quenchnetworks.sassybarista;

import java.util.*;

public class Selector
{
	private LinkedList<String> rules;

	public Selector()
	{
		this.rules = new LinkedList<String>();
	}
	
	public void prependSelectorRule(String selectorRule)
	{
		rules.addFirst(selectorRule);
	}
	
	public void addSelectorRule(String selectorRule)
	{
		rules.add(selectorRule);
	}
	
	public List<String> getSelectorRules()
	{
		return rules;
	}
	
	public Iterator<String> getReverseIterator()
	{
		return rules.descendingIterator();
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
