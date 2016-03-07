package com.kennycason.soroban.parser.expression;

import com.kennycason.soroban.number.BigRational;

/**
 * Created by kenny on 3/3/16.
 */
public class NumberExpression implements Expression {

    private final BigRational number;

    public NumberExpression(final BigRational number) {
        this.number = number;
    }

    public BigRational getValue() {
        return number;
    }

}
