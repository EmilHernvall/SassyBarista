package net.quenchnetworks.sassybarista.value;

public class VariablePropertyValue implements IPropertyValue
{
	private String value;

	public VariablePropertyValue(String value)
	{
		this.value = value;
	}
	
	@Override
	public String toString()
	{
		return value;
	}
}
