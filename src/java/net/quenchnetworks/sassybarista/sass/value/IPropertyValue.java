package net.quenchnetworks.sassybarista.sass.value;

import java.util.*;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.expression.*;
import net.quenchnetworks.sassybarista.sass.value.op.*;

public interface IPropertyValue extends INode
{
    public IPropertyValue evaluate(Map<String, IPropertyValue> context) throws EvaluationException;

    public IPropertyValue callAddOp(IPropertyValue node) throws EvaluationException;
    public IAdditionOp getAdditionOp();
    
    public IPropertyValue callSubOp(IPropertyValue node) throws EvaluationException;
    public ISubtractionOp getSubtractionOp();
    
    public IPropertyValue callMulOp(IPropertyValue node) throws EvaluationException;
    public IMultiplicationOp getMultiplicationOp();
    
    public IPropertyValue callDivOp(IPropertyValue node) throws EvaluationException;
    public IDivisionOp getDivisionOp();
    
    public IPropertyValue negateOp() throws EvaluationException;
}
