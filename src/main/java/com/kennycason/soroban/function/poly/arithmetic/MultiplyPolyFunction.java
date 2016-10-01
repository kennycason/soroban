package com.kennycason.soroban.function.poly.arithmetic;

import com.kennycason.soroban.eval.exception.EvaluationException;
import com.kennycason.soroban.function.binary.arithmetic.MultiplyFunction;
import com.kennycason.soroban.function.poly.PolyFunction;
import com.kennycason.soroban.number.BigRational;

import java.util.List;

/**
 * Created by kenny on 3/6/16.
 */
public class MultiplyPolyFunction implements PolyFunction {
    private final MultiplyFunction multiplyFunction = new MultiplyFunction();

    @Override
    public BigRational apply(final List<BigRational> parameters) {
        if (parameters.size() < 1) { throw new EvaluationException("Multiple function must have at least one parameter"); }
        if (parameters.size() == 1) { return parameters.get(0); }
        if (parameters.size() == 2) {
            return multiplyFunction.apply(parameters.get(0), parameters.get(1));
        }
        BigRational sum = BigRational.ONE;
        for (final BigRational parameter : parameters) {
            sum = multiplyFunction.apply(sum, parameter);
        }
        return sum;
    }

}
