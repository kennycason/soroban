package com.kennycason.soroban.function.binary.arithmetic;

import com.kennycason.soroban.eval.exception.EvaluationException;
import com.kennycason.soroban.function.binary.BinaryFunction;
import com.kennycason.soroban.number.BigRational;

/**
 * Created by kenny on 10/1/16.
 *
 * Greatest Common Divisor
 */
public class GcdFunction implements BinaryFunction {

    @Override
    public BigRational apply(final BigRational left, final BigRational right) {
        if (!(left.isInteger() && right.isInteger())) {
            throw new EvaluationException("GCD can only be applied to integers");
        }
        return new BigRational(left.getNumerator().gcd((right.getNumerator())));

    }

}
