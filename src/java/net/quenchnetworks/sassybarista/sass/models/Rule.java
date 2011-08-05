package net.quenchnetworks.sassybarista.sass.models;

import java.io.Serializable;
import java.util.*;

public class Rule extends Block implements Serializable
{
    private List<SelectorChain> selectorChains;
    private List<Selector> extensions;
    private List<IncludeDirective> includes;
    private List<String> imports;

    public Rule()
    {
        super();
        this.selectorChains = new ArrayList<SelectorChain>();
        this.extensions = new ArrayList<Selector>();
        this.includes = new ArrayList<IncludeDirective>();
        this.imports = new ArrayList<String>();
    }
    
    public void setSelectorChains(List<SelectorChain> selectorChains)
    {
        this.selectorChains = selectorChains;
    }
    
    public void addSelectorChain(SelectorChain selectorChain)
    {
        selectorChains.add(selectorChain);
    }
    
    public List<SelectorChain> getSelectorChains()
    {
        return selectorChains;
    }
    
    public void setIncludes(List<IncludeDirective> includes)
    {
        this.includes = includes;
    }
    
    public void addInclude(IncludeDirective include)
    {
        includes.add(include);
    }
    
    public List<IncludeDirective> getIncludes()
    {
        return includes;
    }
    
    public void addImport(String v)
    {
        imports.add(v);
    }
    
    public List<String> getImports()
    {
        return imports;
    }
    
    public void setExtends(List<Selector> v)
    {
        this.extensions = v;
    }
    
    public void addExtend(Selector v)
    {
        extensions.add(v);
    }
    
    public List<Selector> getExtends()
    {
        return extensions;
    }
    
    public Rule copy()
    {
        Rule rule = new Rule();
        
        // copies sub rules and properites
        super.copy(rule);
        
        for (SelectorChain chain : selectorChains) {
            rule.addSelectorChain(chain.copy());
        }
        
        for (Selector sel : extensions) {
            rule.addExtend(sel.copy());
        }
        
        for (IncludeDirective include : includes) {
            rule.addInclude(include.copy());
        }
        
        for (String i : imports) {
            rule.addImport(i);
        }
        
        return rule;
    }
}
