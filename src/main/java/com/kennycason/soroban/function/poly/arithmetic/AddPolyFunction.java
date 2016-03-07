package com.kennycason.soroban.function.poly.arithmetic;

import com.kennycason.soroban.eval.exception.EvaluationException;
import com.kennycason.soroban.function.binary.arithmetic.AddFunction;
import com.kennycason.soroban.function.poly.PolyFunction;
import com.kennycason.soroban.number.BigRational;

import java.util.List;

/**
 * Created by kenny on 3/6/16.
 */
public class AddPolyFunction implements PolyFunction {
    private final AddFunction addFunction = new AddFunction();

    @Override
    public BigRational apply(final List<BigRational> parameters) {
        if (parameters.size() < 2) { throw new EvaluationException("Add function must have at least two parameters"); }
        if (parameters.size() == 2) {
            return addFunction.apply(parameters.get(0), parameters.get(1));
        }
        BigRational sum = BigRational.ZERO;
        for (final BigRational parameter : parameters) {
            sum = addFunction.apply(sum, parameter);
        }
        return sum;
    }

}
