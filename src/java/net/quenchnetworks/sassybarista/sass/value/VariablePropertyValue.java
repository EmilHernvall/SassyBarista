package net.quenchnetworks.sassybarista.sass.value;

import java.util.*;
import java.io.Serializable;

import net.quenchnetworks.sassybarista.sass.*;

public class VariablePropertyValue implements IPropertyValue, Serializable
{
    private String variable;
    
    public VariablePropertyValue()
    {
        this.variable = null;
    }

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
