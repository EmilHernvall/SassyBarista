package net.quenchnetworks.sassybarista.sass.value;

import java.util.*;
import java.io.Serializable;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.expression.*;
import net.quenchnetworks.sassybarista.sass.value.op.*;

public abstract class AbstractPropertyValue implements IPropertyValue
{
    @Override
    public IPropertyValue evaluate(Map<String, IPropertyValue> context)
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
    public IAdditionOp getAdditionOp()
    {
        return new AdditionOpAdapter() {};
    }
    
    @Override
    public ISubtractionOp getSubtractionOp()
    {
        return new SubtractionOpAdapter() {};
    }
    
    @Override
    public IMultiplicationOp getMultiplicationOp()
    {
        return new MultiplicationOpAdapter() {};
    }
    
    @Override
    public IDivisionOp getDivisionOp()
    {
        return new DivisionOpAdapter() {};
    }
}
