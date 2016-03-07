package com.kennycason.soroban.function.unary.arithmetic;

import com.kennycason.soroban.eval.exception.EvaluationException;
import com.kennycason.soroban.function.unary.UnaryFunction;
import com.kennycason.soroban.number.BigRational;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by kenny on 3/1/16.
 */
public class FactorialFunction implements UnaryFunction {

    @Override
    public BigRational apply(final BigRational input) {
        if (input.isFraction()) {
            return applyToFraction(input);
        }
        return applyToDecimal(input);
    }

    private BigRational applyToFraction(final BigRational input) {
        if (input.getDenominator().equals(BigInteger.ONE)) {
            return applyToWholeNumber(input.getNumerator());
        }
        throw new EvaluationException("Can only perform factorial on integers for now");
    }

    // TODO implement later
    private static BigRational applyToDecimal(final BigRational input) {
        if (input.getValue().signum() == 0) {
            return applyToWholeNumber(input.getValue());
        }
        throw new EvaluationException("Can only perform factorial on integers for now");
    }

    private static BigRational applyToWholeNumber(final BigDecimal input) {
        BigDecimal n = input.subtract(BigDecimal.ONE);

        BigDecimal factorial = input;
        while (n.compareTo(BigDecimal.ONE) > 0) {
            factorial = factorial.multiply(n);
            n = n.subtract(BigDecimal.ONE);
        }
        return new BigRational(factorial);
    }

    private static BigRational applyToWholeNumber(final BigInteger input) {
        BigInteger n = input.subtract(BigInteger.ONE);

        BigInteger factorial = input;
        while (n.compareTo(BigInteger.ONE) > 0) {
            factorial = factorial.multiply(n);
            n = n.subtract(BigInteger.ONE);
        }
        return new BigRational(factorial);
    }
}
