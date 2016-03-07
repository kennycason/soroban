package com.kennycason.soroban.print;

import com.kennycason.soroban.eval.ExpressionEvaluator;
import com.kennycason.soroban.lexer.token.TokenStream;
import com.kennycason.soroban.lexer.tokenizer.CharacterStream;
import com.kennycason.soroban.lexer.tokenizer.ExpressionTokenizer;
import com.kennycason.soroban.parser.SorobanParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by kenny on 3/6/16.
 */
public class ExpressionPrinterTest {
    final ExpressionTokenizer expressionTokenizer = new ExpressionTokenizer();
    final ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator();
    final ExpressionPrinter expressionPrinter = new ExpressionPrinter();

    @Test
    public void print() {
        assertEquals("a", print("a"));
        assertEquals("10", print("10"));
        assertEquals("10/2", print("10 / 2"));
        assertEquals("5", print("10 / 2.0"));
        assertEquals("4", print("10.0 / 2.5"));
        assertEquals("3.3333333333333333333", print("10.0 / 3.0"));
        assertEquals("1.0", print("sin(rad(90))"));
        assertEquals("sin(x)", print("sin(x)"));
        assertEquals("-(n)", print("-n"));  // TODO fix, this is a bug due to unary prefix functions, single param function calls are mapped to unary prefix functions
        assertEquals("sin(x + y)", print("sin(x + y)"));
        assertEquals("sin(x) + cos(y) + 0.47669014603118814", print("sin(x) + cos(y) + tan(PI^2)"));
    }

    private String print(final String expr) {
        final StringBuilder stringBuilder = new StringBuilder();
        expressionPrinter.print(
                expressionEvaluator.evaluate(
                        new SorobanParser(
                                new TokenStream(
                                        expressionTokenizer.tokenize(
                                                new CharacterStream(expr))))
                                                        .parseExpression()), stringBuilder);
        return stringBuilder.toString();
    }

}
