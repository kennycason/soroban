package com.kennycason.soroban.parser.expression;

import com.kennycason.soroban.lexer.token.Token;

/**
 * Created by kenny on 3/3/16.
 */
public class VariableExpression implements Expression {

    private final Token token;

    public VariableExpression(final Token token) {
        this.token = token;
    }

    public String getValue() {
        return token.getValue();
    }

    public Token getToken() {
        return token;
    }

}
