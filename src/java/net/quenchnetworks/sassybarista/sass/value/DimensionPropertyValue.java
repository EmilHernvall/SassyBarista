package net.quenchnetworks.sassybarista.sass.value;

import java.util.*;
import java.math.*;
import java.io.Serializable;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.expression.*;
import net.quenchnetworks.sassybarista.sass.value.op.*;

public class DimensionPropertyValue extends AbstractPropertyValue implements Serializable
{
    private static class AdditionOp extends OpAdapter
    {
        private DimensionPropertyValue value1;

        public AdditionOp(DimensionPropertyValue value1)
        {
            super("DimensionPropertyValue");
            this.value1 = value1;
        }
        
        @Override
        public IPropertyValue op(NumberPropertyValue value2)
        throws EvaluationException
        {
            BigDecimal v1 = value1.getValue();
            BigDecimal v2 = value2.getValue();
            
            return new DimensionPropertyValue(v1.add(v2), value1.getUnit());
        }
        
        @Override
        public IPropertyValue op(DimensionPropertyValue value2)
        throws EvaluationException
        {
            BigDecimal v1 = value1.getValue();
            BigDecimal v2 = value2.getValue();
            
            if (!value2.getUnit().equals(value1.getUnit())) {
                throw new EvaluationException("Arithmetic on values in different units is not yet supported.");
            }
            
            return new DimensionPropertyValue(v1.add(v2), value1.getUnit());
        }
    }
    
    private static class SubtractionOp extends OpAdapter
    {
        private DimensionPropertyValue value1;

        public SubtractionOp(DimensionPropertyValue value1)
        {
            super("DimensionPropertyValue");
            this.value1 = value1;
        }
        
        @Override
        public IPropertyValue op(NumberPropertyValue value2)
        throws EvaluationException
        {
            BigDecimal v1 = value1.getValue();
            BigDecimal v2 = value2.getValue();
            
            return new DimensionPropertyValue(v2.subtract(v1), value1.getUnit());
        }
        
        @Override
        public IPropertyValue op(DimensionPropertyValue value2)
        throws EvaluationException
        {
            BigDecimal v1 = value1.getValue();
            BigDecimal v2 = value2.getValue();
            
            if (!value2.getUnit().equals(value1.getUnit())) {
                throw new EvaluationException("Arithmetic on values in different units is not yet supported.");
            }
            
            return new DimensionPropertyValue(v2.subtract(v1), value1.getUnit());
        }
    }
    
    private static class MultiplicationOp extends OpAdapter
    {
        private DimensionPropertyValue value1;

        public MultiplicationOp(DimensionPropertyValue value1)
        {
            super("DimensionPropertyValue");
            this.value1 = value1;
        }
        
        @Override
        public IPropertyValue op(NumberPropertyValue value2)
        throws EvaluationException
        {
            BigDecimal v1 = value1.getValue();
            BigDecimal v2 = value2.getValue();
            
            return new DimensionPropertyValue(v1.multiply(v2), value1.getUnit());
        }
        
        @Override
        public IPropertyValue op(DimensionPropertyValue value2)
        throws EvaluationException
        {
            BigDecimal v1 = value1.getValue();
            BigDecimal v2 = value2.getValue();
            
            if (!value2.getUnit().equals(value1.getUnit())) {
                throw new EvaluationException("Arithmetic on values in different units is not yet supported.");
            }
            
            return new DimensionPropertyValue(v1.multiply(v2), value1.getUnit());
        }
    }
    
    private static class DivisionOp extends OpAdapter
    {
        private DimensionPropertyValue value1;

        public DivisionOp(DimensionPropertyValue value1)
        {
            super("DimensionPropertyValue");
            this.value1 = value1;
        }
        
        @Override
        public IPropertyValue op(NumberPropertyValue value2)
        throws EvaluationException
        {
            BigDecimal v1 = value1.getValue();
            BigDecimal v2 = value2.getValue();
            
            return new DimensionPropertyValue(v2.divide(v1), value1.getUnit());
        }
        
        @Override
        public IPropertyValue op(DimensionPropertyValue value2)
        throws EvaluationException
        {
            BigDecimal v1 = value1.getValue();
            BigDecimal v2 = value2.getValue();
            
            if (!value2.getUnit().equals(value1.getUnit())) {
                throw new EvaluationException("Arithmetic on values in different units is not yet supported.");
            }
            
            return new DimensionPropertyValue(v2.divide(v1), value1.getUnit());
        }
    }
    
    private static class EqOp extends OpAdapter
    {
        private DimensionPropertyValue value1;

        public EqOp(DimensionPropertyValue value1)
        {
            super("DimensionPropertyValue");
            this.value1 = value1;
        }
        
        @Override
        public IPropertyValue op(DimensionPropertyValue value2)
        throws EvaluationException
        {
            BigDecimal v1 = value1.getValue();
            BigDecimal v2 = value2.getValue();
            
            if (!value2.getUnit().equals(value1.getUnit())) {
                throw new EvaluationException("Arithmetic on values in different units is not yet supported.");
            }
            
            return new BooleanPropertyValue(v1.equals(v2));
        }
    }
    
    private static class NotEqOp extends OpAdapter
    {
        private DimensionPropertyValue value1;

        public NotEqOp(DimensionPropertyValue value1)
        {
            super("DimensionPropertyValue");
            this.value1 = value1;
        }
        
        @Override
        public IPropertyValue op(DimensionPropertyValue value2)
        throws EvaluationException
        {
            BigDecimal v1 = value1.getValue();
            BigDecimal v2 = value2.getValue();
            
            if (!value2.getUnit().equals(value1.getUnit())) {
                throw new EvaluationException("Arithmetic on values in different units is not yet supported.");
            }
            
            return new BooleanPropertyValue(!v1.equals(v2));
        }
    }
    
    private static class LtOp extends OpAdapter
    {
        private DimensionPropertyValue value1;

        public LtOp(DimensionPropertyValue value1)
        {
            super("DimensionPropertyValue");
            this.value1 = value1;
        }
        
        @Override
        public IPropertyValue op(DimensionPropertyValue value2)
        throws EvaluationException
        {
            BigDecimal v1 = value1.getValue();
            BigDecimal v2 = value2.getValue();
            
            if (!value2.getUnit().equals(value1.getUnit())) {
                throw new EvaluationException("Arithmetic on values in different units is not yet supported.");
            }
            
            return new BooleanPropertyValue(v1.compareTo(v2) > 0);
        }
    }
    
    private static class GtOp extends OpAdapter
    {
        private DimensionPropertyValue value1;

        public GtOp(DimensionPropertyValue value1)
        {
            super("DimensionPropertyValue");
            this.value1 = value1;
        }
        
        @Override
        public IPropertyValue op(DimensionPropertyValue value2)
        throws EvaluationException
        {
            BigDecimal v1 = value1.getValue();
            BigDecimal v2 = value2.getValue();
            
            if (!value2.getUnit().equals(value1.getUnit())) {
                throw new EvaluationException("Arithmetic on values in different units is not yet supported.");
            }
            
            return new BooleanPropertyValue(v1.compareTo(v2) < 0);
        }
    }

    private BigDecimal value;
    private String unit;

    public DimensionPropertyValue()
    {
        super("DimensionPropertyValue");
        this.value = new BigDecimal(0);
    }
    
    public DimensionPropertyValue(BigDecimal v, String unit)
    {
        super("DimensionPropertyValue");
        this.value = v;
        this.unit = unit;
    }
    
    public DimensionPropertyValue(String value)
    {
        super("DimensionPropertyValue");
        if (value.endsWith("px")) {
            this.value = new BigDecimal(value.substring(0, value.length()-2));
            unit = "px";
        }
        else if (value.endsWith("em")) {
            this.value = new BigDecimal(value.substring(0, value.length()-2));
            unit = "em";
        }
        else if (value.endsWith("pt")) {
            this.value = new BigDecimal(value.substring(0, value.length()-2));
            unit = "pt";
        }
        else {
            throw new RuntimeException("Unknown unit: " + value);
        }
    }
    
    public BigDecimal getValue()
    {
        return value;
    }
    
    public String getUnit()
    {
        return unit;
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
        return new DimensionPropertyValue(value.negate(), unit);
    }
    
    @Override
    public IPropertyValue copy()
    {
        return new DimensionPropertyValue(value, unit);
    }
    
    @Override
    public String toString()
    {
        return value.toString() + unit;
    }
}
