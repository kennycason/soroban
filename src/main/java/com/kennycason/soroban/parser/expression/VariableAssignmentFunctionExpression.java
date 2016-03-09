package com.kennycason.soroban.parser.expression;

import com.kennycason.soroban.lexer.token.Token;

/**
 * Created by kenny on 3/3/16.
 */
public class VariableAssignmentFunctionExpression implements Expression {

    private final VariableExpression variableExpression;
    private final Token function;
    private final NumberExpression value;

    public VariableAssignmentFunctionExpression(final VariableExpression variableExpression,
                                                final Token function,
                                                final NumberExpression value) {
        this.variableExpression = variableExpression;
        this.function = function;
        this.value = value;
    }

    public VariableExpression getVariableExpression() {
        return variableExpression;
    }

    public Token getFunction() {
        return function;
    }

    public NumberExpression getValue() {
        return value;
    }

}
