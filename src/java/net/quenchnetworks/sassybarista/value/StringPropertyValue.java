package net.quenchnetworks.sassybarista.value;

public class StringPropertyValue implements IPropertyValue
{
	private String value;

	public StringPropertyValue(String value)
	{
		this.value = value;
	}
	
	@Override
	public String toString()
	{
		return value;
	}
}
