package com.kennycason.soroban.function.binary;

import com.kennycason.soroban.function.binary.arithmetic.AddFunction;
import com.kennycason.soroban.number.BigRational;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

/**
 * Created by kenny on 3/6/16.
 */
public class AddFunctionTest {

    private static final double DELTA = 0.0000000005;

    @Test
    public void fraction() {
        final AddFunction addFunction = new AddFunction();
        final BigRational left = new BigRational(BigInteger.valueOf(5), BigInteger.valueOf(3));
        final BigRational right = new BigRational(BigInteger.valueOf(4));

        final BigRational sum = addFunction.apply(left, right);
        assertEquals(BigInteger.valueOf(17), sum.getNumerator());
        assertEquals(BigInteger.valueOf(3), sum.getDenominator());
        assertEquals(BigDecimal.valueOf(5/3.0 + 4).doubleValue(), sum.getValue().doubleValue(), DELTA);
    }

    @Test
    public void decimal() {
        final AddFunction addFunction = new AddFunction();
        final BigRational left = new BigRational(BigDecimal.valueOf(0.5));
        final BigRational right = new BigRational(BigInteger.valueOf(4));

        final BigRational sum = addFunction.apply(left, right);
        assertFalse(sum.isFraction());
        assertNull(sum.getNumerator());
        assertNull(sum.getDenominator());
        assertEquals(BigDecimal.valueOf(4.5), sum.getValue());
    }
}
