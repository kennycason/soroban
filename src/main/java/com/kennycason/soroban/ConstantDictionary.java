package com.kennycason.soroban;

import com.kennycason.soroban.number.BigRational;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kenny on 3/6/16.
 *
 * An extensible constant dictionary with presets
 */
public class ConstantDictionary {
    private static final Map<String, BigRational> CONSTANTS = new HashMap<>();
    static {
        set("PI", new BigRational(Math.PI));
        set("E", new BigRational(Math.E));
    }

    private ConstantDictionary() {}

    public static void set(final String constant, final BigRational value) {
        CONSTANTS.put(constant, value);
    }

    public static boolean isSet(final String constant) {
        return CONSTANTS.containsKey(constant);
    }

    public static BigRational get(final String constant) {
        return CONSTANTS.get(constant);
    }

}
