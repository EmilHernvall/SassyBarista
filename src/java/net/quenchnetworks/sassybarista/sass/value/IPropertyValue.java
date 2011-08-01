package net.quenchnetworks.sassybarista.sass.value;

import java.util.*;

import net.quenchnetworks.sassybarista.sass.*;

public interface IPropertyValue
{
	public String serialize(Map<String, IPropertyValue> variables)
	throws SerializationException;
}
