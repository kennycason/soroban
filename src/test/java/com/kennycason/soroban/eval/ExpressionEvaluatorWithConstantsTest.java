package com.kennycason.soroban.eval;

import com.kennycason.soroban.lexer.token.TokenStream;
import com.kennycason.soroban.lexer.tokenizer.CharacterStream;
import com.kennycason.soroban.lexer.tokenizer.ExpressionTokenizer;
import com.kennycason.soroban.number.BigRational;
import com.kennycason.soroban.parser.SorobanParser;
import com.kennycason.soroban.parser.expression.Expression;
import com.kennycason.soroban.parser.expression.NumberExpression;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by kenny on 3/3/16.
 */
public class ExpressionEvaluatorWithConstantsTest {
    final ExpressionTokenizer expressionTokenizer = new ExpressionTokenizer();
    final ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

    @Test
    public void pi() {
        final Expression expression = evaluate("PI * 2");

        assertTrue(expression instanceof NumberExpression);
        assertEquals(new BigRational(Math.PI * 2), ((NumberExpression) expression).getValue());
    }

    private Expression evaluate(final String expr) {
        return expressionEvaluator.evaluate(
                new SorobanParser(
                        new TokenStream(
                                expressionTokenizer.tokenize(
                                        new CharacterStream(expr))))
                                                .parseExpression());
    }

}
