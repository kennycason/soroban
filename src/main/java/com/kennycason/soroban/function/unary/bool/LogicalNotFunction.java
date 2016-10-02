package com.kennycason.soroban.function.unary.bool;

import com.kennycason.soroban.eval.exception.EvaluationException;
import com.kennycason.soroban.function.unary.UnaryFunction;
import com.kennycason.soroban.number.BigRational;

/**
 * Created by kenny on 3/1/16.
 */
public class LogicalNotFunction implements UnaryFunction {

    @Override
    public BigRational apply(final BigRational input) {
        if (!input.isInteger()) {
            throw new EvaluationException("Logical NOT can only be applied to integers");
        }
        return new BigRational(input.getNumerator().not());
    }

}
