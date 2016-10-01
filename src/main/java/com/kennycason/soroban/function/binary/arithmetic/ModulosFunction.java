package com.kennycason.soroban.function.binary.arithmetic;

import com.kennycason.soroban.eval.exception.EvaluationException;
import com.kennycason.soroban.function.binary.BinaryFunction;
import com.kennycason.soroban.number.BigRational;

import java.math.BigInteger;

/**
 * Created by kenny on 3/1/16.
 */
public class ModulosFunction implements BinaryFunction {

    @Override
    public BigRational apply(final BigRational left, final BigRational right) {
        if (!(left.isFraction() && right.isFraction())) {
            throw new EvaluationException("Modulos can only be applied to integers");
        }
        if (!(left.getDenominator().equals(BigInteger.ONE) && right.getDenominator().equals(BigInteger.ONE))) {
            throw new EvaluationException("Modulos can only be applied to integers");
        }
        return new BigRational(
                left.getNumerator().mod(right.getNumerator()));
    }

}
