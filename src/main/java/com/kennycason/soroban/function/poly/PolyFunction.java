package com.kennycason.soroban.function.poly;

import com.kennycason.soroban.number.BigRational;

import java.util.List;

/**
 * Created by kenny on 3/6/16.
 */
public interface PolyFunction {
    BigRational apply(final List<BigRational> parameters);
}
