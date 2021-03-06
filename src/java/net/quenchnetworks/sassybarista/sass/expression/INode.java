package net.quenchnetworks.sassybarista.sass.expression;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.value.*;

public interface INode extends java.io.Serializable
{
    public void setLocation(int line, int col);
    public int getLine();
    public int getColumn();
    
    public IPropertyValue visit(NodeVisitor visitor) throws EvaluationException;
    public INode copy();
}
