package net.quenchnetworks.sassybarista.sass.value.op;

import java.util.*;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.value.*;
import net.quenchnetworks.sassybarista.sass.expression.*;

public abstract class GtOpAdapter implements IGtOp
{
    public IPropertyValue gtOp(ColorPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("ColorPropertyValue is unsupported.");
    }
    
    public IPropertyValue gtOp(BooleanPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("BooleanPropertyValue is unsupported.");
    }
    
    public IPropertyValue gtOp(DefaultPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("DefaultPropertyValue is unsupported.");
    }
    
    public IPropertyValue gtOp(DimensionPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("DimensionPropertyValue is unsupported.");
    }
    
    public IPropertyValue gtOp(FunctionPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("FunctionPropertyValue is unsupported.");
    }
    
    public IPropertyValue gtOp(NumberPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("NumberPropertyValue is unsupported.");
    }
    
    public IPropertyValue gtOp(PercentagePropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("PercentagePropertyValue is unsupported.");
    }
    
    public IPropertyValue gtOp(StringPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("StringPropertyValue is unsupported.");
    }
    
    public IPropertyValue gtOp(URIPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("URIPropertyValue is unsupported.");
    }
    
    public IPropertyValue gtOp(VariablePropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("VariablePropertyValue is unsupported.");
    }
}