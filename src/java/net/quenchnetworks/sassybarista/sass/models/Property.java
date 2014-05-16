package net.quenchnetworks.sassybarista.sass.models;

import java.io.Serializable;
import java.util.*;

import net.quenchnetworks.sassybarista.sass.value.*;
import net.quenchnetworks.sassybarista.sass.expression.*;

public class Property implements Serializable
{
    private String key;
    private List<INode> values;

    public Property()
    {
        this.key = null;
        this.values = new ArrayList<INode>();
    }

    public Property(String key)
    {
        this.key = key;
        this.values = new ArrayList<INode>();
    }

    public void setValues(List<INode> values)
    {
        this.values = values;
    }

    public void addValue(INode value)
    {
        values.add(value);
    }

    public void setKey(String v)
    {
        this.key = v;
    }

    public String getKey()
    {
        return key;
    }

    public List<INode> getValues()
    {
        return values;
    }

    public Property copy()
    {
        Property newProp = new Property();
        newProp.key = key;

        for (INode value : values) {
            newProp.addValue(value.copy());
        }

        return newProp;
    }
}
