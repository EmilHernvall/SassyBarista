package net.quenchnetworks.sassybarista.sass.value.op;

import java.util.*;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.value.*;
import net.quenchnetworks.sassybarista.sass.expression.*;

public interface IEqOp
{
    public IPropertyValue eqOp(ColorPropertyValue v) throws EvaluationException;
    public IPropertyValue eqOp(BooleanPropertyValue v) throws EvaluationException;
    public IPropertyValue eqOp(DefaultPropertyValue v) throws EvaluationException;
    public IPropertyValue eqOp(DimensionPropertyValue v) throws EvaluationException;
    public IPropertyValue eqOp(FunctionPropertyValue v) throws EvaluationException;
    public IPropertyValue eqOp(NumberPropertyValue v) throws EvaluationException;
    public IPropertyValue eqOp(PercentagePropertyValue v) throws EvaluationException;
    public IPropertyValue eqOp(StringPropertyValue v) throws EvaluationException;
    public IPropertyValue eqOp(URIPropertyValue v) throws EvaluationException;
    public IPropertyValue eqOp(VariablePropertyValue v) throws EvaluationException;
}
