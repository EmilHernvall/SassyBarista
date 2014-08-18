package net.quenchnetworks.sassybarista.sass.value;

import java.util.*;
import java.io.Serializable;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.expression.*;
import net.quenchnetworks.sassybarista.sass.value.op.*;

public class FunctionPropertyValue extends AbstractPropertyValue implements Serializable
{
    private String name;
    private List<INode> values;

    public FunctionPropertyValue()
    {
        super("FunctionPropertyValue");
        this.name = null;
        this.values = new ArrayList<INode>();
    }

    public FunctionPropertyValue(String name)
    {
        super("FunctionPropertyValue");
        this.name = name;
        this.values = new ArrayList<INode>();
    }

    public void addValue(INode value)
    {
        values.add(value);
    }

    @Override
    public IPropertyValue evaluate(Map<String, IPropertyValue> context,
        Map<String, IFunction> functions)
    throws EvaluationException
    {
        ExpressionEvaluator evaluator = new ExpressionEvaluator(functions);
        List<IPropertyValue> newValues = new ArrayList<IPropertyValue>();
        for (INode value : values) {
            IPropertyValue newValue = evaluator.evaluate(value, context);
            newValues.add(newValue);
        }

        values.clear();
        values.addAll(newValues);

        IFunction func = functions.get(name);

        // functions that are not found are rendered as usual
        if (func == null) {
            return this;
        }

        IPropertyValue newValue = func.evaluate(newValues);
        return newValue;
    }

    @Override
    public IPropertyValue callAddOp(IPropertyValue node)
    throws EvaluationException
    {
        IOp op = node.getAdditionOp();
        return op.op(this);
    }

    @Override
    public IPropertyValue callSubOp(IPropertyValue node)
    throws EvaluationException
    {
        IOp op = node.getSubtractionOp();
        return op.op(this);
    }

    @Override
    public IPropertyValue callMulOp(IPropertyValue node)
    throws EvaluationException
    {
        IOp op = node.getMultiplicationOp();
        return op.op(this);
    }

    @Override
    public IPropertyValue callDivOp(IPropertyValue node)
    throws EvaluationException
    {
        IOp op = node.getDivisionOp();
        return op.op(this);
    }

    @Override
    public IPropertyValue callEqOp(IPropertyValue node)
    throws EvaluationException
    {
        IOp op = node.getEqOp();
        return op.op(this);
    }

    @Override
    public IPropertyValue callNotEqOp(IPropertyValue node)
    throws EvaluationException
    {
        IOp op = node.getNotEqOp();
        return op.op(this);
    }

    @Override
    public IPropertyValue callLtOp(IPropertyValue node)
    throws EvaluationException
    {
        IOp op = node.getLtOp();
        return op.op(this);
    }

    @Override
    public IPropertyValue callGtOp(IPropertyValue node)
    throws EvaluationException
    {
        IOp op = node.getGtOp();
        return op.op(this);
    }

    @Override
    public IPropertyValue copy()
    {
        FunctionPropertyValue newValue = new FunctionPropertyValue(name);
        for (INode value : values) {
            newValue.addValue(value.copy());
        }

        return newValue;
    }

    @Override
    public String toString()
    {
        StringBuilder buffer = new StringBuilder();

        buffer.append(name);
        buffer.append("(");

        int i = 0;
        for (INode value : values) {
            buffer.append(value.toString());
            if (i < values.size() - 1) {
                buffer.append(", ");
            }
            i++;
        }

        buffer.append(")");

        return buffer.toString();
    }
}
