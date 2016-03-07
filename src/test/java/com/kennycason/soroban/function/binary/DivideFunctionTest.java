package com.kennycason.soroban.function.binary;

import com.kennycason.soroban.function.binary.arithmetic.DivideFunction;
import com.kennycason.soroban.number.BigRational;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.*;

/**
 * Created by kenny on 3/6/16.
 */
public class DivideFunctionTest {

    private static final double DELTA = 0.0000000005;

    @Test
    public void fraction() {
        final DivideFunction divideFunction = new DivideFunction();
        final BigRational left = new BigRational(BigInteger.valueOf(4), BigInteger.valueOf(2));
        final BigRational right = new BigRational(BigInteger.valueOf(2), BigInteger.valueOf(4));

        final BigRational divided = divideFunction.apply(left, right);
        assertEquals(BigInteger.valueOf(16), divided.getNumerator());
        assertEquals(BigInteger.valueOf(4), divided.getDenominator());
        assertEquals(BigDecimal.valueOf(4.0).doubleValue(), divided.getValue().doubleValue(), DELTA);
    }

    @Test
    public void decimal() {
        final DivideFunction divideFunction = new DivideFunction();
        final BigRational left = new BigRational(BigDecimal.valueOf(4));
        final BigRational right = new BigRational(BigInteger.valueOf(2));

        final BigRational divided = divideFunction.apply(left, right);
        assertFalse(divided.isFraction());
        assertNull(divided.getNumerator());
        assertNull(divided.getDenominator());
        assertEquals(BigDecimal.valueOf(2), divided.getValue());
    }

    @Ignore
    public void divideByZero() {
        final DivideFunction divideFunction = new DivideFunction();
        final BigRational left = new BigRational(BigDecimal.ONE);
        final BigRational right = new BigRational(BigInteger.ZERO);

        final BigRational divided = divideFunction.apply(left, right);
    }

}
