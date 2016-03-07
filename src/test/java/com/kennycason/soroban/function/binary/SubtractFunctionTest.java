package com.kennycason.soroban.function.binary;

import com.kennycason.soroban.function.binary.arithmetic.SubtractFunction;
import com.kennycason.soroban.number.BigRational;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.*;

/**
 * Created by kenny on 3/6/16.
 */
public class SubtractFunctionTest {

    private static final double DELTA = 0.0000000005;

    @Test
    public void fraction() {
        final SubtractFunction subtractFunction = new SubtractFunction();
        final BigRational left = new BigRational(BigInteger.valueOf(5), BigInteger.valueOf(3));
        final BigRational right = new BigRational(BigInteger.valueOf(4));

        final BigRational difference = subtractFunction.apply(left, right);
        assertEquals(BigInteger.valueOf(-7), difference.getNumerator());
        assertEquals(BigInteger.valueOf(3), difference.getDenominator());
        assertEquals(BigDecimal.valueOf(5/3.0 - 4).doubleValue(), difference.getValue().doubleValue(), DELTA);
    }

    @Test
    public void simpleFraction() {
        final SubtractFunction subtractFunction = new SubtractFunction();
        final BigRational left = new BigRational(BigInteger.valueOf(5), BigInteger.valueOf(3));
        final BigRational right = new BigRational(BigInteger.valueOf(2), BigInteger.valueOf(3));

        final BigRational difference = subtractFunction.apply(left, right);
        assertEquals(BigInteger.valueOf(3), difference.getNumerator());
        assertEquals(BigInteger.valueOf(3), difference.getDenominator());
        assertEquals(BigDecimal.valueOf(5/3.0 - 2/3.0).doubleValue(), difference.getValue().doubleValue(), DELTA);
    }

    @Test
    public void decimal() {
        final SubtractFunction subtractFunction = new SubtractFunction();
        final BigRational left = new BigRational(BigDecimal.valueOf(0.5));
        final BigRational right = new BigRational(BigInteger.valueOf(4));

        final BigRational difference = subtractFunction.apply(left, right);
        assertFalse(difference.isFraction());
        assertNull(difference.getNumerator());
        assertNull(difference.getDenominator());
        assertEquals(BigDecimal.valueOf(-3.5), difference.getValue());
    }
}
