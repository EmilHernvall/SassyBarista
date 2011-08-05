package net.quenchnetworks.sassybarista.sass.expression;

import java.util.*;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.value.*;

public class MultiplicationNode extends AbstractNode
{
    public MultiplicationNode()
    {
    }
    
    @Override
    public IPropertyValue visit(NodeVisitor visitor)
    throws EvaluationException
    {
        return visitor.visitMultiplication(this);
    }
    
    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        if (getLeftNode() instanceof IPropertyValue) {
            result.append(getLeftNode());
        } else {
            result.append("(");
            result.append(getLeftNode());
            result.append(")");
        }
        
        result.append("*");
        
        if (getRightNode() instanceof IPropertyValue) {
            result.append(getRightNode());
        } else {
            result.append("(");
            result.append(getRightNode());
            result.append(")");
        }
        
        return result.toString();
    }
}
