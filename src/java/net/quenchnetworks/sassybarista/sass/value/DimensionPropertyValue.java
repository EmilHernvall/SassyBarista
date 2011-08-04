package net.quenchnetworks.sassybarista.sass.value;

import java.util.*;
import java.io.Serializable;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.expression.*;
import net.quenchnetworks.sassybarista.sass.value.op.*;

public class DimensionPropertyValue extends AbstractPropertyValue implements Serializable
{
    private static class AdditionOp extends AdditionOpAdapter
    {
        private DimensionPropertyValue value1;

        public AdditionOp(DimensionPropertyValue value1)
        {
            this.value1 = value1;
        }
        
        @Override
        public IPropertyValue addOp(NumberPropertyValue value2)
        throws EvaluationException
        {
            int v1 = value1.getValue();
            int v2 = value2.getValue();
            
            return new DimensionPropertyValue(v1 + v2, value1.getUnit());
        }
        
        @Override
        public IPropertyValue addOp(DimensionPropertyValue value2)
        throws EvaluationException
        {
            int v1 = value1.getValue();
            int v2 = value2.getValue();
            
            if (!value2.getUnit().equals(value1.getUnit())) {
                throw new EvaluationException("Arithmetic on values in different units is not yet supported.");
            }
            
            return new DimensionPropertyValue(v1 + v2, value1.getUnit());
        }
    }
    
    private static class SubtractionOp extends SubtractionOpAdapter
    {
        private DimensionPropertyValue value1;

        public SubtractionOp(DimensionPropertyValue value1)
        {
            this.value1 = value1;
        }
        
        @Override
        public IPropertyValue subOp(NumberPropertyValue value2)
        throws EvaluationException
        {
            int v1 = value1.getValue();
            int v2 = value2.getValue();
            
            return new DimensionPropertyValue(v1 - v2, value1.getUnit());
        }
        
        @Override
        public IPropertyValue subOp(DimensionPropertyValue value2)
        throws EvaluationException
        {
            int v1 = value1.getValue();
            int v2 = value2.getValue();
            
            if (!value2.getUnit().equals(value1.getUnit())) {
                throw new EvaluationException("Arithmetic on values in different units is not yet supported.");
            }
            
            return new DimensionPropertyValue(v1 - v2, value1.getUnit());
        }
    }
    
    private static class MultiplicationOp extends MultiplicationOpAdapter
    {
        private DimensionPropertyValue value1;

        public MultiplicationOp(DimensionPropertyValue value1)
        {
            this.value1 = value1;
        }
        
        @Override
        public IPropertyValue mulOp(NumberPropertyValue value2)
        throws EvaluationException
        {
            int v1 = value1.getValue();
            int v2 = value2.getValue();
            
            return new DimensionPropertyValue(v1 * v2, value1.getUnit());
        }
        
        @Override
        public IPropertyValue mulOp(DimensionPropertyValue value2)
        throws EvaluationException
        {
            int v1 = value1.getValue();
            int v2 = value2.getValue();
            
            if (!value2.getUnit().equals(value1.getUnit())) {
                throw new EvaluationException("Arithmetic on values in different units is not yet supported.");
            }
            
            return new DimensionPropertyValue(v1 * v2, value1.getUnit());
        }
    }
    
    private static class DivisionOp extends DivisionOpAdapter
    {
        private DimensionPropertyValue value1;

        public DivisionOp(DimensionPropertyValue value1)
        {
            this.value1 = value1;
        }
        
        @Override
        public IPropertyValue divOp(NumberPropertyValue value2)
        throws EvaluationException
        {
            int v1 = value1.getValue();
            int v2 = value2.getValue();
            
            return new DimensionPropertyValue(v1 / v2, value1.getUnit());
        }
        
        @Override
        public IPropertyValue divOp(DimensionPropertyValue value2)
        throws EvaluationException
        {
            int v1 = value1.getValue();
            int v2 = value2.getValue();
            
            if (!value2.getUnit().equals(value1.getUnit())) {
                throw new EvaluationException("Arithmetic on values in different units is not yet supported.");
            }
            
            return new DimensionPropertyValue(v1 / v2, value1.getUnit());
        }
    }

    private int value;
    private String unit;

    public DimensionPropertyValue()
    {
        this.value = 0;
    }
    
    public DimensionPropertyValue(int v, String unit)
    {
        this.value = v;
        this.unit = unit;
    }
    
    public DimensionPropertyValue(String value)
    {
        if (value.endsWith("px")) {
            this.value = Integer.parseInt(value.substring(0, value.length()-2));
            unit = "px";
        }
        else if (value.endsWith("em")) {
            this.value = Integer.parseInt(value.substring(0, value.length()-2));
            unit = "em";
        }
        else if (value.endsWith("pt")) {
            this.value = Integer.parseInt(value.substring(0, value.length()-2));
            unit = "pt";
        }
        else {
            throw new RuntimeException("Unknown unit: " + value);
        }
    }
    
    public int getValue()
    {
        return value;
    }
    
    public String getUnit()
    {
        return unit;
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
        return Integer.toString(value) + "px";
    }
}
