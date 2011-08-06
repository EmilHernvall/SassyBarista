package net.quenchnetworks.sassybarista.sass.value;

import java.util.*;
import java.io.Serializable;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.expression.*;
import net.quenchnetworks.sassybarista.sass.value.op.*;

public abstract class AbstractPropertyValue implements IPropertyValue
{
    private String name;
    private int line = 0, col = 0;
    
    protected AbstractPropertyValue(String name)
    {
        this.name = name;
    }

    @Override
    public void setLocation(int line, int col)
    {
        this.line = line;
        this.col = col;
    }
    
    @Override
    public int getLine()
    {
        return line;
    }
    
    @Override
    public int getColumn()
    {
        return col;
    }

    @Override
    public IPropertyValue evaluate(Map<String, IPropertyValue> context, 
        Map<String, IFunction> functions)
    throws EvaluationException
    {
        return this;
    }

    @Override
    public IPropertyValue visit(NodeVisitor visitor)
    throws EvaluationException
    {
        return visitor.visitValue(this);
    }
    
    @Override
    public IOp getAdditionOp()
    {
        return new OpAdapter(name);
    }
    
    @Override
    public IOp getSubtractionOp()
    {
        return new OpAdapter(name);
    }
    
    @Override
    public IOp getMultiplicationOp()
    {
        return new OpAdapter(name);
    }
    
    @Override
    public IOp getDivisionOp()
    {
        return new OpAdapter(name);
    }
    
    @Override
    public IOp getEqOp()
    {
        return new OpAdapter(name);
    }
    
    @Override
    public IOp getNotEqOp()
    {
        return new OpAdapter(name);
    }
    
    @Override
    public IOp getLtOp()
    {
        return new OpAdapter(name);
    }
    
    @Override
    public IOp getGtOp()
    {
        return new OpAdapter(name);
    }
    
    @Override
    public IPropertyValue negateOp() 
    throws EvaluationException
    {
        throw new EvaluationException("Negation is not supported by " + name + ".");
    }
}
