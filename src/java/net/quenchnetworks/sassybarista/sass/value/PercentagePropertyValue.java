package net.quenchnetworks.sassybarista.sass.value;

import java.util.*;

public class PercentagePropertyValue implements IPropertyValue
{
	private String value;

	public PercentagePropertyValue(String value)
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
