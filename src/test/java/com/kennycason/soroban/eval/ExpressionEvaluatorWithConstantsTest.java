package com.kennycason.soroban.eval;

import com.kennycason.soroban.Soroban;
import com.kennycason.soroban.number.BigRational;
import com.kennycason.soroban.parser.expression.Expression;
import com.kennycason.soroban.parser.expression.NumberExpression;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by kenny on 3/3/16.
 */
public class ExpressionEvaluatorWithConstantsTest {

    @Test
    public void pi() {
        final Expression expression = Soroban.evaluate("PI * 2");

        assertTrue(expression instanceof NumberExpression);
        assertEquals(new BigRational(Math.PI * 2), ((NumberExpression) expression).getValue());
    }

}
