package net.quenchnetworks.sassybarista.sass.models;

import java.io.Serializable;
import java.util.*;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.expression.*;
import net.quenchnetworks.sassybarista.sass.value.*;

public class IfStatement extends ControlStatement implements Serializable
{
    public IfStatement()
    {
        super();
    }
    
    public void evaluate(Block parent, ExpressionEvaluator evaluator, Map<String, IPropertyValue> context)
    throws EvaluationException
    {
        IPropertyValue result = evaluator.evaluate(expr, context);
        if (!(result instanceof BooleanPropertyValue)) {
            throw new EvaluationException("Expression did not return a boolean.");
        }
        
        boolean value = ((BooleanPropertyValue)result).getValue();
        
        if (value) {
            parent.addProperties(getProperties());
        }
    }
    
    public IfStatement copy()
    {
        IfStatement stmt = new IfStatement();
        stmt.expr = expr;
        
        // copies sub rules and properites
        super.copy(stmt);
        
        return stmt;
    }
}
