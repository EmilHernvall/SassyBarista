package net.quenchnetworks.sassybarista;

import java.util.*;

public class SelectorChain
{
	private LinkedList<Selector> selectors;

	public SelectorChain()
	{
		this.selectors = new LinkedList<Selector>();
	}
	
	public void prependSelector(Selector selector)
	{
		selectors.addFirst(selector);
	}
	
	public void addSelector(Selector selector)
	{
		selectors.add(selector);
	}
	
	public List<Selector> getSelectors()
	{
		return selectors;
	}
	
	public Iterator<Selector> getReverseIterator()
	{
		return selectors.descendingIterator();
	}
	
	@Override
	public String toString()
	{
		StringBuilder buffer = new StringBuilder();
		for (Selector selector : selectors) {
			buffer.append(selector);
			buffer.append(" ");
		}
		
		return buffer.toString();
	}
}
