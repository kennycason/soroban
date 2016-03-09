package com.kennycason.soroban.eval;

import com.kennycason.soroban.VariableDictionary;
import com.kennycason.soroban.lexer.token.TokenStream;
import com.kennycason.soroban.lexer.tokenizer.CharacterStream;
import com.kennycason.soroban.lexer.tokenizer.ExpressionTokenizer;
import com.kennycason.soroban.number.BigRational;
import com.kennycason.soroban.parser.SorobanParser;
import com.kennycason.soroban.parser.expression.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by kenny on 3/3/16.
 */
public class ExpressionEvaluatorWithVariableAssignmentTest {
    final ExpressionTokenizer expressionTokenizer = new ExpressionTokenizer();
    final ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

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
        final Expression expression = evaluate("x = 10");
        final Expression evaluatedExpression = expressionEvaluator.evaluate(expression);
        assertTrue(evaluatedExpression instanceof NumberExpression);

        assertEquals(new BigRational(10), ((NumberExpression) evaluatedExpression).getValue());
        assertEquals(new BigRational(10), VariableDictionary.get("x"));

        expressionEvaluator.evaluate(evaluate("x = 20"));
        assertEquals(new BigRational(20), VariableDictionary.get("x"));
    }

    private Expression evaluate(final String expr) {
        return new SorobanParser(
                            new TokenStream(
                                    expressionTokenizer.tokenize(
                                            new CharacterStream(expr))))
                                                            .parseExpression();
    }

}
