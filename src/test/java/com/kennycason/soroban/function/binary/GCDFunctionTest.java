package com.kennycason.soroban.function.binary;

import com.kennycason.soroban.function.binary.arithmetic.GcdFunction;
import com.kennycason.soroban.number.BigRational;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

/**
 * Created by kenny on 10/1/16.
 */
public class GCDFunctionTest {
    private static final GcdFunction GCD_FUNCTION = new GcdFunction();

    @Test
    public void integer() {
        final BigRational left = new BigRational(BigInteger.valueOf(16));
        final BigRational right = new BigRational(BigInteger.valueOf(4));

        assertEquals(new BigRational(4), GCD_FUNCTION.apply(left, right));

        final BigRational right2 = new BigRational(BigInteger.valueOf(5));
        assertEquals(BigRational.ONE, GCD_FUNCTION.apply(left, right2));
    }

}
