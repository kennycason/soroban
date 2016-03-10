package com.kennycason.soroban.parser.expression;

import com.kennycason.soroban.lexer.token.Token;

/**
 * Created by kenny on 3/3/16.
 */
public class VariableAssignmentFunctionExpression implements Expression {

    private final VariableExpression variableExpression;
    private final Token function;
    private final Expression expression;

    public VariableAssignmentFunctionExpression(final VariableExpression variableExpression,
                                                final Token function,
                                                final Expression expression) {
        this.variableExpression = variableExpression;
        this.function = function;
        this.expression = expression;
    }

    public VariableExpression getVariableExpression() {
        return variableExpression;
    }

    public Token getFunction() {
        return function;
    }

    public Expression getExpression() {
        return expression;
    }

}
