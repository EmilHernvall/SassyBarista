package net.quenchnetworks.sassybarista.sass.expression;

import java.util.*;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.value.*;

public class SubtractionNode extends AbstractNode
{
    public SubtractionNode()
    {
    }
    
    @Override
    public IPropertyValue visit(NodeVisitor visitor)
    throws EvaluationException
    {
        return visitor.visitSubtraction(this);
    }
    
    @Override
    public String toString()
    {
        return getLeftNode() + " - " + getRightNode();
    }
}
