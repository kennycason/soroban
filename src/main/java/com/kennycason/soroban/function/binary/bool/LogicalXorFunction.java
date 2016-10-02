package com.kennycason.soroban.function.binary.bool;

import com.kennycason.soroban.eval.exception.EvaluationException;
import com.kennycason.soroban.function.binary.BinaryFunction;
import com.kennycason.soroban.number.BigRational;

/**
 * Created by kenny on 10/1/16.
 */
public class LogicalXorFunction implements BinaryFunction {

    @Override
    public BigRational apply(final BigRational left, final BigRational right) {
        if (!(left.isInteger() && right.isInteger())) {
            throw new EvaluationException("Logical XOR can only be applied to integers");
        }
        return new BigRational(left.getNumerator().xor(right.getNumerator()));
    }

}
