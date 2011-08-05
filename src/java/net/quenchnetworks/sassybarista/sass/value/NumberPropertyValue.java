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
            BigDecimal v1 = value1.getValue();
            BigDecimal v2 = value2.getValue();
            
            return new NumberPropertyValue(v1.add(v2));
        }
        
        @Override
        public IPropertyValue addOp(DimensionPropertyValue value2)
        throws EvaluationException
        {
            BigDecimal v1 = value1.getValue();
            BigDecimal v2 = value2.getValue();
            
            return new DimensionPropertyValue(v1.add(v2), value2.getUnit());
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
            BigDecimal v1 = value1.getValue();
            BigDecimal v2 = value2.getValue();
            
            return new NumberPropertyValue(v2.subtract(v1));
        }
        
        @Override
        public IPropertyValue subOp(DimensionPropertyValue value2)
        throws EvaluationException
        {
            BigDecimal v1 = value1.getValue();
            BigDecimal v2 = value2.getValue();
            
            return new DimensionPropertyValue(v2.subtract(v1), value2.getUnit());
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
            BigDecimal v1 = value1.getValue();
            BigDecimal v2 = value2.getValue();
            
            return new NumberPropertyValue(v1.multiply(v2));
        }
        
        @Override
        public IPropertyValue mulOp(DimensionPropertyValue value2)
        throws EvaluationException
        {
            BigDecimal v1 = value1.getValue();
            BigDecimal v2 = value2.getValue();
            
            return new DimensionPropertyValue(v1.multiply(v2), value2.getUnit());
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
            BigDecimal v1 = value1.getValue();
            BigDecimal v2 = value2.getValue();
            
            return new NumberPropertyValue(v2.divide(v1));
        }
        
        @Override
        public IPropertyValue divOp(DimensionPropertyValue value2)
        throws EvaluationException
        {
            BigDecimal v1 = value1.getValue();
            BigDecimal v2 = value2.getValue();
            
            return new DimensionPropertyValue(v2.divide(v1), value2.getUnit());
        }
    }
    
    private static class EqOp extends EqOpAdapter
    {
        private NumberPropertyValue value1;

        public EqOp(NumberPropertyValue value1)
        {
            this.value1 = value1;
        }
        
        @Override
        public IPropertyValue eqOp(NumberPropertyValue value2)
        throws EvaluationException
        {
            BigDecimal v1 = value1.getValue();
            BigDecimal v2 = value2.getValue();
            
            return new BooleanPropertyValue(v1.equals(v2));
        }
    }
    
    private static class NotEqOp extends NotEqOpAdapter
    {
        private NumberPropertyValue value1;

        public NotEqOp(NumberPropertyValue value1)
        {
            this.value1 = value1;
        }
        
        @Override
        public IPropertyValue notEqOp(NumberPropertyValue value2)
        throws EvaluationException
        {
            BigDecimal v1 = value1.getValue();
            BigDecimal v2 = value2.getValue();
            
            return new BooleanPropertyValue(!v1.equals(v2));
        }
    }
    
    private static class LtOp extends LtOpAdapter
    {
        private NumberPropertyValue value1;

        public LtOp(NumberPropertyValue value1)
        {
            this.value1 = value1;
        }
        
        @Override
        public IPropertyValue ltOp(NumberPropertyValue value2)
        throws EvaluationException
        {
            BigDecimal v1 = value1.getValue();
            BigDecimal v2 = value2.getValue();
            
            return new BooleanPropertyValue(v1.compareTo(v2) > 0);
        }
    }
    
    private static class GtOp extends GtOpAdapter
    {
        private NumberPropertyValue value1;

        public GtOp(NumberPropertyValue value1)
        {
            this.value1 = value1;
        }
        
        @Override
        public IPropertyValue gtOp(NumberPropertyValue value2)
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
        this.value = new BigDecimal(0);
    }
    
    public NumberPropertyValue(String value)
    {
        this.value = new BigDecimal(value);
    }
    
    public NumberPropertyValue(BigDecimal value)
    {
        this.value = value;
    }
    
    public BigDecimal getValue()
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
    public IEqOp getEqOp()
    {
        return new EqOp(this);
    }
    
    @Override
    public INotEqOp getNotEqOp()
    {
        return new NotEqOp(this);
    }
    
    @Override
    public ILtOp getLtOp()
    {
        return new LtOp(this);
    }
    
    @Override
    public IGtOp getGtOp()
    {
        return new GtOp(this);
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
    public IPropertyValue negateOp() 
    throws EvaluationException
    {
        value = value.negate();
        
        return this;
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
        return new NumberPropertyValue(value);
    }
    
    @Override
    public String toString()
    {
        return value.toString();
    }
}
