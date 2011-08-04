package net.quenchnetworks.sassybarista.sass.value;

import java.util.*;
import java.io.Serializable;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.expression.*;
import net.quenchnetworks.sassybarista.sass.value.op.*;

public class NumberPropertyValue extends AbstractPropertyValue implements Serializable
{
    private static class AdditionOp extends AdditionOpAdapter
    {
        private NumberPropertyValue value1;

        public AdditionOp(NumberPropertyValue value1)
        {
            this.value1 = value1;
        }
        
        @Override
        public IPropertyValue addOp(NumberPropertyValue value2)
        throws EvaluationException
        {
            int v1 = value1.getValue();
            int v2 = value2.getValue();
            
            return new NumberPropertyValue(v1 + v2);
        }
        
        @Override
        public IPropertyValue addOp(DimensionPropertyValue value2)
        throws EvaluationException
        {
            int v1 = value1.getValue();
            int v2 = value2.getValue();
            
            return new DimensionPropertyValue(v1 + v2, value2.getUnit());
        }
    }
    
    private static class SubtractionOp extends SubtractionOpAdapter
    {
        private NumberPropertyValue value1;

        public SubtractionOp(NumberPropertyValue value1)
        {
            this.value1 = value1;
        }
        
        @Override
        public IPropertyValue subOp(NumberPropertyValue value2)
        throws EvaluationException
        {
            int v1 = value1.getValue();
            int v2 = value2.getValue();
            
            return new NumberPropertyValue(v2 - v1);
        }
        
        @Override
        public IPropertyValue subOp(DimensionPropertyValue value2)
        throws EvaluationException
        {
            int v1 = value1.getValue();
            int v2 = value2.getValue();
            
            return new DimensionPropertyValue(v2 - v1, value2.getUnit());
        }
    }
    
    private static class MultiplicationOp extends MultiplicationOpAdapter
    {
        private NumberPropertyValue value1;

        public MultiplicationOp(NumberPropertyValue value1)
        {
            this.value1 = value1;
        }
        
        @Override
        public IPropertyValue mulOp(NumberPropertyValue value2)
        throws EvaluationException
        {
            int v1 = value1.getValue();
            int v2 = value2.getValue();
            
            return new NumberPropertyValue(v1 * v2);
        }
        
        @Override
        public IPropertyValue mulOp(DimensionPropertyValue value2)
        throws EvaluationException
        {
            int v1 = value1.getValue();
            int v2 = value2.getValue();
            
            return new DimensionPropertyValue(v1 * v2, value2.getUnit());
        }
    }
    
    private static class DivisionOp extends DivisionOpAdapter
    {
        private NumberPropertyValue value1;

        public DivisionOp(NumberPropertyValue value1)
        {
            this.value1 = value1;
        }
        
        @Override
        public IPropertyValue divOp(NumberPropertyValue value2)
        throws EvaluationException
        {
            int v1 = value1.getValue();
            int v2 = value2.getValue();
            
            return new NumberPropertyValue(v1 / v2);
        }
        
        @Override
        public IPropertyValue divOp(DimensionPropertyValue value2)
        throws EvaluationException
        {
            int v1 = value1.getValue();
            int v2 = value2.getValue();
            
            return new DimensionPropertyValue(v1 / v2, value2.getUnit());
        }
    }

    private int value;

    public NumberPropertyValue()
    {
        this.value = 0;
    }
    
    public NumberPropertyValue(String value)
    {
        this.value = Integer.parseInt(value);
    }
    
    public NumberPropertyValue(int value)
    {
        this.value = value;
    }
    
    public int getValue()
    {
        return value;
    }
    
    @Override
    public IAdditionOp getAdditionOp()
    {
        return new AdditionOp(this);
    }
    
    @Override
    public ISubtractionOp getSubtractionOp()
    {
        return new SubtractionOp(this);
    }
    
    @Override
    public IMultiplicationOp getMultiplicationOp()
    {
        return new MultiplicationOp(this);
    }
    
    @Override
    public IDivisionOp getDivisionOp()
    {
        return new DivisionOp(this);
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
        return Integer.toString(value);
    }
}
