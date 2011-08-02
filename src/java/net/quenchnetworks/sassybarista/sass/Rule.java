package net.quenchnetworks.sassybarista.sass;

import java.util.*;

public class Rule extends Block
{
    private List<SelectorChain> selectorChains;
    private List<Selector> extensions;

    public Rule()
    {
        super();
        this.selectorChains = new ArrayList<SelectorChain>();
        this.extensions = new ArrayList<Selector>();
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
    
    public void addExtend(Selector v)
    {
        extensions.add(v);
    }
    
    public List<Selector> getExtends()
    {
        return extensions;
    }
}
