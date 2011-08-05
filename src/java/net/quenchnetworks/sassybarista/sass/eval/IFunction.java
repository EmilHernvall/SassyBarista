package net.quenchnetworks.sassybarista.sass.eval;

import java.util.*;

import net.quenchnetworks.sassybarista.sass.value.*;

public interface IFunction
{
    public IPropertyValue evaluate(List<IPropertyValue> params)
    throws EvaluationException;
}
