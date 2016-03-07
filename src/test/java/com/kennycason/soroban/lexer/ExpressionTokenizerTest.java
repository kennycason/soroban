package com.kennycason.soroban.lexer;

import com.kennycason.soroban.lexer.exception.LexerException;
import com.kennycason.soroban.lexer.token.Token;
import com.kennycason.soroban.lexer.token.TokenType;
import com.kennycason.soroban.lexer.tokenizer.CharacterStream;
import com.kennycason.soroban.lexer.tokenizer.ExpressionTokenizer;
import org.junit.Test;

/**
 * Created by kenny on 3/1/16.
 */
public class ExpressionTokenizerTest {

    private final ExpressionTokenizer expressionTokenizer = new ExpressionTokenizer();

    @Test
    public void basic() {
        TokenTestUtils.assertValid(expressionTokenizer.tokenize(new CharacterStream("1 + 1")),
                new Token("1", TokenType.NUMBER),
                new Token("+", TokenType.PLUS),
                new Token("1", TokenType.NUMBER)
        );

        TokenTestUtils.assertValid(expressionTokenizer.tokenize(new CharacterStream("1 + 1 / 30")),
                new Token("1", TokenType.NUMBER),
                new Token("+", TokenType.PLUS),
                new Token("1", TokenType.NUMBER),
                new Token("/", TokenType.DIVIDE),
                new Token("30", TokenType.NUMBER)
        );

        TokenTestUtils.assertValid(expressionTokenizer.tokenize(new CharacterStream("sin(10)")),
                new Token("sin", TokenType.STRING),
                new Token("(", TokenType.LEFT_PAREN),
                new Token("10", TokenType.NUMBER),
                new Token(")", TokenType.RIGHT_PAREN)
        );

        TokenTestUtils.assertValid(expressionTokenizer.tokenize(new CharacterStream("log10(10)")),
                new Token("log10", TokenType.STRING),
                new Token("(", TokenType.LEFT_PAREN),
                new Token("10", TokenType.NUMBER),
                new Token(")", TokenType.RIGHT_PAREN)
        );
    }

    @Test
    public void postfix() {
        TokenTestUtils.assertValid(expressionTokenizer.tokenize(new CharacterStream("n!")),
                new Token("n", TokenType.STRING),
                new Token("!", TokenType.EXCLAMATION)
        );
        TokenTestUtils.assertValid(expressionTokenizer.tokenize(new CharacterStream("10!")),
                new Token("10", TokenType.NUMBER),
                new Token("!", TokenType.EXCLAMATION)
        );
    }

    // if unknown should throw exception
    @Test(expected = LexerException.class)
    public void unknownPostfix() {
        expressionTokenizer.tokenize(new CharacterStream("n#"));
    }

    @Test
    public void fullExpression() {
        TokenTestUtils.assertValid(expressionTokenizer.tokenize(new CharacterStream("(pi ^ 2) / x * -2y")),
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
        TokenTestUtils.assertValid(expressionTokenizer.tokenize(new CharacterStream("0b1010101 + 0x99ddff - 0xABCDEF * 0b1")),
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
