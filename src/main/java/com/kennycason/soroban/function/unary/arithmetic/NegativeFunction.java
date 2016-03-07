package com.kennycason.soroban.function.unary.arithmetic;

import com.kennycason.soroban.function.unary.UnaryFunction;
import com.kennycason.soroban.number.BigRational;

/**
 * Created by kenny on 3/1/16.
 */
public class NegativeFunction implements UnaryFunction {

    @Override
    public BigRational apply(final BigRational input) {
        if (input.isFraction()) {
            return new BigRational(input.getNumerator().negate(), input.getDenominator());
        }
        return new BigRational(input.getValue().negate());
    }

}
