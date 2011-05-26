package net.quenchnetworks.sassybarista;

import java.util.*;

public class Rule
{
	private List<SelectorChain> selectorChains;
	private List<Property> properties;
	private List<Rule> subrules;

	public Rule()
	{
		this.selectorChains = new ArrayList<SelectorChain>();
		this.properties = new ArrayList<Property>();
		this.subrules = new ArrayList<Rule>();
	}
	
	public void addSelectorChain(SelectorChain selectorChain)
	{
		selectorChains.add(selectorChain);
	}
	
	public void addProperty(Property property)
	{
		properties.add(property);
	}
	
	public void addSubRule(Rule rule)
	{
		subrules.add(rule);
	}
	
	public List<SelectorChain> getSelectorChains()
	{
		return selectorChains;
	}
	
	public List<Property> getProperties()
	{
		return properties;
	}
	
	public List<Rule> getSubRules()
	{
		return subrules;
	}
}
