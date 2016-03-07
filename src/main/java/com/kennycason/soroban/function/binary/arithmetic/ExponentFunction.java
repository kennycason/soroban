package com.kennycason.soroban.function.binary.arithmetic;

import com.kennycason.soroban.function.binary.BinaryFunction;
import com.kennycason.soroban.number.BigRational;

/**
 * Created by kenny on 3/1/16.
 */
public class ExponentFunction implements BinaryFunction {

    @Override
    public BigRational apply(final BigRational left, final BigRational right) {
        return new BigRational(
                Math.pow(left.getValue().doubleValue(), right.getValue().doubleValue())
        );
    }

}
