package com.kennycason.soroban.function.binary;

import com.kennycason.soroban.function.binary.arithmetic.LcmFunction;
import com.kennycason.soroban.number.BigRational;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

/**
 * Created by kenny on 10/1/16.
 */
public class LCMFunctionTest {
    private static final LcmFunction LCM_FUNCTION = new LcmFunction();

    @Test
    public void integer() {
        final BigRational left = new BigRational(BigInteger.valueOf(5));
        final BigRational right = new BigRational(BigInteger.valueOf(4));

        final BigRational gcd = LCM_FUNCTION.apply(left, right);
        assertEquals(new BigRational(20), gcd);
    }

}
