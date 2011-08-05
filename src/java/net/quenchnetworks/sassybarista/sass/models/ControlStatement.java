package net.quenchnetworks.sassybarista.sass.models;

import java.io.Serializable;
import java.util.*;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.expression.*;
import net.quenchnetworks.sassybarista.sass.value.*;

public abstract class ControlStatement extends Block implements Serializable
{
    protected INode expr;

    public ControlStatement()
    {
        super();
    }
    
    public abstract void evaluate(Block parent, ExpressionEvaluator evaluator, Map<String, IPropertyValue> context)
    throws EvaluationException;
    
    public void setExpression(INode v)
    {
        this.expr = v;
    }
    
    public INode getExpression()
    {
        return expr;
    }
}
