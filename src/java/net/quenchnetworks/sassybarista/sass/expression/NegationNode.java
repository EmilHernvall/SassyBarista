package net.quenchnetworks.sassybarista.sass.expression;

import java.util.*;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.value.*;

public class NegationNode implements INode, java.io.Serializable
{
    private INode node;
    protected int line = 0;
    protected int col = 0;

    public NegationNode()
    {
    }
    
    public NegationNode(INode child)
    {
        this.node = child;
    }
    
    public INode getNode()
    {
        return node;
    }
    
    public void setNode(INode node)
    {
        this.node = node;
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
    public IPropertyValue visit(NodeVisitor visitor)
    throws EvaluationException
    {
        return visitor.visitNegatation(this);
    }
    
    @Override
    public INode copy()
    {
        NegationNode newNode = new NegationNode();
        newNode.setNode(getNode().copy());
        
        return newNode;
    }
    
    @Override
    public String toString()
    {
        return getNode().toString();
    }
}
