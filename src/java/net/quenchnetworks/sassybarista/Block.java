package net.quenchnetworks.sassybarista;

import java.util.*;

public class Block
{
	private List<Property> properties;
	private List<Rule> subrules;
	private List<IncludeDirective> includes;

	public Block()
	{
		this.properties = new ArrayList<Property>();
		this.subrules = new ArrayList<Rule>();
		this.includes = new ArrayList<IncludeDirective>();
	}
	
	public void addProperty(Property property)
	{
		properties.add(property);
	}
	
	public  void addProperties(List<Property> newProperties)
	{
		properties.addAll(newProperties);
	}
	
	public void addSubRule(Rule rule)
	{
		subrules.add(rule);
	}
	
	public void addSubRules(List<Rule> newRules)
	{
		subrules.addAll(newRules);
	}
	
	public void addInclude(IncludeDirective include)
	{
		includes.add(include);
	}
	
	public List<Property> getProperties()
	{
		return properties;
	}
	
	public List<Rule> getSubRules()
	{
		return subrules;
	}
	
	public List<IncludeDirective> getIncludes()
	{
		return includes;
	}
	
	@Override
	public int hashCode()
	{
		int code = 17;
		for (Property property : properties) {
			code = 31 * code + property.hashCode();
		}
		for (Rule rule : subrules) {
			code = 31 * code + rule.hashCode();
		}
		for (IncludeDirective inc : includes) {
			code = 31 * code + inc.hashCode();
		}
		
		return code;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (!(obj instanceof Block)) {
			return false;
		}
		
		Block block = (Block)obj;
		
		return true;
	}
}
