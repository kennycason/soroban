package com.kennycason.soroban.eval;

import com.kennycason.soroban.Soroban;
import com.kennycason.soroban.dictionary.VariableDictionary;
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
public class ExpressionEvaluatorWithVariablesTest {
    @Before
    public void before() {
        VariableDictionary.clearAll();
    }

    @After
    public void after() {
        VariableDictionary.clearAll();
    }

    @Test
    public void xPlusY() {
        final Expression expression = Soroban.evaluate("x + 10");

        assertTrue(expression instanceof InfixFunctionExpression);
        // set variable x = 5
        VariableDictionary.set("x", new NumberExpression(new BigRational(5)));
        final Expression evaluatedExpression = Soroban.evaluate(expression);
        assertTrue(evaluatedExpression instanceof NumberExpression);
        assertEquals(new BigRational(15), ((NumberExpression) evaluatedExpression).getValue());
    }

    @Test
    public void longerFunction() {
        final Expression expression = Soroban.evaluate("(x + y) * x");
        final Expression evaluatedExpression = Soroban.evaluate(expression);
        assertTrue(evaluatedExpression instanceof InfixFunctionExpression);
        assertTrue(((InfixFunctionExpression) evaluatedExpression).getRight() instanceof VariableExpression);
        assertTrue(((InfixFunctionExpression) ((InfixFunctionExpression) evaluatedExpression).getLeft()).getLeft() instanceof VariableExpression);
        assertTrue(((InfixFunctionExpression) ((InfixFunctionExpression) evaluatedExpression).getLeft()).getRight() instanceof VariableExpression);

        // set variable x = 5, partially solve
        VariableDictionary.set("x", new NumberExpression(new BigRational(5)));
        final Expression evaluatedExpressionWithX = Soroban.evaluate(expression);
        assertTrue(evaluatedExpressionWithX instanceof InfixFunctionExpression);
        assertTrue(((InfixFunctionExpression) evaluatedExpressionWithX).getRight() instanceof NumberExpression);
        assertTrue(((InfixFunctionExpression) ((InfixFunctionExpression) evaluatedExpressionWithX).getLeft()).getLeft() instanceof NumberExpression);
        assertTrue(((InfixFunctionExpression) ((InfixFunctionExpression) evaluatedExpressionWithX).getLeft()).getRight() instanceof VariableExpression);

        // set varialbe y = 10, completely solve
        VariableDictionary.set("y", new NumberExpression(new BigRational(10)));
        final Expression evaluatedExpressionWithXAndY = Soroban.evaluate(expression);
        assertTrue(evaluatedExpressionWithXAndY instanceof NumberExpression);
        // (5 + 10) * 5
        assertEquals(new BigRational(75), ((NumberExpression) evaluatedExpressionWithXAndY).getValue());
    }

}
