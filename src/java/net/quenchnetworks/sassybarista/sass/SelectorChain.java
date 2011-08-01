package net.quenchnetworks.sassybarista.sass;

import java.util.*;

public class SelectorChain
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
