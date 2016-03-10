package com.kennycason.soroban.eval;

import com.kennycason.soroban.Soroban;
import com.kennycason.soroban.dictionary.VariableDictionary;
import com.kennycason.soroban.lexer.token.TokenType;
import com.kennycason.soroban.number.BigRational;
import com.kennycason.soroban.parser.expression.Expression;
import com.kennycason.soroban.parser.expression.InfixFunctionExpression;
import com.kennycason.soroban.parser.expression.NumberExpression;
import com.kennycason.soroban.parser.expression.VariableExpression;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by kenny on 3/3/16.
 */
public class ExpressionEvaluatorWithVariableAssignmentTest {

    @Before
    public void before() {
        VariableDictionary.clearAll();
    }

    @After
    public void after() {
        VariableDictionary.clearAll();
    }

    @Test
    public void numberAssignment() {
        final Expression expression = Soroban.evaluate("x = 10");
        final Expression evaluatedExpression = Soroban.evaluate(expression);
        assertTrue(evaluatedExpression instanceof NumberExpression);

        assertEquals(new BigRational(10), ((NumberExpression) evaluatedExpression).getValue());
        assertEquals(new BigRational(10), ((NumberExpression) VariableDictionary.get("x")).getValue());

        Soroban.evaluate("x = 20");
        assertEquals(new BigRational(20), ((NumberExpression) VariableDictionary.get("x")).getValue());
    }

    @Test
    public void expressionAssignment() {
        final Expression expression = Soroban.evaluate("x = a ^ 3");
        final Expression evaluatedExpression = Soroban.evaluate(expression);
        assertTrue(evaluatedExpression instanceof InfixFunctionExpression);

        final InfixFunctionExpression infixFunctionExpression = (InfixFunctionExpression) evaluatedExpression;
        assertEquals("a", ((VariableExpression) infixFunctionExpression.getLeft()).getValue());
        assertEquals(TokenType.EXPONENT, infixFunctionExpression.getFunction().getType());
        assertEquals(new BigRational(3), ((NumberExpression) infixFunctionExpression.getRight()).getValue());

        Soroban.evaluate("a = 2");
        assertEquals(new BigRational(2), ((NumberExpression) Soroban.evaluate("a")).getValue());
        assertEquals(new BigRational(8.0), ((NumberExpression) Soroban.evaluate("x")).getValue());
    }

}
