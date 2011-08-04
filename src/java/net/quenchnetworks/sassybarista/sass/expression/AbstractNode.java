package net.quenchnetworks.sassybarista.sass.expression;

public abstract class AbstractNode implements IBinaryNode
{
    private INode left;
    private INode right;

    public AbstractNode()
    {
    
    }

    public void setLeftNode(INode node) { this.left = node; }
    public INode getLeftNode() { return left; }
    
    public void setRightNode(INode node) { this.right = node; }
    public INode getRightNode() { return right; }
}
