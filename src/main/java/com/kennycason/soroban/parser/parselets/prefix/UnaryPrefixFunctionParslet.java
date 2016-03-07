package com.kennycason.soroban.parser.parselets.prefix;

import com.kennycason.soroban.lexer.token.Token;
import com.kennycason.soroban.parser.PrattParser;
import com.kennycason.soroban.parser.expression.Expression;
import com.kennycason.soroban.parser.expression.PrefixUnaryFunctionExpression;

/**
 * Created by kenny on 3/3/16.
 *
 * handle generic prefix functions like !n, -n
 */
public class UnaryPrefixFunctionParslet implements PrefixParselet {

    private final int precedence;

    public UnaryPrefixFunctionParslet(final int precedence) {
        this.precedence = precedence;
    }

    @Override
    public Expression parse(final PrattParser parser, final Token token) {
        final Expression expression = parser.parseExpression(precedence);

        return new PrefixUnaryFunctionExpression(expression, token);
    }
}
