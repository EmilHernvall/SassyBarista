package net.quenchnetworks.sassybarista.sass.value.op;

import java.util.*;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.value.*;
import net.quenchnetworks.sassybarista.sass.expression.*;

public interface IDivisionOp
{
    public IPropertyValue divOp(ColorPropertyValue v) throws EvaluationException;
    public IPropertyValue divOp(BooleanPropertyValue v) throws EvaluationException;
    public IPropertyValue divOp(DefaultPropertyValue v) throws EvaluationException;
    public IPropertyValue divOp(DimensionPropertyValue v) throws EvaluationException;
    public IPropertyValue divOp(FunctionPropertyValue v) throws EvaluationException;
    public IPropertyValue divOp(NumberPropertyValue v) throws EvaluationException;
    public IPropertyValue divOp(PercentagePropertyValue v) throws EvaluationException;
    public IPropertyValue divOp(StringPropertyValue v) throws EvaluationException;
    public IPropertyValue divOp(URIPropertyValue v) throws EvaluationException;
    public IPropertyValue divOp(VariablePropertyValue v) throws EvaluationException;
}
