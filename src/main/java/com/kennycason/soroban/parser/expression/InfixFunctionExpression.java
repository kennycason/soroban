package com.kennycason.soroban.parser.expression;

import com.kennycason.soroban.lexer.token.Token;

/**
 * Created by kenny on 3/3/16.
 */
public class InfixFunctionExpression implements Expression {

    private final Expression left;
    private final Token function;
    private final Expression right;

    public InfixFunctionExpression(final Expression left,
                                   final Token function,
                                   final Expression right) {
        this.left = left;
        this.function = function;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public Token getFunction() {
        return function;
    }

    public Expression getRight() {
        return right;
    }

}
