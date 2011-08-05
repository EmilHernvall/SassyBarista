package net.quenchnetworks.sassybarista.sass.value;

import java.util.*;
import java.io.Serializable;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.expression.*;
import net.quenchnetworks.sassybarista.sass.value.op.*;

public abstract class AbstractPropertyValue implements IPropertyValue
{
    @Override
    public IPropertyValue evaluate(Map<String, IPropertyValue> context, 
        Map<String, IFunction> functions)
    throws EvaluationException
    {
        return this;
    }

    @Override
    public IPropertyValue visit(NodeVisitor visitor)
    throws EvaluationException
    {
        return visitor.visitValue(this);
    }
    
    @Override
    public IOp getAdditionOp()
    {
        return new OpAdapter() {};
    }
    
    @Override
    public IOp getSubtractionOp()
    {
        return new OpAdapter() {};
    }
    
    @Override
    public IOp getMultiplicationOp()
    {
        return new OpAdapter() {};
    }
    
    @Override
    public IOp getDivisionOp()
    {
        return new OpAdapter() {};
    }
    
    @Override
    public IOp getEqOp()
    {
        return new OpAdapter() {};
    }
    
    @Override
    public IOp getNotEqOp()
    {
        return new OpAdapter() {};
    }
    
    @Override
    public IOp getLtOp()
    {
        return new OpAdapter() {};
    }
    
    @Override
    public IOp getGtOp()
    {
        return new OpAdapter() {};
    }
    
    @Override
    public IPropertyValue negateOp() 
    throws EvaluationException
    {
        throw new EvaluationException("Negation is not supported for this node.");
    }
}
