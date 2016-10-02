package com.kennycason.soroban.function.unary.arithmetic;

import com.kennycason.soroban.function.unary.UnaryFunction;
import com.kennycason.soroban.number.BigRational;

import java.math.BigInteger;

/**
 * Created by kenny on 3/1/16.
 */
public class ReduceFractionFunction implements UnaryFunction {

    @Override
    public BigRational apply(final BigRational input) {
        if (!input.isFraction()) { return input; }
        if (input.getDenominator().equals(BigInteger.ONE)) { return input; }

        final BigInteger gcd = input.getNumerator().gcd(input.getDenominator());
        return new BigRational(input.getNumerator().divide(gcd), input.getDenominator().divide(gcd));
    }

}
