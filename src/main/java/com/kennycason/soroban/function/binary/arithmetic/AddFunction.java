package com.kennycason.soroban.function.binary.arithmetic;

import com.kennycason.soroban.function.binary.BinaryFunction;
import com.kennycason.soroban.number.BigArithmetic;
import com.kennycason.soroban.number.BigRational;

import java.math.BigInteger;

/**
 * Created by kenny on 3/1/16.
 */
public class AddFunction implements BinaryFunction {

    @Override
    public BigRational apply(final BigRational left, final BigRational right) {
        if (left.isFraction() && right.isFraction()) {
            return addFractions(left, right);
        }
        return new BigRational(left.getValue().add(right.getValue()));
    }

    private BigRational addFractions(final BigRational left, final BigRational right) {
        final BigInteger lcm = BigArithmetic.lcm(left.getDenominator(), right.getDenominator());
        final BigInteger leftNumerator = left.getNumerator().multiply(lcm.divide(left.getDenominator()));
        final BigInteger rightNumerator = right.getNumerator().multiply(lcm.divide(right.getDenominator()));
        return new BigRational(
                leftNumerator.add(rightNumerator),
                lcm
        );
    }

}
