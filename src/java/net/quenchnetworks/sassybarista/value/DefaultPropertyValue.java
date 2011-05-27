package net.quenchnetworks.sassybarista.value;

public class DefaultPropertyValue implements IPropertyValue
{
	private String value;

	public DefaultPropertyValue(String value)
	{
		this.value = value;
	}
	
	@Override
	public String toString()
	{
		return value;
	}
}
