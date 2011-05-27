package net.quenchnetworks.sassybarista.value;

public class DimensionPropertyValue implements IPropertyValue
{
	private String value;

	public DimensionPropertyValue(String value)
	{
		this.value = value;
	}
	
	@Override
	public String toString()
	{
		return value;
	}
}
