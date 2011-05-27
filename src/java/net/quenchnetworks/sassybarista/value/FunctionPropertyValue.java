package net.quenchnetworks.sassybarista.value;

import java.util.*;

public class FunctionPropertyValue implements IPropertyValue
{
	private String name;
	private List<IPropertyValue> values;

	public FunctionPropertyValue(String name)
	{
		this.name = name;
		this.values = new ArrayList<IPropertyValue>();
	}
	
	public void addValue(IPropertyValue value)
	{
		values.add(value);
	}
	
	@Override
	public String toString()
	{
		StringBuilder buffer = new StringBuilder();
		
		buffer.append(name);
		buffer.append("(");
		
		int i = 0;
		for (IPropertyValue value : values) {
			buffer.append(value.toString());
			if (i < values.size() - 1) {
				buffer.append(" ");
			}
			i++;
		}
		
		buffer.append(")");
		
		return buffer.toString();
	}
}
