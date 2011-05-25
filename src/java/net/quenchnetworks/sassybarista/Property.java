package net.quenchnetworks.sassybarista;

import java.util.*;

public class Property
{
	private String key;
	private List<String> values;

	public Property(String key)
	{
		this.key = key;
		this.values = new ArrayList<String>();
	}
	
	public void addValue(String value)
	{
		values.add(value);
	}
	
	public String getKey()
	{
		return key;
	}
	
	public List<String> getValues()
	{
		return values;
	}
}
