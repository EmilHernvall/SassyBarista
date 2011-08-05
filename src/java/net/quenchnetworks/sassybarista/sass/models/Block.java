package net.quenchnetworks.sassybarista.sass.models;

import java.io.Serializable;
import java.util.*;

public class Block implements Serializable
{
    private List<Property> properties;
    private List<Rule> subrules;
    private List<ControlStatement> ctrlStmts;

    public Block()
    {
        this.properties = new ArrayList<Property>();
        this.subrules = new ArrayList<Rule>();
        this.ctrlStmts = new ArrayList<ControlStatement>();
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
    
    public void setSubRules(List<Rule> v)
    {
        this.subrules = v;
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
    
    public void addControlStatement(ControlStatement stmt)
    {
        ctrlStmts.add(stmt);
    }
    
    public List<ControlStatement> getControlStatements()
    {
        return ctrlStmts;
    }
    
    protected void copy(Block copyTo)
    {
        for (Property property : properties) {
            copyTo.addProperty(property.copy());
        }
        
        for (Rule rule : subrules) {
            copyTo.addSubRule(rule.copy());
        }
        
        for (ControlStatement stmt : ctrlStmts) {
            copyTo.addControlStatement(stmt.copy());
        }
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
