package net.quenchnetworks.sassybarista;

import java.util.*;

public class Rule
{
	private List<Selector> selectors;
	private List<Property> properties;
	private List<Rule> subrules;

	public Rule()
	{
		this.selectors = new ArrayList<Selector>();
		this.properties = new ArrayList<Property>();
		this.subrules = new ArrayList<Rule>();
	}
	
	public void addSelector(Selector selector)
	{
		selectors.add(selector);
	}
	
	public void addProperty(Property property)
	{
		properties.add(property);
	}
	
	public void addSubRule(Rule rule)
	{
		subrules.add(rule);
	}
	
	public List<Selector> getSelectors()
	{
		return selectors;
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
