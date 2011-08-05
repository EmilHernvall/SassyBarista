package net.quenchnetworks.sassybarista.sass.value.op;

import java.util.*;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.value.*;
import net.quenchnetworks.sassybarista.sass.expression.*;

public abstract class EqOpAdapter implements IEqOp
{
    public IPropertyValue eqOp(ColorPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("ColorPropertyValue is unsupported.");
    }
    
    public IPropertyValue eqOp(BooleanPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("BooleanPropertyValue is unsupported.");
    }
    
    public IPropertyValue eqOp(DefaultPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("DefaultPropertyValue is unsupported.");
    }
    
    public IPropertyValue eqOp(DimensionPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("DimensionPropertyValue is unsupported.");
    }
    
    public IPropertyValue eqOp(FunctionPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("FunctionPropertyValue is unsupported.");
    }
    
    public IPropertyValue eqOp(NumberPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("NumberPropertyValue is unsupported.");
    }
    
    public IPropertyValue eqOp(PercentagePropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("PercentagePropertyValue is unsupported.");
    }
    
    public IPropertyValue eqOp(StringPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("StringPropertyValue is unsupported.");
    }
    
    public IPropertyValue eqOp(URIPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("URIPropertyValue is unsupported.");
    }
    
    public IPropertyValue eqOp(VariablePropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("VariablePropertyValue is unsupported.");
    }
}
