package net.quenchnetworks.sassybarista.sass.value.op;

import java.util.*;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.value.*;
import net.quenchnetworks.sassybarista.sass.expression.*;

public interface IOp
{
    public IPropertyValue op(ColorPropertyValue v) throws EvaluationException;
    public IPropertyValue op(BooleanPropertyValue v) throws EvaluationException;
    public IPropertyValue op(DefaultPropertyValue v) throws EvaluationException;
    public IPropertyValue op(DimensionPropertyValue v) throws EvaluationException;
    public IPropertyValue op(FunctionPropertyValue v) throws EvaluationException;
    public IPropertyValue op(NumberPropertyValue v) throws EvaluationException;
    public IPropertyValue op(PercentagePropertyValue v) throws EvaluationException;
    public IPropertyValue op(StringPropertyValue v) throws EvaluationException;
    public IPropertyValue op(URIPropertyValue v) throws EvaluationException;
    public IPropertyValue op(VariablePropertyValue v) throws EvaluationException;
}
