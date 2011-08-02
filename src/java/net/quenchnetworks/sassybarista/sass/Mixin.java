package net.quenchnetworks.sassybarista.sass;

import java.util.*;

import net.quenchnetworks.sassybarista.sass.value.*;

public class Mixin extends Block
{
    private String name;
    private List<String> parameters;

    public Mixin()
    {
        super();
        
        parameters = new ArrayList<String>();
    }
    
    public Map<String, IPropertyValue> getParameterMap(IncludeDirective include)
    throws SerializationException
    {
        List<IPropertyValue> values = include.getParameters();
        if (parameters.size() != values.size()) {
            throw new SerializationException("Mixin " + name + " requires " + parameters.size() + 
                " parameters, but was passed " + values.size() + " arguments.");
        }
        
        Map<String, IPropertyValue> map = new HashMap<String, IPropertyValue>();
        for (int i = 0; i < values.size(); i++) {
            String parameter = parameters.get(i);
            IPropertyValue value = values.get(i);
            map.put(parameter, value);
        }
        
        return map;
    }
    
    public void addParameter(String parameter)
    {
        parameters.add(parameter);
    }
    
    public String getName() { return name; }
    public void setName(String v) { this.name = v; }
}
