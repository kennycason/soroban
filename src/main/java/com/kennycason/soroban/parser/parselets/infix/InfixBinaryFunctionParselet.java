package com.kennycason.soroban.parser.parselets.infix;

import com.kennycason.soroban.lexer.token.Associativity;
import com.kennycason.soroban.lexer.token.Token;
import com.kennycason.soroban.parser.PrattParser;
import com.kennycason.soroban.parser.expression.Expression;
import com.kennycason.soroban.parser.expression.InfixFunctionExpression;

/**
 * Created by kenny on 3/3/16.
 *
 * handle binary infix functions
 * 1 + 1
 * 1 - 1
 * 1 * 1
 * 1 / 1
 * a % 2
 * a ^ 4
 */
public class InfixBinaryFunctionParselet implements InfixParselet {

    private final int precedence;
    private final Associativity associativity;

    public InfixBinaryFunctionParselet(final int precedence, final Associativity associativity) {
        this.precedence = precedence;
        this.associativity = associativity;
    }

    @Override
    public Expression parse(final PrattParser parser, final Expression left, final Token token) {
        // to allow right-associative functions like '^' we need to add a lower
        // precedence
        if (associativity == Associativity.RIGHT) {
            final Expression right = parser.parseExpression(precedence - 1);
            return new InfixFunctionExpression(left, token, right);
        }
        // is left-associative
        final Expression right = parser.parseExpression(precedence);
        return new InfixFunctionExpression(left, token, right);
    }

    @Override
    public int getPrecedence() {
        return precedence;
    }

}
