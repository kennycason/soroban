package com.kennycason.soroban.function.unary;

import com.kennycason.soroban.function.unary.arithmetic.ReduceFractionFunction;
import com.kennycason.soroban.number.BigRational;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

/**
 * Created by kenny on 3/6/16.
 */
public class ReduceFractionFunctionTest {
    private static final ReduceFractionFunction REDUCE_FRACTION_FUNCTION = new ReduceFractionFunction();

    @Test
    public void fraction() {
        final BigRational rational = new BigRational(BigInteger.valueOf(675), BigInteger.valueOf(15));
        final BigRational simplified = new BigRational(45);

        assertEquals(simplified, REDUCE_FRACTION_FUNCTION.apply(rational));
    }

}
