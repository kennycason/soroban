package com.kennycason.soroban.eval;

import com.kennycason.soroban.lexer.token.TokenStream;
import com.kennycason.soroban.lexer.tokenizer.CharacterStream;
import com.kennycason.soroban.lexer.tokenizer.ExpressionTokenizer;
import com.kennycason.soroban.number.BigRational;
import com.kennycason.soroban.parser.SorobanParser;
import com.kennycason.soroban.parser.expression.Expression;
import com.kennycason.soroban.parser.expression.NumberExpression;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by kenny on 3/3/16.
 */
public class ExpressionEvaluatorTest {
    private static final double DELTA = 0.00000005;
    final ExpressionTokenizer expressionTokenizer = new ExpressionTokenizer();
    final ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();

    @Before
    public void before() {
        expressionEvaluator.clearVariables();
    }

    @Test
    public void base10() {
        final Expression expression = evaluate("10");
        assertTrue(expression instanceof NumberExpression);
        assertEquals(new BigRational(10), ((NumberExpression) expression).getValue());

        final Expression expression2 = evaluate("10.0");
        assertTrue(expression2 instanceof NumberExpression);
        assertEquals(new BigRational(10.0), ((NumberExpression) expression2).getValue());
    }

    @Test
    public void base2() {
        final Expression expression = evaluate("0b101");
        assertTrue(expression instanceof NumberExpression);
        assertEquals(new BigRational(5), ((NumberExpression) expression).getValue());
    }

    @Test
    public void base16() {
        final Expression expression = evaluate("0xFF");
        assertTrue(expression instanceof NumberExpression);
        assertEquals(new BigRational(255), ((NumberExpression) expression).getValue());
    }

    @Test
    public void sin90() {
        final Expression expression = evaluate("sin(rad(90))");
        assertTrue(expression instanceof NumberExpression);
        assertEquals(new BigRational(1.0), ((NumberExpression) expression).getValue());
    }

    @Test
    public void sinSumOfIntegers() {
        final Expression expression = evaluate("sin(rad(45 + 45))");
        assertTrue(expression instanceof NumberExpression);
        assertEquals(new BigRational(1.0), ((NumberExpression) expression).getValue());
    }

    @Test
    public void arithmetic() {
        final Expression expression = evaluate("-((10 ^ 2) / 4 + 25) * (1 + 1)");
        assertTrue(expression instanceof NumberExpression);
        assertEquals(new BigRational(-100.0), ((NumberExpression) expression).getValue());
    }

    @Test
    public void arithmetic2() {
        final Expression expression = evaluate("10 / 2");
        assertTrue(expression instanceof NumberExpression);
        // TODO doesn't equal 5.
        assertEquals(new BigRational(BigInteger.valueOf(10), BigInteger.valueOf(2)), ((NumberExpression) expression).getValue());
    }

    @Test
    public void addHex() {
        final Expression expression = evaluate("0xEE + 0x11");
        assertTrue(expression instanceof NumberExpression);
        assertEquals(new BigRational(0xFF), ((NumberExpression) expression).getValue());
    }

    @Test
    public void factorial() {
        final Expression expression = evaluate("10!");
        assertTrue(expression instanceof NumberExpression);
        assertEquals(new BigRational(3628800L), ((NumberExpression) expression).getValue());
    }

    @Test
    public void log10() {
        final Expression expression = evaluate("log10(100)");
        assertTrue(expression instanceof NumberExpression);
        assertEquals(new BigRational(2.0), ((NumberExpression) expression).getValue());
    }

    @Test
    public void ln() {
        final Expression expression = evaluate("ln(2.71828182846)");
        assertTrue(expression instanceof NumberExpression);
        assertEquals(new BigRational(1.0).getValue().doubleValue(),
                ((NumberExpression) expression).getValue().getValue().doubleValue(), DELTA);
    }

    @Test
    public void addPoly() {
        final Expression expression = evaluate("add(1, 2, 3, 4, 5)");
        assertTrue(expression instanceof NumberExpression);
        assertEquals(new BigRational(15), ((NumberExpression) expression).getValue());
    }

    @Test
    public void addPolyWithFunctions() {
        final Expression expression = evaluate("add(1 + 1, 1 + 1)");
        assertTrue(expression instanceof NumberExpression);
        assertEquals(new BigRational(4), ((NumberExpression) expression).getValue());
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
