package net.quenchnetworks.sassybarista.sass.models;

import java.io.Serializable;
import java.util.*;

public class SelectorChain implements Serializable
{
    private List<Selector> selectors;

    public SelectorChain()
    {
        this.selectors = new ArrayList<Selector>();
    }
    
    public void addSelector(Selector selector)
    {
        selectors.add(selector);
    }
    
    public void addSelectors(List<Selector> newSelectors)
    {
        selectors.addAll(newSelectors);
    }
    
    public List<Selector> getSelectors()
    {
        return selectors;
    }
    
    public SelectorChain copy()
    {
        SelectorChain newChain = new SelectorChain();
        for (Selector selector : selectors) {
            newChain.addSelector(selector.copy());
        }
        
        return newChain;
    }
    
    public boolean matchesPath(List<Selector> path)
    {
        Iterator<Selector> chainIt = selectors.iterator();
        Iterator<Selector> pathIt = path.iterator();
        
        Selector chainSel = chainIt.next();
        int matched = 0;
        while (pathIt.hasNext()) {
            Selector pathSel = pathIt.next();
            //System.out.println("cmp: " + pathSel + " == " + chainSel);
            if (chainSel.matches(pathSel)) {
                //System.out.println("match");
                matched++;
                if (!chainIt.hasNext()) {
                    if (!pathIt.hasNext()) {
                        break;
                    }
                    return false;
                }
                chainSel = chainIt.next();
            }
        }
    
        if (matched == selectors.size()) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode()
    {
        int code = 17;
        for (Selector selector : selectors) {
            code = 31 * code + selector.hashCode();
        }
        
        return code;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof SelectorChain)) {
            return false;
        }
        
        return hashCode() == obj.hashCode();
    }
    
    @Override
    public String toString()
    {
        StringBuilder buffer = new StringBuilder();
        int i = 0;
        for (Selector selector : selectors) {
            buffer.append(selector);
            if (i < selectors.size() - 1) {
                buffer.append(" ");
            }
            i++;
        }
        
        return buffer.toString();
    }
}
