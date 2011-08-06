package net.quenchnetworks.sassybarista.sass.value.op;

import java.util.*;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.value.*;
import net.quenchnetworks.sassybarista.sass.expression.*;

public class OpAdapter implements IOp
{
    private String name;

    public OpAdapter(String name)
    {
        this.name = name;
    }

    public IPropertyValue op(ColorPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("ColorPropertyValue is unsupported by " + name + ": " + v + ".");
    }
    
    public IPropertyValue op(BooleanPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("BooleanPropertyValue is unsupported by " + name + ": " + v + ".");
    }
    
    public IPropertyValue op(DefaultPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("DefaultPropertyValue is unsupported by " + name + ": " + v + ".");
    }
    
    public IPropertyValue op(DimensionPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("DimensionPropertyValue is unsupported by " + name + ": " + v + ".");
    }
    
    public IPropertyValue op(FunctionPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("FunctionPropertyValue is unsupported by " + name + ": " + v + ".");
    }
    
    public IPropertyValue op(NumberPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("NumberPropertyValue is unsupported by " + name + ": " + v + ".");
    }
    
    public IPropertyValue op(PercentagePropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("PercentagePropertyValue is unsupported by " + name + ": " + v + ".");
    }
    
    public IPropertyValue op(StringPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("StringPropertyValue is unsupported by " + name + ": " + v + ".");
    }
    
    public IPropertyValue op(URIPropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("URIPropertyValue is unsupported by " + name + ": " + v + ".");
    }
    
    public IPropertyValue op(VariablePropertyValue v)
    throws EvaluationException
    {
        throw new EvaluationException("VariablePropertyValue is unsupported by " + name + ": " + v + ".");
    }
}
