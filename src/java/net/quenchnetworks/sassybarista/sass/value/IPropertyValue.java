package net.quenchnetworks.sassybarista.sass.value;

import java.util.*;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.expression.*;
import net.quenchnetworks.sassybarista.sass.value.op.*;

public interface IPropertyValue extends INode
{
    public IPropertyValue evaluate(Map<String, IPropertyValue> context, 
        Map<String, IFunction> functions) throws EvaluationException;

    public IPropertyValue callAddOp(IPropertyValue node) throws EvaluationException;
    public IOp getAdditionOp();
    
    public IPropertyValue callSubOp(IPropertyValue node) throws EvaluationException;
    public IOp getSubtractionOp();
    
    public IPropertyValue callMulOp(IPropertyValue node) throws EvaluationException;
    public IOp getMultiplicationOp();
    
    public IPropertyValue callDivOp(IPropertyValue node) throws EvaluationException;
    public IOp getDivisionOp();
    
    public IPropertyValue negateOp() throws EvaluationException;
    
    public IPropertyValue callEqOp(IPropertyValue node) throws EvaluationException;
    public IOp getEqOp();
    
    public IPropertyValue callNotEqOp(IPropertyValue node) throws EvaluationException;
    public IOp getNotEqOp();
    
    public IPropertyValue callLtOp(IPropertyValue node) throws EvaluationException;
    public IOp getLtOp();
    
    public IPropertyValue callGtOp(IPropertyValue node) throws EvaluationException;
    public IOp getGtOp();
    
    public IPropertyValue copy();
}
