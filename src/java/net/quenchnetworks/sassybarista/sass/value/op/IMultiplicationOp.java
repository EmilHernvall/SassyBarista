package net.quenchnetworks.sassybarista.sass.value.op;

import java.util.*;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.value.*;
import net.quenchnetworks.sassybarista.sass.expression.*;

public interface IMultiplicationOp
{
    public IPropertyValue mulOp(ColorPropertyValue v) throws EvaluationException;
    public IPropertyValue mulOp(BooleanPropertyValue v) throws EvaluationException;
    public IPropertyValue mulOp(DefaultPropertyValue v) throws EvaluationException;
    public IPropertyValue mulOp(DimensionPropertyValue v) throws EvaluationException;
    public IPropertyValue mulOp(FunctionPropertyValue v) throws EvaluationException;
    public IPropertyValue mulOp(NumberPropertyValue v) throws EvaluationException;
    public IPropertyValue mulOp(PercentagePropertyValue v) throws EvaluationException;
    public IPropertyValue mulOp(StringPropertyValue v) throws EvaluationException;
    public IPropertyValue mulOp(URIPropertyValue v) throws EvaluationException;
    public IPropertyValue mulOp(VariablePropertyValue v) throws EvaluationException;
}
