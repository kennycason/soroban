package com.kennycason.soroban.eval;

import com.kennycason.soroban.VariableDictionary;
import com.kennycason.soroban.lexer.token.TokenStream;
import com.kennycason.soroban.lexer.tokenizer.CharacterStream;
import com.kennycason.soroban.lexer.tokenizer.ExpressionTokenizer;
import com.kennycason.soroban.number.BigRational;
import com.kennycason.soroban.parser.SorobanParser;
import com.kennycason.soroban.parser.expression.Expression;
import com.kennycason.soroban.parser.expression.InfixFunctionExpression;
import com.kennycason.soroban.parser.expression.NumberExpression;
import com.kennycason.soroban.parser.expression.VariableExpression;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by kenny on 3/3/16.
 */
public class ExpressionEvaluatorWithVariablesTest {
    final ExpressionTokenizer expressionTokenizer = new ExpressionTokenizer();
    final ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

    @Before
    public void before() {
        VariableDictionary.clearAll();
    }

    @Test
    public void xPlusY() {
        final Expression expression = evaluate("x + 10");
        assertTrue(expressionEvaluator.evaluate(expression) instanceof InfixFunctionExpression);

        // set variable x = 5
        VariableDictionary.set("x", new BigRational(5));
        final Expression evaluatedExpression = expressionEvaluator.evaluate(expression);
        assertTrue(evaluatedExpression instanceof NumberExpression);
        assertEquals(new BigRational(15), ((NumberExpression) evaluatedExpression).getValue());
    }

    @Test
    public void longerFunction() {
        final Expression expression = evaluate("(x + y) * x");
        final Expression evaluatedExpression = expressionEvaluator.evaluate(expression);
        assertTrue(evaluatedExpression instanceof InfixFunctionExpression);
        assertTrue(((InfixFunctionExpression) evaluatedExpression).getRight() instanceof VariableExpression);
        assertTrue(((InfixFunctionExpression) ((InfixFunctionExpression) evaluatedExpression).getLeft()).getLeft() instanceof VariableExpression);
        assertTrue(((InfixFunctionExpression) ((InfixFunctionExpression) evaluatedExpression).getLeft()).getRight() instanceof VariableExpression);

        // set variable x = 5, partially solve
        VariableDictionary.set("x", new BigRational(5));
        final Expression evaluatedExpressionWithX = expressionEvaluator.evaluate(expression);
        assertTrue(evaluatedExpressionWithX instanceof InfixFunctionExpression);
        assertTrue(((InfixFunctionExpression) evaluatedExpressionWithX).getRight() instanceof NumberExpression);
        assertTrue(((InfixFunctionExpression) ((InfixFunctionExpression) evaluatedExpressionWithX).getLeft()).getLeft() instanceof NumberExpression);
        assertTrue(((InfixFunctionExpression) ((InfixFunctionExpression) evaluatedExpressionWithX).getLeft()).getRight() instanceof VariableExpression);

        // set varialbe y = 10, completely solve
        VariableDictionary.set("y", new BigRational(10));
        final Expression evaluatedExpressionWithXAndY = expressionEvaluator.evaluate(expression);
        assertTrue(evaluatedExpressionWithXAndY instanceof NumberExpression);
        // (5 + 10) * 5
        assertEquals(new BigRational(75), ((NumberExpression) evaluatedExpressionWithXAndY).getValue());
    }

    private Expression evaluate(final String expr) {
        return new SorobanParser(
                            new TokenStream(
                                    expressionTokenizer.tokenize(
                                            new CharacterStream(expr))))
                                                            .parseExpression();
    }

}
