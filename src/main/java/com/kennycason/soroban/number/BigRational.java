package com.kennycason.soroban.number;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Created by kenny on 3/6/16.
 */
public class BigRational {
    public static final BigRational ZERO = new BigRational(BigInteger.ZERO);
    public static final BigRational ONE = new BigRational(BigInteger.ONE);

    private final BigInteger numerator;
    private final BigInteger denominator;
    private final BigDecimal value;

    public BigRational(final double value) {
        this(BigDecimal.valueOf(value));
    }

    public BigRational(final BigDecimal value) {
        this.numerator = null;
        this.denominator = null;
        this.value = value;
    }

    public BigRational(final long value) {
        this(BigInteger.valueOf(value));
    }

    public BigRational(final BigInteger value) {
        this.numerator = value;
        this.denominator = BigInteger.ONE;
        this.value = new BigDecimal(value);
    }


    public BigRational(final BigInteger numerator, final BigInteger denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        this.value = new BigDecimal(this.numerator)
                .divide(new BigDecimal(denominator), new MathContext(20, RoundingMode.HALF_UP));
    }

    public BigInteger getDenominator() {
        return denominator;
    }

    public BigInteger getNumerator() {
        return numerator;
    }

    public BigDecimal getValue() {
        return value;
    }

    public boolean isFraction() {
        return numerator != null && denominator != null;
    }

    @Override
    public boolean equals(Object o) {
        final BigRational compareTo = (BigRational) o;
        if (isFraction()) {
            return numerator.equals(compareTo.numerator)
                    && denominator.equals(compareTo.denominator);
        }
        return value.equals(compareTo.value);
    }

    @Override
    public String toString() {
        if (isFraction()) {
            if (denominator.equals(BigInteger.ONE)) {
                return numerator.toString();
            }
            return numerator + "/" + denominator;
        }
        return value.toString();
    }

}
