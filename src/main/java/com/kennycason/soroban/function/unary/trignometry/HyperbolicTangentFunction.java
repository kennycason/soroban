package com.kennycason.soroban.function.unary.trignometry;

import com.kennycason.soroban.function.unary.UnaryFunction;
import com.kennycason.soroban.number.BigRational;

/**
 * Created by kenny on 3/1/16.
 */
public class HyperbolicTangentFunction implements UnaryFunction {

    @Override
    public BigRational apply(final BigRational input) {
        return new BigRational(Math.tanh(input.getValue().doubleValue()));
    }
}
