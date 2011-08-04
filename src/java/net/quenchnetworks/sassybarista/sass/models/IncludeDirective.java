package net.quenchnetworks.sassybarista.sass.models;

import java.io.Serializable;
import java.util.*;

import net.quenchnetworks.sassybarista.sass.value.*;

public class IncludeDirective implements Serializable
{
    private String mixinName;
    private List<IPropertyValue> parameters;

    public IncludeDirective()
    {
        this.mixinName = null;
        this.parameters = new ArrayList<IPropertyValue>();
    }
    
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
