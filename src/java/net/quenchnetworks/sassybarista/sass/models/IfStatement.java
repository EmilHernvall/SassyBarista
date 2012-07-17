package net.quenchnetworks.sassybarista.sass.models;

import java.io.Serializable;
import java.util.*;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.expression.*;
import net.quenchnetworks.sassybarista.sass.value.*;

public class IfStatement extends ControlStatement implements Serializable
{
    private List<IfStatement> elseIfs;
    private Block elseBlock = null;

    public IfStatement()
    {
        super();
        this.elseIfs = new ArrayList<IfStatement>();
    }

    public void addElseIf(IfStatement v) { elseIfs.add(v); }
    public void setElseBlock(Block v) { this.elseBlock = v; }
    
    public boolean evaluate(Block parent, ExpressionEvaluator evaluator, Map<String, IPropertyValue> context)
    throws EvaluationException
    {
        IPropertyValue result = evaluator.evaluate(expr, context);
        if (!(result instanceof BooleanPropertyValue)) {
            throw new EvaluationException("Expression did not return a boolean.");
        }
        
        boolean value = ((BooleanPropertyValue)result).getValue();
        
        if (value) {
            parent.addProperties(getProperties());
            return true;
        }

        for (IfStatement elseIf : elseIfs) {
            if (elseIf.evaluate(parent, evaluator, context)) {
                return false;
            }
        }

        if (elseBlock != null) {
            parent.addProperties(elseBlock.getProperties());
        }

        return false;
    }
    
    public IfStatement copy()
    {
        IfStatement stmt = new IfStatement();
        stmt.expr = expr.copy();
        for (IfStatement elseIf : elseIfs) {
            stmt.addElseIf(elseIf.copy());
        }
        if (elseBlock != null) {
            stmt.elseBlock = new Block();
            elseBlock.copy(stmt.elseBlock);
        }
        
        // copies sub rules and properites
        super.copy(stmt);
        
        return stmt;
    }
}
