package net.quenchnetworks.sassybarista.value;

public class URIPropertyValue implements IPropertyValue
{
	private String value;

	public URIPropertyValue(String value)
	{
		this.value = value;
	}
	
	@Override
	public String toString()
	{
		return value;
	}
}
