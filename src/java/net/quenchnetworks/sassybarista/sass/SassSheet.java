package net.quenchnetworks.sassybarista.sass;

import java.util.*;

import net.quenchnetworks.sassybarista.sass.value.*;

public class SassSheet
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
    
    public Map<String, IPropertyValue> getVariables() { return variables; }
    public Map<String, Mixin> getMixins() { return mixins; }
    public List<Rule> getRules() { return rules; }
}
