package net.quenchnetworks.sassybarista.sass.expression;

import java.util.*;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.value.*;

public class ExpressionEvaluator implements NodeVisitor
{
    private Map<String, IFunction> functions;
    private Map<String, IPropertyValue> context;

    public ExpressionEvaluator(Map<String, IFunction> functions)
    {
        this.functions = functions;
    }
    
    public IPropertyValue evaluate(INode node, Map<String, IPropertyValue> context)
    throws EvaluationException
    {
        this.context = context;
        return node.visit(this);
    }
    
    @Override
    public IPropertyValue visitAddition(AdditionNode node)
    throws EvaluationException
    {
        INode left = node.getLeftNode();
        INode right = node.getRightNode();
        
        IPropertyValue val1, val2;
        
        val1 = left.visit(this);
        val2 = right.visit(this);

        IPropertyValue newValue = val1.callAddOp(val2);
        
        return newValue;
    }
    
    @Override
    public IPropertyValue visitSubtraction(SubtractionNode node)
    throws EvaluationException
    {
        INode left = node.getLeftNode();
        INode right = node.getRightNode();
        
        IPropertyValue val1, val2;
        
        val1 = left.visit(this);
        val2 = right.visit(this);
        
        IPropertyValue newValue = val1.callSubOp(val2);
        
        return newValue;
    }
    
    @Override
    public IPropertyValue visitMultiplication(MultiplicationNode node)
    throws EvaluationException
    {
        INode left = node.getLeftNode();
        INode right = node.getRightNode();
        
        IPropertyValue val1, val2;
        
        val1 = left.visit(this);
        val2 = right.visit(this);
        
        IPropertyValue newValue = val1.callMulOp(val2);
        
        return newValue;
    }
    
    @Override
    public IPropertyValue visitDivision(DivisionNode node)
    throws EvaluationException
    {
        INode left = node.getLeftNode();
        INode right = node.getRightNode();
        
        IPropertyValue val1, val2;
        
        val1 = left.visit(this);
        val2 = right.visit(this);
        
        IPropertyValue newValue = val1.callDivOp(val2);
        
        return newValue;
    }
    
    @Override
    public IPropertyValue visitNegatation(NegationNode node) 
    throws EvaluationException
    {
        INode child = node.getNode();
        IPropertyValue val = child.visit(this);
        
        return val.negateOp();
    }
    
    @Override
    public IPropertyValue visitValue(IPropertyValue node)
    throws EvaluationException
    {
        return node.evaluate(context, functions);
    }
}
