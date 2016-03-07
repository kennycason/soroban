package com.kennycason.soroban.function.binary.arithmetic;

import com.kennycason.soroban.function.binary.BinaryFunction;
import com.kennycason.soroban.number.BigRational;

/**
 * Created by kenny on 3/1/16.
 */
public class MultiplyFunction implements BinaryFunction {

    @Override
    public BigRational apply(final BigRational left, final BigRational right) {
        if (left.isFraction() && right.isFraction()) {
            return new BigRational(
                    left.getNumerator().multiply(right.getNumerator()),
                    left.getDenominator().multiply(right.getDenominator())
            );
        }
        return new BigRational(
                left.getValue().multiply(right.getValue())
        );
    }

}
