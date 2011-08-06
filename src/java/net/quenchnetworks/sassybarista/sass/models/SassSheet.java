package net.quenchnetworks.sassybarista.sass.models;

import java.io.Serializable;
import java.util.*;

import net.quenchnetworks.sassybarista.sass.expression.*;
import net.quenchnetworks.sassybarista.sass.value.*;

public class SassSheet implements Serializable
{
    private Map<String, INode> variables;
    private Map<String, Mixin> mixins;
    private List<Rule> rules;
    
    public SassSheet(Map<String, INode> variables, 
        Map<String, Mixin> mixins, List<Rule> rules)
    {
        this.variables = variables;
        this.mixins = mixins;
        this.rules = rules;
    }
    
    public SassSheet()
    {
        this.variables = new LinkedHashMap<String, INode>();
        this.mixins = new HashMap<String, Mixin>();
        this.rules = new ArrayList<Rule>();
    }
    
    public Map<String, INode> getVariables() { return variables; }
    public void setVariables(Map<String, INode> v) { this.variables = v; }
    
    public Map<String, Mixin> getMixins() { return mixins; }
    public void setMixins(Map<String, Mixin> v) { this.mixins = v; }
    
    public List<Rule> getRules() { return rules; }
    public void setRules(List<Rule> v) { this.rules = v; }
    
    public SassSheet copy()
    {
        SassSheet newSheet = new SassSheet();
        for (Map.Entry<String, INode> entry : variables.entrySet()) {
            newSheet.variables.put(entry.getKey(), entry.getValue().copy());
        }
        
        for (Map.Entry<String, Mixin> entry : mixins.entrySet()) {
            newSheet.mixins.put(entry.getKey(), entry.getValue().copy());
        }
        
        for (Rule rule : rules) {
            newSheet.rules.add(rule.copy());
        }
        
        return newSheet;
    }
}
