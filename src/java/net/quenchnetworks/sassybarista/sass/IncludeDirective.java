package net.quenchnetworks.sassybarista.sass;

import java.util.*;

import net.quenchnetworks.sassybarista.sass.value.*;

public class IncludeDirective
{
	private String mixinName;
	private List<IPropertyValue> parameters;

	public IncludeDirective(String mixinName)
	{
		this.mixinName = mixinName;
		this.parameters = new ArrayList<IPropertyValue>();
	}
	
	public String getMixinName()
	{
		return mixinName;
	}
	
	public void addParameter(IPropertyValue param)
	{
		parameters.add(param);
	}
	
	public List<IPropertyValue> getParameters()
	{
		return parameters;
	}
}
