package com.kennycason.soroban.lexer;

import com.kennycason.soroban.lexer.token.Token;
import com.kennycason.soroban.lexer.token.TokenType;
import com.kennycason.soroban.lexer.tokenizer.HexadecimalTokenizer;
import com.kennycason.soroban.lexer.tokenizer.CharacterStream;
import org.junit.Test;

/**
 * Created by kenny on 3/1/16.
 */
public class HexadecimalTokenizerTest {

    private final HexadecimalTokenizer hexadecimalTokenizer = new HexadecimalTokenizer();

    @Test
    public void basic() {
        TokenTestUtils.assertValid(hexadecimalTokenizer.tokenize(new CharacterStream("abcdef")),
                new Token("abcdef", TokenType.NUMBER)
        );

        TokenTestUtils.assertValid(hexadecimalTokenizer.tokenize(new CharacterStream("1234567890abcdef + ")),
                new Token("1234567890abcdef", TokenType.NUMBER)
        );
    }

}
