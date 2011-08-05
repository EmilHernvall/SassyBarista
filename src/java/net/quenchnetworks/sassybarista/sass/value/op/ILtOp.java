package net.quenchnetworks.sassybarista.sass.value.op;

import java.util.*;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.value.*;
import net.quenchnetworks.sassybarista.sass.expression.*;

public interface ILtOp
{
    public IPropertyValue ltOp(ColorPropertyValue v) throws EvaluationException;
    public IPropertyValue ltOp(BooleanPropertyValue v) throws EvaluationException;
    public IPropertyValue ltOp(DefaultPropertyValue v) throws EvaluationException;
    public IPropertyValue ltOp(DimensionPropertyValue v) throws EvaluationException;
    public IPropertyValue ltOp(FunctionPropertyValue v) throws EvaluationException;
    public IPropertyValue ltOp(NumberPropertyValue v) throws EvaluationException;
    public IPropertyValue ltOp(PercentagePropertyValue v) throws EvaluationException;
    public IPropertyValue ltOp(StringPropertyValue v) throws EvaluationException;
    public IPropertyValue ltOp(URIPropertyValue v) throws EvaluationException;
    public IPropertyValue ltOp(VariablePropertyValue v) throws EvaluationException;
}
