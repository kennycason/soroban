package com.kennycason.soroban.function.binary.arithmetic;

import com.kennycason.soroban.function.binary.BinaryFunction;
import com.kennycason.soroban.number.BigRational;

import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Created by kenny on 3/1/16.
 */
public class DivideFunction implements BinaryFunction {
    private static final MultiplyFunction MULTIPLY_FUNCTION = new MultiplyFunction();

    @Override
    public BigRational apply(final BigRational left, final BigRational right) {
        if (left.isFraction() && right.isFraction()) {
            // flip and multiply
            return MULTIPLY_FUNCTION.apply(left, new BigRational(right.getDenominator(), right.getNumerator()));
        }
        // TODO handle infinity and nan

        return new BigRational(
                left.getValue()
                        .divide(right.getValue(), new MathContext(20, RoundingMode.HALF_UP)));
    }

}
