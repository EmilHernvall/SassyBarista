package net.quenchnetworks.sassybarista.sass.expression;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.value.*;

public interface NodeVisitor
{
    IPropertyValue visitAddition(AdditionNode node) throws EvaluationException;
    IPropertyValue visitSubtraction(SubtractionNode node) throws EvaluationException;
    IPropertyValue visitMultiplication(MultiplicationNode node) throws EvaluationException;
    IPropertyValue visitDivision(DivisionNode node) throws EvaluationException;
    IPropertyValue visitNegatation(NegationNode node) throws EvaluationException;
    IPropertyValue visitValue(IPropertyValue node) throws EvaluationException;
}
