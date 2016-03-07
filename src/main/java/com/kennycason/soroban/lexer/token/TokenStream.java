package com.kennycason.soroban.lexer.token;


import com.kennycason.soroban.lexer.exception.EndOfStreamException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kenny on 3/2/16.
 */
public class TokenStream {

    private final List<Token> tokens = new ArrayList<>();

    private int currentToken;

    public TokenStream(final List<Token> tokens) {
        this.tokens.addAll(tokens);
        currentToken = 0;
    }

    public Token next() {
        final Token next = peek();
        currentToken++;
        return next;
    }

    public Token peek() {
        if (currentToken == tokens.size()) {
            throw new EndOfStreamException();
        }
        return tokens.get(currentToken);
    }

    public boolean hasNext() {
        return currentToken < tokens.size();
    }

    public int getPosition() {
        return currentToken;
    }

    public void reset() {
        currentToken = 0;
    }

}
