package net.quenchnetworks.sassybarista.sass.expression;

import java.util.*;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.value.*;

public class GtNode extends AbstractNode
{
    public GtNode()
    {
    }
    
    @Override
    public IPropertyValue visit(NodeVisitor visitor)
    throws EvaluationException
    {
        return visitor.visitGt(this);
    }
    
    @Override
    public INode copy()
    {
        GtNode newNode = new GtNode();
        newNode.setLeftNode(getLeftNode().copy());
        newNode.setRightNode(getRightNode().copy());
        
        return newNode;
    }
    
    @Override
    public String toString()
    {
        return getLeftNode() + " > " + getRightNode();
    }
}
