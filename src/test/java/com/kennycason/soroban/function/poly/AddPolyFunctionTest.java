package com.kennycason.soroban.function.poly;

import com.kennycason.soroban.function.poly.arithmetic.AddPolyFunction;
import com.kennycason.soroban.number.BigRational;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by kenny on 3/6/16.
 */
public class AddPolyFunctionTest {

    @Test
    public void threeItems() {
        final AddPolyFunction addFunction = new AddPolyFunction();
        final BigRational one = new BigRational(BigInteger.valueOf(1));
        final BigRational two = new BigRational(BigInteger.valueOf(2));
        final BigRational three = new BigRational(BigInteger.valueOf(3));

        final BigRational sum = addFunction.apply(Arrays.asList(one, two, three));
        assertEquals(BigInteger.valueOf(6), sum.getNumerator());
        assertEquals(BigInteger.valueOf(1), sum.getDenominator());
        assertEquals(BigDecimal.valueOf(6), sum.getValue());
    }

}
