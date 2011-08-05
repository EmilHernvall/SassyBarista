package net.quenchnetworks.sassybarista.sass.value.op;

import java.util.*;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.value.*;
import net.quenchnetworks.sassybarista.sass.expression.*;

public interface INotEqOp
{
    public IPropertyValue notEqOp(ColorPropertyValue v) throws EvaluationException;
    public IPropertyValue notEqOp(BooleanPropertyValue v) throws EvaluationException;
    public IPropertyValue notEqOp(DefaultPropertyValue v) throws EvaluationException;
    public IPropertyValue notEqOp(DimensionPropertyValue v) throws EvaluationException;
    public IPropertyValue notEqOp(FunctionPropertyValue v) throws EvaluationException;
    public IPropertyValue notEqOp(NumberPropertyValue v) throws EvaluationException;
    public IPropertyValue notEqOp(PercentagePropertyValue v) throws EvaluationException;
    public IPropertyValue notEqOp(StringPropertyValue v) throws EvaluationException;
    public IPropertyValue notEqOp(URIPropertyValue v) throws EvaluationException;
    public IPropertyValue notEqOp(VariablePropertyValue v) throws EvaluationException;
}
