package com.kennycason.soroban.eval;

import com.kennycason.soroban.Soroban;
import com.kennycason.soroban.dictionary.VariableDictionary;
import com.kennycason.soroban.number.BigRational;
import com.kennycason.soroban.parser.expression.Expression;
import com.kennycason.soroban.parser.expression.NumberExpression;
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
    public void xEqual10() {
        final Expression expression = Soroban.evaluate("x = 10");
        final Expression evaluatedExpression = Soroban.evaluate(expression);
        assertTrue(evaluatedExpression instanceof NumberExpression);

        assertEquals(new BigRational(10), ((NumberExpression) evaluatedExpression).getValue());
        assertEquals(new BigRational(10), VariableDictionary.get("x"));

        Soroban.evaluate("x = 20");
        assertEquals(new BigRational(20), VariableDictionary.get("x"));
    }

}
