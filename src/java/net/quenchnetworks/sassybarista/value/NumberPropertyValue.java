package net.quenchnetworks.sassybarista.value;

public class NumberPropertyValue implements IPropertyValue
{
	private String value;

	public NumberPropertyValue(String value)
	{
		this.value = value;
	}
	
	@Override
	public String toString()
	{
		return value;
	}
}
