package com.kennycason.soroban.function.unary;

import com.kennycason.soroban.function.unary.arithmetic.NegativeFunction;
import com.kennycason.soroban.number.BigRational;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

/**
 * Created by kenny on 3/6/16.
 */
public class NegativeFunctionTest {

    @Test
    public void fraction() {
        final NegativeFunction negativeFunction = new NegativeFunction();
        final BigRational rational = new BigRational(BigInteger.valueOf(1), BigInteger.valueOf(4));
        final BigRational negative = negativeFunction.apply(rational);
        assertEquals(BigInteger.valueOf(-1), negative.getNumerator());
        assertEquals(BigInteger.valueOf(4), negative.getDenominator());
        assertEquals(BigDecimal.valueOf(-1/4.0), negative.getValue());
    }

    @Test
    public void decimal() {
        final NegativeFunction negativeFunction = new NegativeFunction();
        final BigRational rational = new BigRational(BigDecimal.valueOf(-0.25));
        final BigRational negative = negativeFunction.apply(rational);
        assertEquals(BigDecimal.valueOf(0.25), negative.getValue());
    }
}
