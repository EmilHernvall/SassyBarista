package net.quenchnetworks.sassybarista.sass.value.op;

import java.util.*;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.value.*;
import net.quenchnetworks.sassybarista.sass.expression.*;

public interface IGtOp
{
    public IPropertyValue gtOp(ColorPropertyValue v) throws EvaluationException;
    public IPropertyValue gtOp(BooleanPropertyValue v) throws EvaluationException;
    public IPropertyValue gtOp(DefaultPropertyValue v) throws EvaluationException;
    public IPropertyValue gtOp(DimensionPropertyValue v) throws EvaluationException;
    public IPropertyValue gtOp(FunctionPropertyValue v) throws EvaluationException;
    public IPropertyValue gtOp(NumberPropertyValue v) throws EvaluationException;
    public IPropertyValue gtOp(PercentagePropertyValue v) throws EvaluationException;
    public IPropertyValue gtOp(StringPropertyValue v) throws EvaluationException;
    public IPropertyValue gtOp(URIPropertyValue v) throws EvaluationException;
    public IPropertyValue gtOp(VariablePropertyValue v) throws EvaluationException;
}
