package net.quenchnetworks.sassybarista;

import java.util.*;

public class Rule
{
	private List<Selector> selectors;
	private List<Property> properties;

	public Rule()
	{
		this.selectors = new ArrayList<Selector>();
		this.properties = new ArrayList<Property>();
	}
	
	public void addSelector(Selector selector)
	{
		selectors.add(selector);
	}
	
	public void addProperty(Property property)
	{
		properties.add(property);
	}
	
	public List<Selector> getSelectors()
	{
		return selectors;
	}
	
	public List<Property> getProperties()
	{
		return properties;
	}
}
