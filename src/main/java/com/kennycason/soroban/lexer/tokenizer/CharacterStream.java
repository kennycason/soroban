package com.kennycason.soroban.lexer.tokenizer;


import com.kennycason.soroban.lexer.exception.EndOfStreamException;

/**
 * Created by kenny on 1/26/16.
 */
public class CharacterStream {

    private final String text;

    private int currentToken;

    public CharacterStream(final String text) {
        this.text = text;
        currentToken = 0;
    }

    public char next() {
        final char next = peek();
        currentToken++;
        return next;
    }

    public char peek() {
        if (currentToken == text.length()) {
            throw new EndOfStreamException();
        }
        return text.charAt(currentToken);
    }

    public boolean hasNext() {
        return currentToken < text.length();
    }

    public int getPosition() {
        return currentToken;
    }

    public void reset() {
        currentToken = 0;
    }

}
