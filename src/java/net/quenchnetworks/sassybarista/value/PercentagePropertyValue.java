package net.quenchnetworks.sassybarista.value;

public class PercentagePropertyValue implements IPropertyValue
{
	private String value;

	public PercentagePropertyValue(String value)
	{
		this.value = value;
	}
	
	@Override
	public String toString()
	{
		return value;
	}
}
