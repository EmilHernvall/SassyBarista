package net.quenchnetworks.sassybarista.sass.value.op;

import java.util.*;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.value.*;
import net.quenchnetworks.sassybarista.sass.expression.*;

public interface ISubtractionOp
{
    public IPropertyValue subOp(ColorPropertyValue v) throws EvaluationException;
    public IPropertyValue subOp(DefaultPropertyValue v) throws EvaluationException;
    public IPropertyValue subOp(DimensionPropertyValue v) throws EvaluationException;
    public IPropertyValue subOp(FunctionPropertyValue v) throws EvaluationException;
    public IPropertyValue subOp(NumberPropertyValue v) throws EvaluationException;
    public IPropertyValue subOp(PercentagePropertyValue v) throws EvaluationException;
    public IPropertyValue subOp(StringPropertyValue v) throws EvaluationException;
    public IPropertyValue subOp(URIPropertyValue v) throws EvaluationException;
    public IPropertyValue subOp(VariablePropertyValue v) throws EvaluationException;
}
