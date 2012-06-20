package net.quenchnetworks.sassybarista.sass.value;

import java.util.*;
import java.math.*;
import java.io.Serializable;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.expression.*;
import net.quenchnetworks.sassybarista.sass.value.op.*;

public class ImportantPropertyValue extends AbstractPropertyValue implements Serializable
{
    public ImportantPropertyValue()
    {
        super("ImportantPropertyValue");
    }
    
    @Override
    public IOp getAdditionOp()
    {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public IOp getEqOp()
    {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public IOp getNotEqOp()
    {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public IPropertyValue callAddOp(IPropertyValue node) 
    throws EvaluationException
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public IPropertyValue callSubOp(IPropertyValue node) 
    throws EvaluationException
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public IPropertyValue callMulOp(IPropertyValue node) 
    throws EvaluationException
    {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public IPropertyValue callDivOp(IPropertyValue node) 
    throws EvaluationException
    {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public IPropertyValue callEqOp(IPropertyValue node) 
    throws EvaluationException
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public IPropertyValue callNotEqOp(IPropertyValue node) 
    throws EvaluationException
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public IPropertyValue callLtOp(IPropertyValue node) 
    throws EvaluationException
    {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public IPropertyValue callGtOp(IPropertyValue node) 
    throws EvaluationException
    {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public IPropertyValue copy()
    {
        return new ImportantPropertyValue();
    }
    
    @Override
    public String toString()
    {
        return "!important";
    }
}
