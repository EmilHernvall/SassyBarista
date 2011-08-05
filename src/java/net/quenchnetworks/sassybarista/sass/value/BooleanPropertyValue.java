package net.quenchnetworks.sassybarista.sass.value;

import java.util.*;
import java.io.Serializable;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.expression.*;
import net.quenchnetworks.sassybarista.sass.value.op.*;

public class BooleanPropertyValue extends AbstractPropertyValue implements Serializable
{
    private boolean value;

    public BooleanPropertyValue()
    {
        this.value = false;
    }
    
    public BooleanPropertyValue(boolean v)
    {
        this.value = v;
    }
    
    public BooleanPropertyValue(String value)
    {
        this.value = Boolean.valueOf(value);
    }
    
    public boolean getValue()
    {
        return value;
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
    public IPropertyValue callEqOp(IPropertyValue node) 
    throws EvaluationException
    {
        IEqOp op = node.getEqOp();
        return op.eqOp(this);
    }

    @Override
    public IPropertyValue callNotEqOp(IPropertyValue node) 
    throws EvaluationException
    {
        INotEqOp op = node.getNotEqOp();
        return op.notEqOp(this);
    }

    @Override
    public IPropertyValue callLtOp(IPropertyValue node) 
    throws EvaluationException
    {
        ILtOp op = node.getLtOp();
        return op.ltOp(this);
    }
    
    @Override
    public IPropertyValue callGtOp(IPropertyValue node) 
    throws EvaluationException
    {
        IGtOp op = node.getGtOp();
        return op.gtOp(this);
    }
    
    @Override
    public IPropertyValue copy()
    {
        return new BooleanPropertyValue(value);
    }
    
    @Override
    public String toString()
    {
        return Boolean.toString(value);
    }
}
