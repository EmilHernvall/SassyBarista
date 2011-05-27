package net.quenchnetworks.sassybarista.value;

import java.util.*;

public class URIPropertyValue implements IPropertyValue
{
	private String value;

	public URIPropertyValue(String value)
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
