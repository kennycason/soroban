package com.kennycason.soroban.parser;

/**
 * Created by kenny on 3/5/16.
 */
public class Precedence {
    public static final int PLUS          = 1;
    public static final int MULTIPLY      = 2;
    public static final int EXPONENT      = 3;
    public static final int PREFIX        = 4;
    public static final int POSTFIX       = 5;
    public static final int FUNCTION_CALL = 6;
}
