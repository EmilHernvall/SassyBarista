package net.quenchnetworks.sassybarista.value;

import java.util.*;

public class DimensionPropertyValue implements IPropertyValue
{
	private String value;

	public DimensionPropertyValue(String value)
	{
		this.value = value;
	}
	
	@Override
	public String serialize(Map<String, IPropertyValue> variables)
	{
		return toString();
	}
	
	@Override
	public String toString()
	{
		return value;
	}
}
