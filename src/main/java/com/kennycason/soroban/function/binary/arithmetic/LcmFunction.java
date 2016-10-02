package com.kennycason.soroban.function.binary.arithmetic;

import com.kennycason.soroban.eval.exception.EvaluationException;
import com.kennycason.soroban.function.binary.BinaryFunction;
import com.kennycason.soroban.function.unary.arithmetic.ReduceFractionFunction;
import com.kennycason.soroban.number.BigRational;

/**
 * Created by kenny on 10/1/16.
 *
 * Least Common Multiple
 * lcm(a, b) = ab/gcd(a, b)
 */
public class LcmFunction implements BinaryFunction {
    private static final GcdFunction GCD_FUNCTION = new GcdFunction();
    private static final MultiplyFunction MULTIPLY_FUNCTION = new MultiplyFunction();
    private static final DivideFunction DIVIDE_FUNCTION = new DivideFunction();
    private static final ReduceFractionFunction REDUCE_FRACTION_FUNCTION = new ReduceFractionFunction();

    @Override
    public BigRational apply(final BigRational left, final BigRational right) {
        if (!(left.isInteger() && right.isInteger())) {
            throw new EvaluationException("LCM can only be applied to integers");
        }
        return REDUCE_FRACTION_FUNCTION.apply(
                    DIVIDE_FUNCTION.apply(
                        MULTIPLY_FUNCTION.apply(left, right),
                        GCD_FUNCTION.apply(left, right)));
    }

}
