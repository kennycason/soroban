package com.kennycason.soroban.lexer.token;

/**
 * Created by kenny on 3/1/16.
 */
public class NumberToken extends Token {
    public enum Base {
        BINARY,
        TEN,
        HEXADECIMAL
    }

    private final Base base;

    public NumberToken(final String value, final Base base) {
        super(value, TokenType.NUMBER);
        this.base = base;
    }

    public Base getBase() {
        return base;
    }

    public boolean isDecimal() {
        return getValue().contains(".");
    }

}
