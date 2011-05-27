package net.quenchnetworks.sassybarista.value;

import java.util.*;

import net.quenchnetworks.sassybarista.*;

public interface IPropertyValue
{
	public String serialize(Map<String, IPropertyValue> variables)
	throws SerializationException;
}
