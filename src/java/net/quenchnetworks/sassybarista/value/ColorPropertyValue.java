package net.quenchnetworks.sassybarista.value;

public class ColorPropertyValue implements IPropertyValue
{
	private String value;

	public ColorPropertyValue(String value)
	{
		this.value = value;
	}
	
	@Override
	public String toString()
	{
		return value;
	}
}
