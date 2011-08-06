package net.quenchnetworks.sassybarista.sass.expression;

public abstract class AbstractNode implements IBinaryNode, java.io.Serializable
{
    protected INode left;
    protected INode right;
    protected int line = 0;
    protected int col = 0;

    public AbstractNode()
    {
    
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

    public void setLeftNode(INode node) { this.left = node; }
    public INode getLeftNode() { return left; }
    
    public void setRightNode(INode node) { this.right = node; }
    public INode getRightNode() { return right; }
}
