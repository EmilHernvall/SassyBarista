package net.quenchnetworks.sassybarista.sass.expression;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.value.*;

public interface NodeVisitor
{
    IPropertyValue visitAddition(AdditionNode node) throws EvaluationException;
    IPropertyValue visitSubtraction(SubtractionNode node) throws EvaluationException;
    IPropertyValue visitMultiplication(MultiplicationNode node) throws EvaluationException;
    IPropertyValue visitDivision(DivisionNode node) throws EvaluationException;
    IPropertyValue visitNegatation(NegationNode node) throws EvaluationException;
    
    IPropertyValue visitEq(EqNode node) throws EvaluationException;
    IPropertyValue visitNotEq(NotEqNode node) throws EvaluationException;
    IPropertyValue visitLt(LtNode node) throws EvaluationException;
    IPropertyValue visitGt(GtNode node) throws EvaluationException;
    
    IPropertyValue visitValue(IPropertyValue node) throws EvaluationException;
}
