package com.kennycason.soroban.function.binary;

import com.kennycason.soroban.number.BigRational;

/**
 * Created by kenny on 3/1/16.
 */
public interface BinaryFunction {
    BigRational apply(BigRational left, BigRational right);
}
