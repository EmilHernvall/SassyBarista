package net.quenchnetworks.sassybarista.sass.models;

import java.io.Serializable;
import java.util.*;

import net.quenchnetworks.sassybarista.sass.value.*;

public class SassSheet implements Serializable
{
    private Map<String, IPropertyValue> variables;
    private Map<String, Mixin> mixins;
    private List<Rule> rules;
    
    public SassSheet(Map<String, IPropertyValue> variables, 
        Map<String, Mixin> mixins, List<Rule> rules)
    {
        this.variables = variables;
        this.mixins = mixins;
        this.rules = rules;
    }
    
    public SassSheet()
    {
        this.variables = new HashMap<String, IPropertyValue>();
        this.mixins = new HashMap<String, Mixin>();
        this.rules = new ArrayList<Rule>();
    }
    
    public Map<String, IPropertyValue> getVariables() { return variables; }
    public void setVariables(Map<String, IPropertyValue> v) { this.variables = v; }
    
    public Map<String, Mixin> getMixins() { return mixins; }
    public void setMixins(Map<String, Mixin> v) { this.mixins = v; }
    
    public List<Rule> getRules() { return rules; }
    public void setRules(List<Rule> v) { this.rules = v; }
}
