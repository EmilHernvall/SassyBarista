package net.quenchnetworks.sassybarista.sass.value.op;

import java.util.*;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.value.*;
import net.quenchnetworks.sassybarista.sass.expression.*;

public abstract class SubtractionOpAdapter implements ISubtractionOp
{
    public IPropertyValue subOp(ColorPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("ColorPropertyValue is unsupported.");
    }
    
    public IPropertyValue subOp(DefaultPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("DefaultPropertyValue is unsupported.");
    }
    
    public IPropertyValue subOp(DimensionPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("DimensionPropertyValue is unsupported.");
    }
    
    public IPropertyValue subOp(FunctionPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("FunctionPropertyValue is unsupported.");
    }
    
    public IPropertyValue subOp(NumberPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("NumberPropertyValue is unsupported.");
    }
    
    public IPropertyValue subOp(PercentagePropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("PercentagePropertyValue is unsupported.");
    }
    
    public IPropertyValue subOp(StringPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("StringPropertyValue is unsupported.");
    }
    
    public IPropertyValue subOp(URIPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("URIPropertyValue is unsupported.");
    }
    
    public IPropertyValue subOp(VariablePropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("VariablePropertyValue is unsupported.");
    }
}
