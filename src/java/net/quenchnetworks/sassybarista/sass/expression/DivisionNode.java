package net.quenchnetworks.sassybarista.sass.expression;

import java.util.*;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.value.*;

public class DivisionNode extends AbstractNode
{
    public DivisionNode()
    {
    }
    
    @Override
    public IPropertyValue visit(NodeVisitor visitor)
    throws EvaluationException
    {
        return visitor.visitDivision(this);
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
        
        result.append("/");
        
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
