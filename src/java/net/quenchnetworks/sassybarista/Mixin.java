package net.quenchnetworks.sassybarista;

import java.util.*;

public class Mixin extends Block
{
	private String name;

	public Mixin()
	{
		super();
	}
	
	public String getName() { return name; }
	public void setName(String v) { this.name = v; }
}
