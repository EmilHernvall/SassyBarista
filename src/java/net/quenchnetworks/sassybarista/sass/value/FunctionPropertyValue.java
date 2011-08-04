package net.quenchnetworks.sassybarista.sass.value;

import java.util.*;
import java.io.Serializable;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.expression.*;
import net.quenchnetworks.sassybarista.sass.value.op.*;

public class FunctionPropertyValue extends AbstractPropertyValue implements Serializable
{
    private String name;
    private List<IPropertyValue> values;

    public FunctionPropertyValue()
    {
        this.name = null;
        this.values = new ArrayList<IPropertyValue>();
    }
    
    public FunctionPropertyValue(String name)
    {
        this.name = name;
        this.values = new ArrayList<IPropertyValue>();
    }
    
    public void addValue(IPropertyValue value)
    {
        values.add(value);
    }
    
    public List<IPropertyValue> getValues()
    {
        return values;
    }
    
    @Override
    public IPropertyValue callAddOp(IPropertyValue node) 
    throws EvaluationException
    {
        IAdditionOp op = node.getAdditionOp();
        return op.addOp(this);
    }

    @Override
    public IPropertyValue callSubOp(IPropertyValue node) 
    throws EvaluationException
    {
        ISubtractionOp op = node.getSubtractionOp();
        return op.subOp(this);
    }

    @Override
    public IPropertyValue callMulOp(IPropertyValue node) 
    throws EvaluationException
    {
        IMultiplicationOp op = node.getMultiplicationOp();
        return op.mulOp(this);
    }
    
    @Override
    public IPropertyValue callDivOp(IPropertyValue node) 
    throws EvaluationException
    {
        IDivisionOp op = node.getDivisionOp();
        return op.divOp(this);
    }
    
    @Override
    public String toString()
    {
        StringBuilder buffer = new StringBuilder();
        
        buffer.append(name);
        buffer.append("(");
        
        int i = 0;
        for (IPropertyValue value : values) {
            buffer.append(value.toString());
            if (i < values.size() - 1) {
                buffer.append(" ");
            }
            i++;
        }
        
        buffer.append(")");
        
        return buffer.toString();
    }
}
