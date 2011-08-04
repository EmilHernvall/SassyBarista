package net.quenchnetworks.sassybarista.sass.value;

import java.util.*;
import java.io.Serializable;

public class DefaultPropertyValue implements IPropertyValue, Serializable
{
    private String value;

    public DefaultPropertyValue()
    {
        this.value = null;
    }
    
    public DefaultPropertyValue(String value)
    {
        this.value = value;
    }
    
    @Override
    public String serialize(Map<String, IPropertyValue> variables)
    {
        return toString();
    }
    
    @Override
    public String toString()
    {
        return value;
    }
}
