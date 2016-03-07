package com.kennycason.soroban.lexer.tokenizer;

import com.kennycason.soroban.lexer.exception.LexerException;
import com.kennycason.soroban.lexer.token.Token;
import com.kennycason.soroban.lexer.token.TokenType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kenny on 2/29/16.
 */
public class ExpressionTokenizer {
    private final NumberTokenizer numberTokenizer = new NumberTokenizer();

    public List<Token> tokenize(final CharacterStream tokenStream) {
        final List<Token> tokens = new ArrayList<>();

        while (tokenStream.hasNext()) {
            final char token = Character.toLowerCase(tokenStream.peek());

            switch (token) {
                case '(':
                    tokens.add(new Token(String.valueOf(tokenStream.next()), TokenType.LEFT_PAREN));
                    continue;
                case ')':
                    tokens.add(new Token(String.valueOf(tokenStream.next()), TokenType.RIGHT_PAREN));
                    continue;
                case '+':
                    tokens.add(new Token(String.valueOf(tokenStream.next()), TokenType.PLUS));
                    continue;
                case '-':
                    tokens.add(new Token(String.valueOf(tokenStream.next()), TokenType.MINUS));
                    continue;
                case '*':
                    tokens.add(new Token(String.valueOf(tokenStream.next()), TokenType.MULTIPLY));
                    continue;
                case '/':
                    tokens.add(new Token(String.valueOf(tokenStream.next()), TokenType.DIVIDE));
                    continue;
                case '!':
                    tokens.add(new Token(String.valueOf(tokenStream.next()), TokenType.EXCLAMATION));
                    continue;
                case '^':
                    tokens.add(new Token(String.valueOf(tokenStream.next()), TokenType.EXPONENT));
                    continue;
                case ',':
                    tokens.add(new Token(String.valueOf(tokenStream.next()), TokenType.COMMA));
                    continue;
                case ' ':
                    tokenStream.next(); // ignore token
                    continue;
            }
            if (Character.isDigit(token)
                        || token == '.') {
                tokens.add(numberTokenizer.tokenize(tokenStream));
            }
            else if (Character.isAlphabetic(token) || Character.isDigit(token)) {
                tokens.add(consumeString(tokenStream));
            }
            else {
                throw new LexerException("Found unrecognized character [" + token + "]");
            }
        }

        return tokens;
    }

    private Token consumeString(final CharacterStream tokenStream) {
        final StringBuilder stringBuilder = new StringBuilder();

        while (tokenStream.hasNext()) {
            if (Character.isAlphabetic(tokenStream.peek())) {
                stringBuilder.append(tokenStream.next());

            } else if (Character.isDigit(tokenStream.peek())) {
                stringBuilder.append(tokenStream.next());

            } else {
                break;
            }
        }
        return new Token(stringBuilder.toString(), TokenType.STRING);
    }

}
