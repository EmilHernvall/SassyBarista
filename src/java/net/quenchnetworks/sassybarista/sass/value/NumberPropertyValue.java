package net.quenchnetworks.sassybarista.sass.value;

import java.util.*;
import java.math.*;
import java.io.Serializable;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.expression.*;
import net.quenchnetworks.sassybarista.sass.value.op.*;

public class NumberPropertyValue extends AbstractPropertyValue implements Serializable
{
    private static class AdditionOp extends OpAdapter
    {
        private NumberPropertyValue value1;

        public AdditionOp(NumberPropertyValue value1)
        {
            super("NumberPropertyValue");
            this.value1 = value1;
        }
        
        @Override
        public IPropertyValue op(NumberPropertyValue value2)
        throws EvaluationException
        {
            BigDecimal v1 = value1.getValue();
            BigDecimal v2 = value2.getValue();
            
            return new NumberPropertyValue(v1.add(v2));
        }
        
        @Override
        public IPropertyValue op(DimensionPropertyValue value2)
        throws EvaluationException
        {
            BigDecimal v1 = value1.getValue();
            BigDecimal v2 = value2.getValue();
            
            return new DimensionPropertyValue(v1.add(v2), value2.getUnit());
        }
    }
    
    private static class SubtractionOp extends OpAdapter
    {
        private NumberPropertyValue value1;

        public SubtractionOp(NumberPropertyValue value1)
        {
            super("NumberPropertyValue");
            this.value1 = value1;
        }
        
        @Override
        public IPropertyValue op(NumberPropertyValue value2)
        throws EvaluationException
        {
            BigDecimal v1 = value1.getValue();
            BigDecimal v2 = value2.getValue();
            
            return new NumberPropertyValue(v2.subtract(v1));
        }
        
        @Override
        public IPropertyValue op(DimensionPropertyValue value2)
        throws EvaluationException
        {
            BigDecimal v1 = value1.getValue();
            BigDecimal v2 = value2.getValue();
            
            return new DimensionPropertyValue(v2.subtract(v1), value2.getUnit());
        }
    }
    
    private static class MultiplicationOp extends OpAdapter
    {
        private NumberPropertyValue value1;

        public MultiplicationOp(NumberPropertyValue value1)
        {
            super("NumberPropertyValue");
            this.value1 = value1;
        }
        
        @Override
        public IPropertyValue op(NumberPropertyValue value2)
        throws EvaluationException
        {
            BigDecimal v1 = value1.getValue();
            BigDecimal v2 = value2.getValue();
            
            return new NumberPropertyValue(v1.multiply(v2));
        }
        
        @Override
        public IPropertyValue op(DimensionPropertyValue value2)
        throws EvaluationException
        {
            BigDecimal v1 = value1.getValue();
            BigDecimal v2 = value2.getValue();
            
            return new DimensionPropertyValue(v1.multiply(v2), value2.getUnit());
        }
    }
    
    private static class DivisionOp extends OpAdapter
    {
        private NumberPropertyValue value1;

        public DivisionOp(NumberPropertyValue value1)
        {
            super("NumberPropertyValue");
            this.value1 = value1;
        }
        
        @Override
        public IPropertyValue op(NumberPropertyValue value2)
        throws EvaluationException
        {
            BigDecimal v1 = value1.getValue();
            BigDecimal v2 = value2.getValue();
            
            return new NumberPropertyValue(v2.divide(v1));
        }
        
        @Override
        public IPropertyValue op(DimensionPropertyValue value2)
        throws EvaluationException
        {
            BigDecimal v1 = value1.getValue();
            BigDecimal v2 = value2.getValue();
            
            return new DimensionPropertyValue(v2.divide(v1), value2.getUnit());
        }
    }
    
    private static class EqOp extends OpAdapter
    {
        private NumberPropertyValue value1;

        public EqOp(NumberPropertyValue value1)
        {
            super("NumberPropertyValue");
            this.value1 = value1;
        }
        
        @Override
        public IPropertyValue op(NumberPropertyValue value2)
        throws EvaluationException
        {
            BigDecimal v1 = value1.getValue();
            BigDecimal v2 = value2.getValue();
            
            return new BooleanPropertyValue(v1.equals(v2));
        }
    }
    
    private static class NotEqOp extends OpAdapter
    {
        private NumberPropertyValue value1;

        public NotEqOp(NumberPropertyValue value1)
        {
            super("NumberPropertyValue");
            this.value1 = value1;
        }
        
        @Override
        public IPropertyValue op(NumberPropertyValue value2)
        throws EvaluationException
        {
            BigDecimal v1 = value1.getValue();
            BigDecimal v2 = value2.getValue();
            
            return new BooleanPropertyValue(!v1.equals(v2));
        }
    }
    
    private static class LtOp extends OpAdapter
    {
        private NumberPropertyValue value1;

        public LtOp(NumberPropertyValue value1)
        {
            super("NumberPropertyValue");
            this.value1 = value1;
        }
        
        @Override
        public IPropertyValue op(NumberPropertyValue value2)
        throws EvaluationException
        {
            BigDecimal v1 = value1.getValue();
            BigDecimal v2 = value2.getValue();
            
            return new BooleanPropertyValue(v1.compareTo(v2) > 0);
        }
    }
    
    private static class GtOp extends OpAdapter
    {
        private NumberPropertyValue value1;

        public GtOp(NumberPropertyValue value1)
        {
            super("NumberPropertyValue");
            this.value1 = value1;
        }
        
        @Override
        public IPropertyValue op(NumberPropertyValue value2)
        throws EvaluationException
        {
            BigDecimal v1 = value1.getValue();
            BigDecimal v2 = value2.getValue();
            
            return new BooleanPropertyValue(v1.compareTo(v2) < 0);
        }
    }

    private BigDecimal value;

    public NumberPropertyValue()
    {
        super("NumberPropertyValue");
        this.value = new BigDecimal(0);
    }
    
    public NumberPropertyValue(String value)
    {
        super("NumberPropertyValue");
        this.value = new BigDecimal(value);
    }
    
    public NumberPropertyValue(BigDecimal value)
    {
        super("NumberPropertyValue");
        this.value = value;
    }
    
    public BigDecimal getValue()
    {
        return value;
    }
    
    @Override
    public IOp getAdditionOp()
    {
        return new AdditionOp(this);
    }
    
    @Override
    public IOp getSubtractionOp()
    {
        return new SubtractionOp(this);
    }
    
    @Override
    public IOp getMultiplicationOp()
    {
        return new MultiplicationOp(this);
    }
    
    @Override
    public IOp getDivisionOp()
    {
        return new DivisionOp(this);
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
    public IOp getLtOp()
    {
        return new LtOp(this);
    }
    
    @Override
    public IOp getGtOp()
    {
        return new GtOp(this);
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
    public IPropertyValue negateOp() 
    throws EvaluationException
    {
        return new NumberPropertyValue(value.negate());
    }
    
    @Override
    public IPropertyValue copy()
    {
        return new NumberPropertyValue(value);
    }
    
    @Override
    public String toString()
    {
        return value.toString();
    }
}
