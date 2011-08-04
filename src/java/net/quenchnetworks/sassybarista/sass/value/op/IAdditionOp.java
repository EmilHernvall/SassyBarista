package net.quenchnetworks.sassybarista.sass.value.op;

import java.util.*;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.value.*;
import net.quenchnetworks.sassybarista.sass.expression.*;

public interface IAdditionOp
{
    public IPropertyValue addOp(ColorPropertyValue v) throws EvaluationException;
    public IPropertyValue addOp(DefaultPropertyValue v) throws EvaluationException;
    public IPropertyValue addOp(DimensionPropertyValue v) throws EvaluationException;
    public IPropertyValue addOp(FunctionPropertyValue v) throws EvaluationException;
    public IPropertyValue addOp(NumberPropertyValue v) throws EvaluationException;
    public IPropertyValue addOp(PercentagePropertyValue v) throws EvaluationException;
    public IPropertyValue addOp(StringPropertyValue v) throws EvaluationException;
    public IPropertyValue addOp(URIPropertyValue v) throws EvaluationException;
    public IPropertyValue addOp(VariablePropertyValue v) throws EvaluationException;
}
