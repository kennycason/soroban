package com.kennycason.soroban;

import com.kennycason.soroban.eval.ExpressionEvaluator;
import com.kennycason.soroban.lexer.token.Token;
import com.kennycason.soroban.lexer.token.TokenStream;
import com.kennycason.soroban.lexer.tokenizer.CharacterStream;
import com.kennycason.soroban.lexer.tokenizer.ExpressionTokenizer;
import com.kennycason.soroban.parser.SorobanParser;
import com.kennycason.soroban.parser.expression.Expression;
import com.kennycason.soroban.print.ExpressionPrinter;

import java.util.List;

/**
 * Created by kenny on 3/9/16.
 */
public class Soroban {
    private static final ExpressionTokenizer EXPRESSION_TOKENIZER = new ExpressionTokenizer();
    private static final  ExpressionEvaluator EXPRESSION_EVALUATOR = new ExpressionEvaluator();
    private static final  ExpressionPrinter EXPRESSION_PRINTER = new ExpressionPrinter();

    public static List<Token> tokenize(final String expression) {
        return EXPRESSION_TOKENIZER.tokenize(new CharacterStream(expression));
    }

    public static TokenStream tokenStream(final String expression) {
        return new TokenStream(EXPRESSION_TOKENIZER.tokenize(new CharacterStream(expression)));
    }

    public static SorobanParser parser(final String expression) {
        return parser(tokenize(expression));
    }

    public static SorobanParser parser(final List<Token> tokens) {
        return new SorobanParser(new TokenStream(tokens));
    }

    public static Expression evaluate(final String expression) {
        return EXPRESSION_EVALUATOR.evaluate(parser(expression).parseExpression());
    }

    public static Expression evaluate(final Expression expression) {
        return EXPRESSION_EVALUATOR.evaluate(expression);
    }

    public static String print(final String expression) {
        final StringBuilder stringBuilder = new StringBuilder();
        EXPRESSION_PRINTER.print(evaluate(expression), stringBuilder);
        return stringBuilder.toString();
    }

    public static String print(final Expression expression) {
        final StringBuilder stringBuilder = new StringBuilder();
        EXPRESSION_PRINTER.print(expression, stringBuilder);
        return stringBuilder.toString();
    }

}
