package com.kennycason.soroban.number;

import java.math.BigInteger;

/**
 * Created by kenny on 3/6/16.
 */
public class BigArithmetic {

    public static BigInteger lcm(final BigInteger left, final BigInteger right) {
        return left.multiply(right).divide(left.gcd(right));
    }
}
