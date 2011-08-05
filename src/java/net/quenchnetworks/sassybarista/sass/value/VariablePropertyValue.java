package net.quenchnetworks.sassybarista.sass.value;

import java.util.*;
import java.io.Serializable;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.expression.*;
import net.quenchnetworks.sassybarista.sass.value.op.*;

public class VariablePropertyValue extends AbstractPropertyValue implements Serializable
{
    private String variable;
    
    public VariablePropertyValue()
    {
        this.variable = null;
    }

    public VariablePropertyValue(String variable)
    {
        this.variable = variable;
    }
    
    @Override
    public IPropertyValue evaluate(Map<String, IPropertyValue> context, 
        Map<String, IFunction> functions)
    throws EvaluationException
    {
        IPropertyValue newValue = context.get(variable);
        if (newValue == null) {
            throw new EvaluationException("Variable " + variable + " is not available in current context.");
        }
    
        return newValue;
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
    public IPropertyValue copy()
    {
        return new VariablePropertyValue(variable);
    }
    
    @Override
    public String toString()
    {
        return variable;
    }
}
