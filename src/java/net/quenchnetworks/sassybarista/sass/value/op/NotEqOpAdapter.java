package net.quenchnetworks.sassybarista.sass.value.op;

import java.util.*;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.value.*;
import net.quenchnetworks.sassybarista.sass.expression.*;

public abstract class NotEqOpAdapter implements INotEqOp
{
    public IPropertyValue notEqOp(ColorPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("ColorPropertyValue is unsupported.");
    }
    
    public IPropertyValue notEqOp(BooleanPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("BooleanPropertyValue is unsupported.");
    }
    
    public IPropertyValue notEqOp(DefaultPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("DefaultPropertyValue is unsupported.");
    }
    
    public IPropertyValue notEqOp(DimensionPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("DimensionPropertyValue is unsupported.");
    }
    
    public IPropertyValue notEqOp(FunctionPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("FunctionPropertyValue is unsupported.");
    }
    
    public IPropertyValue notEqOp(NumberPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("NumberPropertyValue is unsupported.");
    }
    
    public IPropertyValue notEqOp(PercentagePropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("PercentagePropertyValue is unsupported.");
    }
    
    public IPropertyValue notEqOp(StringPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("StringPropertyValue is unsupported.");
    }
    
    public IPropertyValue notEqOp(URIPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("URIPropertyValue is unsupported.");
    }
    
    public IPropertyValue notEqOp(VariablePropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("VariablePropertyValue is unsupported.");
    }
}
