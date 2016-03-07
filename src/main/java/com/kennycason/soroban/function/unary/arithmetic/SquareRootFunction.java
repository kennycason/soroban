package com.kennycason.soroban.function.unary.arithmetic;

import com.kennycason.soroban.function.unary.UnaryFunction;
import com.kennycason.soroban.number.BigRational;

/**
 * Created by kenny on 3/6/16.
 */
public class SquareRootFunction implements UnaryFunction {

    @Override
    public BigRational apply(final BigRational left) {
        return new BigRational(
                Math.sqrt(left.getValue().doubleValue())
        );
    }

}
