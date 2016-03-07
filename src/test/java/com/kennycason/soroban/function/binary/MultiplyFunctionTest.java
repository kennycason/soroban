package com.kennycason.soroban.function.binary;

import com.kennycason.soroban.function.binary.arithmetic.MultiplyFunction;
import com.kennycason.soroban.number.BigRational;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.*;

/**
 * Created by kenny on 3/6/16.
 */
public class MultiplyFunctionTest {

    private static final double DELTA = 0.0000000005;

    @Test
    public void fraction() {
        final MultiplyFunction multiplyFunction = new MultiplyFunction();
        final BigRational left = new BigRational(BigInteger.valueOf(5), BigInteger.valueOf(3));
        final BigRational right = new BigRational(BigInteger.valueOf(4));

        final BigRational product = multiplyFunction.apply(left, right);
        assertEquals(BigInteger.valueOf(20), product.getNumerator());
        assertEquals(BigInteger.valueOf(3), product.getDenominator());
        assertEquals(BigDecimal.valueOf(20 / 3.0).doubleValue(), product.getValue().doubleValue(), DELTA);
    }

    @Test
    public void decimal() {
        final MultiplyFunction multiplyFunction = new MultiplyFunction();
        final BigRational left = new BigRational(BigDecimal.valueOf(0.5));
        final BigRational right = new BigRational(BigInteger.valueOf(4));

        final BigRational product = multiplyFunction.apply(left, right);
        assertFalse(product.isFraction());
        assertNull(product.getNumerator());
        assertNull(product.getDenominator());
        assertEquals(BigDecimal.valueOf(2.0), product.getValue());
    }

}
