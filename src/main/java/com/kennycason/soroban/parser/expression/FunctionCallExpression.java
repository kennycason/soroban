package com.kennycason.soroban.parser.expression;

import java.util.List;

/**
 * Created by kenny on 3/3/16.
 *
 * sin(a)
 * pow(base, exp)
 */
public class FunctionCallExpression implements Expression {

    private final Expression function;
    private final List<Expression> expressions;

    public FunctionCallExpression(final Expression function,
                                  final List<Expression> expressions) {
        this.function = function;
        this.expressions = expressions;
    }

    public Expression getFunction() {
        return function;
    }

    public List<Expression> getExpressions() {
        return expressions;
    }

}
