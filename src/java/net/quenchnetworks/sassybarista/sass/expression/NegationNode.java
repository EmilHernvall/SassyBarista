package net.quenchnetworks.sassybarista.sass.expression;

import java.util.*;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.value.*;

public class NegationNode implements INode
{
    private INode node;

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
    public IPropertyValue visit(NodeVisitor visitor)
    throws EvaluationException
    {
        return visitor.visitNegatation(this);
    }
    
    @Override
    public String toString()
    {
        return getNode().toString();
    }
}
