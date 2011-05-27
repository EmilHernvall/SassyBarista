package net.quenchnetworks.sassybarista;

import java.util.*;

import net.quenchnetworks.sassybarista.value.*;

public class Property
{
	private String key;
	private List<IPropertyValue> values;

	public Property(String key)
	{
		this.key = key;
		this.values = new ArrayList<IPropertyValue>();
	}
	
	public void addValue(IPropertyValue value)
	{
		values.add(value);
	}
	
	public String getKey()
	{
		return key;
	}
	
	public List<IPropertyValue> getValues()
	{
		return values;
	}
}
