package net.quenchnetworks.sassybarista.sass.expression;

import java.util.*;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.value.*;

public interface IBinaryNode extends INode
{
    public void setLeftNode(INode node);
    public INode getLeftNode();
    
    public void setRightNode(INode node);
    public INode getRightNode();
}
