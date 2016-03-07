package com.kennycason.soroban.lexer.exception;

/**
 * Created by kenny on 2/29/16.
 */
public class EndOfStreamException extends LexerException {
    public EndOfStreamException() {
        super("No tokens remain in stream");
    }
}
