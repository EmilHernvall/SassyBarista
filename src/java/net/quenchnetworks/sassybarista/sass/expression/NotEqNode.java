package net.quenchnetworks.sassybarista.sass.expression;

import java.util.*;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.value.*;

public class NotEqNode extends AbstractNode
{
    public NotEqNode()
    {
    }
    
    @Override
    public IPropertyValue visit(NodeVisitor visitor)
    throws EvaluationException
    {
        return visitor.visitNotEq(this);
    }
    
    @Override
    public INode copy()
    {
        NotEqNode newNode = new NotEqNode();
        newNode.setLeftNode(getLeftNode().copy());
        newNode.setRightNode(getRightNode().copy());
        
        return newNode;
    }
    
    @Override
    public String toString()
    {
        return getLeftNode() + " != " + getRightNode();
    }
}
