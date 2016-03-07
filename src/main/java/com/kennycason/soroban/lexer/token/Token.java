package com.kennycason.soroban.lexer.token;

/**
 * Created by kenny on 2/29/16.
 */
public class Token {
    private final String value;
    private final TokenType type;

    public Token(final String value, final TokenType type) {
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public TokenType getType() {
        return type;
    }
}
