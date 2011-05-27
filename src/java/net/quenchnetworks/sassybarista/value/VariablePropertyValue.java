package net.quenchnetworks.sassybarista.value;

import java.util.*;

import net.quenchnetworks.sassybarista.*;

public class VariablePropertyValue implements IPropertyValue
{
	private String variable;

	public VariablePropertyValue(String variable)
	{
		this.variable = variable;
	}
	
	@Override
	public String serialize(Map<String, IPropertyValue> variables)
	throws SerializationException
	{
		if (!variables.containsKey(variable)) {
			throw new SerializationException("Variable " + variable + " was not found.");
		}
	
		IPropertyValue value = variables.get(variable);
		return value.serialize(variables);
	}
	
	@Override
	public String toString()
	{
		return variable;
	}
}
