package net.quenchnetworks.sassybarista;

import java.util.*;

public class Rule extends Block
{
	private List<SelectorChain> selectorChains;

	public Rule()
	{
		super();
		this.selectorChains = new ArrayList<SelectorChain>();
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
}
