package com.kennycason.soroban.parser;

import com.kennycason.soroban.lexer.token.TokenStream;
import com.kennycason.soroban.lexer.token.TokenType;
import com.kennycason.soroban.lexer.tokenizer.CharacterStream;
import com.kennycason.soroban.lexer.tokenizer.ExpressionTokenizer;
import com.kennycason.soroban.number.BigRational;
import com.kennycason.soroban.parser.expression.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by kenny on 3/2/16.
 */
public class SorobanParserTest {
    private final ExpressionTokenizer expressionTokenizer = new ExpressionTokenizer();

    @Test
    public void infixFunctionPower() {
        final PrattParser parser = buildParser("a ^ 2");
        final Expression expression = parser.parseExpression();

        final InfixFunctionExpression infixFunctionExpression = (InfixFunctionExpression) expression;
        assertEquals("a", ((VariableExpression) infixFunctionExpression.getLeft()).getValue());
        assertEquals(TokenType.EXPONENT, infixFunctionExpression.getFunction().getType());
        assertEquals("^", infixFunctionExpression.getFunction().getValue());
        assertEquals(new BigRational(2L), ((NumberExpression) infixFunctionExpression.getRight()).getValue());
    }

    @Test
    public void infixFunctionSubtract() {
        final PrattParser parser = buildParser("a - 2");
        final Expression expression = parser.parseExpression();

        final InfixFunctionExpression infixFunctionExpression = (InfixFunctionExpression) expression;
        assertEquals("a", ((VariableExpression) infixFunctionExpression.getLeft()).getValue());
        assertEquals(TokenType.MINUS, infixFunctionExpression.getFunction().getType());
        assertEquals("-", infixFunctionExpression.getFunction().getValue());
        assertEquals(new BigRational(2L), ((NumberExpression) infixFunctionExpression.getRight()).getValue());
    }

    // parse as ((1 + 1) + 1)
    @Test
    public void chainedInfixFunction() {
        final PrattParser parser = buildParser("1 + 1 + 1");
        final Expression expression = parser.parseExpression();
        final InfixFunctionExpression topExpression = (InfixFunctionExpression) expression;

        final InfixFunctionExpression left = (InfixFunctionExpression) topExpression.getLeft();
        assertEquals(new BigRational(1L), ((NumberExpression) left.getLeft()).getValue());
        assertEquals(TokenType.PLUS, left.getFunction().getType());
        assertEquals("+", left.getFunction().getValue());
        assertEquals(new BigRational(1L), ((NumberExpression) left.getRight()).getValue());

        assertEquals(TokenType.PLUS, topExpression.getFunction().getType());
        assertEquals("+", topExpression.getFunction().getValue());
        assertEquals(new BigRational(1L), ((NumberExpression) topExpression.getRight()).getValue());
    }

    @Test
    public void prefixFunction() {
        final PrattParser parser = buildParser("sin(x)");
        final Expression expression = parser.parseExpression();

        final FunctionCallExpression functionCallExpression = (FunctionCallExpression) expression;
        assertEquals("sin", ((VariableExpression) functionCallExpression.getFunction()).getValue());
        assertEquals(1, functionCallExpression.getExpressions().size());
        assertEquals("x", ((VariableExpression) functionCallExpression.getExpressions().get(0)).getValue());
    }

    @Test
    public void chainedPrefixFunction() {
        final PrattParser parser = buildParser("sin(cos(x))");
        final Expression expression = parser.parseExpression();

        final FunctionCallExpression functionCallExpression = (FunctionCallExpression) expression;
        assertEquals("sin", ((VariableExpression) functionCallExpression.getFunction()).getValue());
        assertEquals(1, functionCallExpression.getExpressions().size());

        final FunctionCallExpression nestedFunctionCallExpression = (FunctionCallExpression) functionCallExpression.getExpressions().get(0);
        assertEquals("cos", ((VariableExpression) nestedFunctionCallExpression.getFunction()).getValue());
        assertEquals(1, nestedFunctionCallExpression.getExpressions().size());
        assertEquals("x", ((VariableExpression) nestedFunctionCallExpression.getExpressions().get(0)).getValue());
    }

    @Test
    public void mixfix() {
        final PrattParser parser = buildParser("sin(1 * 1)");
        final Expression expression = parser.parseExpression();

        final FunctionCallExpression functionCallExpression = (FunctionCallExpression) expression;
        assertEquals("sin", ((VariableExpression) functionCallExpression.getFunction()).getValue());
        assertEquals(1, functionCallExpression.getExpressions().size());

        final InfixFunctionExpression infixFunctionExpression = (InfixFunctionExpression) functionCallExpression.getExpressions().get(0);
        assertEquals(new BigRational(1L), ((NumberExpression) infixFunctionExpression.getLeft()).getValue());
        assertEquals(TokenType.MULTIPLY, infixFunctionExpression.getFunction().getType());
        assertEquals("*", infixFunctionExpression.getFunction().getValue());
        assertEquals(new BigRational(1L), ((NumberExpression) infixFunctionExpression.getRight()).getValue());
    }

    @Test
    public void groupedInfix() {
        final PrattParser parser = buildParser("x ^ (y ^ z)");
        final Expression expression = parser.parseExpression();

        final InfixFunctionExpression left = (InfixFunctionExpression) expression;
        assertEquals("x", ((VariableExpression) left.getLeft()).getValue());
        assertEquals(TokenType.EXPONENT, left.getFunction().getType());
        assertEquals("^", left.getFunction().getValue());

        final InfixFunctionExpression right = (InfixFunctionExpression) left.getRight();
        assertEquals("y", ((VariableExpression) right.getLeft()).getValue());
        assertEquals(TokenType.EXPONENT, right.getFunction().getType());
        assertEquals("^", right.getFunction().getValue());
        assertEquals("z", ((VariableExpression) right.getRight()).getValue());
    }

    @Test
    public void groupedInfixDifferentGroup() {
        final PrattParser parser = buildParser("(x ^ y) ^ z");
        final Expression expression = parser.parseExpression();

        final InfixFunctionExpression topExpression = (InfixFunctionExpression) expression;

        final InfixFunctionExpression left = (InfixFunctionExpression) topExpression.getLeft();
        assertEquals("x", ((VariableExpression) left.getLeft()).getValue());
        assertEquals(TokenType.EXPONENT, left.getFunction().getType());
        assertEquals("^", left.getFunction().getValue());
        assertEquals("y", ((VariableExpression) left.getRight()).getValue());

        assertEquals("^", topExpression.getFunction().getValue());
        assertEquals("z", ((VariableExpression) topExpression.getRight()).getValue());
    }

    @Test
    public void multiGroups() {
        final PrattParser parser = buildParser("((x ^ y) ^ z)");
        final Expression expression = parser.parseExpression();

        final InfixFunctionExpression topExpression = (InfixFunctionExpression) expression;

        final InfixFunctionExpression left = (InfixFunctionExpression) topExpression.getLeft();
        assertEquals("x", ((VariableExpression) left.getLeft()).getValue());
        assertEquals(TokenType.EXPONENT, left.getFunction().getType());
        assertEquals("^", left.getFunction().getValue());
        assertEquals("y", ((VariableExpression) left.getRight()).getValue());

        assertEquals("^", topExpression.getFunction().getValue());
        assertEquals("z", ((VariableExpression) topExpression.getRight()).getValue());
    }

    @Test
    public void unaryPrefix() {
        final PrattParser parser = buildParser("!n");
        final Expression expression = parser.parseExpression();

        final PrefixUnaryFunctionExpression prefixUnaryFunctionExpression = (PrefixUnaryFunctionExpression) expression;
        assertEquals("!", prefixUnaryFunctionExpression.getFunction().getValue());
        assertEquals("n", ((VariableExpression) prefixUnaryFunctionExpression.getExpression()).getValue());
    }

    @Test
    public void unaryPrefixNegative() {
        final PrattParser parser = buildParser("-n");
        final Expression expression = parser.parseExpression();

        final PrefixUnaryFunctionExpression prefixUnaryFunctionExpression = (PrefixUnaryFunctionExpression) expression;
        assertEquals("-", prefixUnaryFunctionExpression.getFunction().getValue());
        assertEquals("n", ((VariableExpression) prefixUnaryFunctionExpression.getExpression()).getValue());
    }

    @Test
    public void unaryPostfix() {
        final PrattParser parser = buildParser("n!");
        final Expression expression = parser.parseExpression();

        final PostfixUnaryFunctionExpression postfixUnaryFunctionExpression = (PostfixUnaryFunctionExpression) expression;
        assertEquals("!", postfixUnaryFunctionExpression.getFunction().getValue());
        assertEquals("n", ((VariableExpression) postfixUnaryFunctionExpression.getExpression()).getValue());
    }

    @Test
    public void polyAdd() {
        final PrattParser parser = buildParser("add(a, b, c)");
        final Expression expression = parser.parseExpression();

        final FunctionCallExpression functionCallExpression = (FunctionCallExpression) expression;
        assertEquals("add", ((VariableExpression) functionCallExpression.getFunction()).getValue());
        assertEquals(3, functionCallExpression.getExpressions().size());
        assertEquals("a", ((VariableExpression) functionCallExpression.getExpressions().get(0)).getValue());
        assertEquals("b", ((VariableExpression) functionCallExpression.getExpressions().get(1)).getValue());
        assertEquals("c", ((VariableExpression) functionCallExpression.getExpressions().get(2)).getValue());
    }

    @Test
    public void polyAddWithNestedExpressions() {
        final PrattParser parser = buildParser("add((a + a), (b + b))");
        final Expression expression = parser.parseExpression();

        final FunctionCallExpression functionCallExpression = (FunctionCallExpression) expression;
        assertEquals("add", ((VariableExpression) functionCallExpression.getFunction()).getValue());
        assertEquals(2, functionCallExpression.getExpressions().size());
        final InfixFunctionExpression left = (InfixFunctionExpression) functionCallExpression.getExpressions().get(0);
        final InfixFunctionExpression right = (InfixFunctionExpression) functionCallExpression.getExpressions().get(1);

        assertEquals("+", left.getFunction().getValue());
        assertEquals("a", ((VariableExpression) left.getLeft()).getValue());
        assertEquals("a", ((VariableExpression) left.getRight()).getValue());

        assertEquals("+", right.getFunction().getValue());
        assertEquals("b", ((VariableExpression) right.getLeft()).getValue());
        assertEquals("b", ((VariableExpression) right.getRight()).getValue());
    }

    @Test
    public void assignVariable() {
        final PrattParser parser = buildParser("a = 10");
        final Expression expression = parser.parseExpression();

        final VariableAssignmentFunctionExpression variableAssignmentFunctionExpression = (VariableAssignmentFunctionExpression) expression;
        assertEquals("a", variableAssignmentFunctionExpression.getVariableExpression().getValue());
        assertEquals(TokenType.ASSIGNMENT, variableAssignmentFunctionExpression.getFunction().getType());
        assertEquals("=", variableAssignmentFunctionExpression.getFunction().getValue());
        assertEquals(new BigRational(10L), variableAssignmentFunctionExpression.getValue().getValue());
    }

    private PrattParser buildParser(final String expr) {
        return new SorobanParser(
                new TokenStream(
                        expressionTokenizer.tokenize(
                                new CharacterStream(expr))));
    }

}
