package net.quenchnetworks.sassybarista.sass.value;

import java.util.*;
import java.io.Serializable;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.expression.*;
import net.quenchnetworks.sassybarista.sass.value.op.*;

public class BooleanPropertyValue extends AbstractPropertyValue implements Serializable
{
    private static class EqOp extends OpAdapter
    {
        private BooleanPropertyValue value1;

        public EqOp(BooleanPropertyValue value1)
        {
            super("BooleanPropertyValue");
            this.value1 = value1;
        }
        
        @Override
        public IPropertyValue op(BooleanPropertyValue value2)
        throws EvaluationException
        {
            boolean v1 = value1.getValue();
            boolean v2 = value2.getValue();
            
            return new BooleanPropertyValue(v1 == v2);
        }
    }
    
    private static class NotEqOp extends OpAdapter
    {
        private BooleanPropertyValue value1;

        public NotEqOp(BooleanPropertyValue value1)
        {
            super("BooleanPropertyValue");
            this.value1 = value1;
        }
        
        @Override
        public IPropertyValue op(BooleanPropertyValue value2)
        throws EvaluationException
        {
            boolean v1 = value1.getValue();
            boolean v2 = value2.getValue();
            
            return new BooleanPropertyValue(v1 != v2);
        }
    }

    private boolean value;

    public BooleanPropertyValue()
    {
        super("BooleanPropertyValue");
        this.value = false;
    }
    
    public BooleanPropertyValue(boolean v)
    {
        super("BooleanPropertyValue");
        this.value = v;
    }
    
    public BooleanPropertyValue(String value)
    {
        super("BooleanPropertyValue");
        this.value = Boolean.valueOf(value);
    }
    
    public boolean getValue()
    {
        return value;
    }
    
    @Override
    public IOp getEqOp()
    {
        return new EqOp(this);
    }
    
    @Override
    public IOp getNotEqOp()
    {
        return new NotEqOp(this);
    }
    
    @Override
    public IPropertyValue callAddOp(IPropertyValue node) 
    throws EvaluationException
    {
        IOp op = node.getAdditionOp();
        return op.op(this);
    }

    @Override
    public IPropertyValue callSubOp(IPropertyValue node) 
    throws EvaluationException
    {
        IOp op = node.getSubtractionOp();
        return op.op(this);
    }

    @Override
    public IPropertyValue callMulOp(IPropertyValue node) 
    throws EvaluationException
    {
        IOp op = node.getMultiplicationOp();
        return op.op(this);
    }
    
    @Override
    public IPropertyValue callDivOp(IPropertyValue node) 
    throws EvaluationException
    {
        IOp op = node.getDivisionOp();
        return op.op(this);
    }
    
    @Override
    public IPropertyValue callEqOp(IPropertyValue node) 
    throws EvaluationException
    {
        IOp op = node.getEqOp();
        return op.op(this);
    }

    @Override
    public IPropertyValue callNotEqOp(IPropertyValue node) 
    throws EvaluationException
    {
        IOp op = node.getNotEqOp();
        return op.op(this);
    }

    @Override
    public IPropertyValue callLtOp(IPropertyValue node) 
    throws EvaluationException
    {
        IOp op = node.getLtOp();
        return op.op(this);
    }
    
    @Override
    public IPropertyValue callGtOp(IPropertyValue node) 
    throws EvaluationException
    {
        IOp op = node.getGtOp();
        return op.op(this);
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
