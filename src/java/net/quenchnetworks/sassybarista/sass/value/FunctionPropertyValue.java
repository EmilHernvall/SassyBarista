package net.quenchnetworks.sassybarista.sass.value;

import java.util.*;
import java.io.Serializable;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.expression.*;
import net.quenchnetworks.sassybarista.sass.value.op.*;

public class FunctionPropertyValue extends AbstractPropertyValue implements Serializable
{
    private String name;
    private List<IPropertyValue> values;

    public FunctionPropertyValue()
    {
        super("FunctionPropertyValue");
        this.name = null;
        this.values = new ArrayList<IPropertyValue>();
    }
    
    public FunctionPropertyValue(String name)
    {
        super("FunctionPropertyValue");
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
    public IPropertyValue evaluate(Map<String, IPropertyValue> context, 
        Map<String, IFunction> functions)
    throws EvaluationException
    {
        List<IPropertyValue> newValues = new ArrayList<IPropertyValue>();
        for (IPropertyValue value : values) {
            IPropertyValue newValue = value.evaluate(context, functions);
            newValues.add(newValue);
        }
        
        this.values = newValues;
    
        IFunction func = functions.get(name);
        
        // functions that are not found are rendered as usual
        if (func == null) {
            return this;
        }
    
        IPropertyValue newValue = func.evaluate(values);
        return newValue;
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
        FunctionPropertyValue newValue = new FunctionPropertyValue(name);
        for (IPropertyValue value : values) {
            newValue.addValue(value.copy());
        }
        
        return newValue;
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
