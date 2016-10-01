package com.kennycason.soroban.function.poly;

import com.kennycason.soroban.function.poly.arithmetic.MultiplyPolyFunction;
import com.kennycason.soroban.number.BigRational;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by kenny on 3/6/16.
 */
public class MultiplyPolyFunctionTest {

    @Test
    public void threeItems() {
        final MultiplyPolyFunction multiplePolyFunction = new MultiplyPolyFunction();
        final BigRational two = new BigRational(BigInteger.valueOf(2));
        final BigRational three = new BigRational(BigInteger.valueOf(3));
        final BigRational four = new BigRational(BigInteger.valueOf(4));

        final BigRational sum = multiplePolyFunction.apply(Arrays.asList(two, three, four));
        assertEquals(BigInteger.valueOf(24), sum.getNumerator());
        assertEquals(BigInteger.valueOf(1), sum.getDenominator());
        assertEquals(BigDecimal.valueOf(24), sum.getValue());
    }

}
