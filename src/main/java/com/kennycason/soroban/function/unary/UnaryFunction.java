package com.kennycason.soroban.function.unary;

import com.kennycason.soroban.number.BigRational;

/**
 * Created by kenny on 3/1/16.
 */
public interface UnaryFunction {
    BigRational apply(BigRational input);
}
