package net.quenchnetworks.sassybarista.sass.expression;

import java.util.*;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.value.*;

public class LtNode extends AbstractNode
{
    public LtNode()
    {
    }
    
    @Override
    public IPropertyValue visit(NodeVisitor visitor)
    throws EvaluationException
    {
        return visitor.visitLt(this);
    }
    
    @Override
    public INode copy()
    {
        LtNode newNode = new LtNode();
        newNode.setLeftNode(getLeftNode().copy());
        newNode.setRightNode(getRightNode().copy());
        
        return newNode;
    }
    
    @Override
    public String toString()
    {
        return getLeftNode() + " < " + getRightNode();
    }
}
