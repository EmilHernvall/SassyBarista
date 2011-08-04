package net.quenchnetworks.sassybarista.sass.models;

import java.io.Serializable;
import java.util.*;

public class Block implements Serializable
{
    private List<Property> properties;
    private List<Rule> subrules;

    public Block()
    {
        this.properties = new ArrayList<Property>();
        this.subrules = new ArrayList<Rule>();
    }
    
    public void addProperty(Property property)
    {
        properties.add(property);
    }
    
    public  void addProperties(List<Property> newProperties)
    {
        properties.addAll(newProperties);
    }
    
    public List<Property> getProperties()
    {
        return properties;
    }
    
    public void addSubRule(Rule rule)
    {
        subrules.add(rule);
    }
    
    public void addSubRules(List<Rule> newRules)
    {
        subrules.addAll(newRules);
    }
    
    public List<Rule> getSubRules()
    {
        return subrules;
    }
    
    @Override
    public int hashCode()
    {
        int code = 17;
        for (Property property : properties) {
            code = 31 * code + property.hashCode();
        }
        for (Rule rule : subrules) {
            code = 31 * code + rule.hashCode();
        }
        
        return code;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof Block)) {
            return false;
        }
        
        return hashCode() == obj.hashCode();
    }
}
