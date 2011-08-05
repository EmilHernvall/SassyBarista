package net.quenchnetworks.sassybarista.sass.value;

import java.util.*;
import java.math.*;
import java.io.Serializable;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.expression.*;
import net.quenchnetworks.sassybarista.sass.value.op.*;

public class DefaultPropertyValue extends AbstractPropertyValue implements Serializable
{
    private static class AdditionOp extends AdditionOpAdapter
    {
        private DefaultPropertyValue value1;

        public AdditionOp(DefaultPropertyValue value1)
        {
            this.value1 = value1;
        }
        
        @Override
        public IPropertyValue addOp(DefaultPropertyValue value2)
        throws EvaluationException
        {
            String v1 = value1.getValue();
            String v2 = value2.getValue();
            
            return new DefaultPropertyValue(v2 + " " + v1);
        }
        
        @Override
        public IPropertyValue addOp(StringPropertyValue value2)
        throws EvaluationException
        {
            String v1 = value1.getValue();
            String v2 = value2.getValue();
            
            return new StringPropertyValue(v2 + v1, value2.getQuoteType());
        }
        
        @Override
        public IPropertyValue addOp(NumberPropertyValue value2)
        throws EvaluationException
        {
            String v1 = value1.getValue();
            if ("px".equals(v1)) {
            } else if ("pt".equals(v1)) {
            } else if ("em".equals(v1)) {
            }  else {
                throw new EvaluationException("Only units can be added to numbers");
            }
            
            BigDecimal v2 = value2.getValue();
            
            return new DimensionPropertyValue(v2, v1);
        }
    }

    private String value;

    public DefaultPropertyValue()
    {
        this.value = null;
    }
    
    public DefaultPropertyValue(String value)
    {
        this.value = value;
    }
    
    public String getValue()
    {
        return value;
    }
    
    @Override
    public IAdditionOp getAdditionOp()
    {
        return new AdditionOp(this);
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
        return new DefaultPropertyValue(value);
    }
    
    @Override
    public String toString()
    {
        return value;
    }
}
