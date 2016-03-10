package com.kennycason.soroban.lexer;

import com.kennycason.soroban.Soroban;
import com.kennycason.soroban.lexer.exception.LexerException;
import com.kennycason.soroban.lexer.token.Token;
import com.kennycason.soroban.lexer.token.TokenType;
import org.junit.Test;

/**
 * Created by kenny on 3/1/16.
 */
public class ExpressionTokenizerTest {

    @Test
    public void basic() {
        TokenTestUtils.assertValid(Soroban.tokenize("1 + 1"),
                new Token("1", TokenType.NUMBER),
                new Token("+", TokenType.PLUS),
                new Token("1", TokenType.NUMBER)
        );

        TokenTestUtils.assertValid(Soroban.tokenize("1 + 1 / 30"),
                new Token("1", TokenType.NUMBER),
                new Token("+", TokenType.PLUS),
                new Token("1", TokenType.NUMBER),
                new Token("/", TokenType.DIVIDE),
                new Token("30", TokenType.NUMBER)
        );

        TokenTestUtils.assertValid(Soroban.tokenize("sin(10)"),
                new Token("sin", TokenType.STRING),
                new Token("(", TokenType.LEFT_PAREN),
                new Token("10", TokenType.NUMBER),
                new Token(")", TokenType.RIGHT_PAREN)
        );

        TokenTestUtils.assertValid(Soroban.tokenize("log10(10)"),
                new Token("log10", TokenType.STRING),
                new Token("(", TokenType.LEFT_PAREN),
                new Token("10", TokenType.NUMBER),
                new Token(")", TokenType.RIGHT_PAREN)
        );
    }

    @Test
    public void assignment() {
        TokenTestUtils.assertValid(Soroban.tokenize("a = 10"),
                new Token("a", TokenType.STRING),
                new Token("=", TokenType.ASSIGNMENT),
                new Token("10", TokenType.NUMBER)
        );
    }

    @Test
    public void postfix() {
        TokenTestUtils.assertValid(Soroban.tokenize("n!"),
                new Token("n", TokenType.STRING),
                new Token("!", TokenType.EXCLAMATION)
        );
        TokenTestUtils.assertValid(Soroban.tokenize("10!"),
                new Token("10", TokenType.NUMBER),
                new Token("!", TokenType.EXCLAMATION)
        );
    }

    // if unknown should throw exception
    @Test(expected = LexerException.class)
    public void unknownPostfix() {
        Soroban.tokenize("n#");
    }

    @Test
    public void fullExpression() {
        TokenTestUtils.assertValid(Soroban.tokenize("(pi ^ 2) / x * -2y"),
                new Token("(", TokenType.LEFT_PAREN),
                new Token("pi", TokenType.STRING),
                new Token("^", TokenType.EXPONENT),
                new Token("2", TokenType.NUMBER),
                new Token(")", TokenType.RIGHT_PAREN),
                new Token("/", TokenType.DIVIDE),
                new Token("x", TokenType.STRING),
                new Token("*", TokenType.MULTIPLY),
                new Token("-", TokenType.MINUS),
                new Token("2", TokenType.NUMBER),
                new Token("y", TokenType.STRING)
        );
    }

    @Test
    public void binaryAndHex() {
        TokenTestUtils.assertValid(Soroban.tokenize("0b1010101 + 0x99ddff - 0xABCDEF * 0b1"),
                new Token("1010101", TokenType.NUMBER),
                new Token("+", TokenType.PLUS),
                new Token("99ddff", TokenType.NUMBER),
                new Token("-", TokenType.MINUS),
                new Token("abcdef", TokenType.NUMBER),
                new Token("*", TokenType.MULTIPLY),
                new Token("1", TokenType.NUMBER)
        );
    }

}
