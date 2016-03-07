package com.kennycason.soroban.lexer;

import com.kennycason.soroban.lexer.token.Token;
import com.kennycason.soroban.lexer.token.TokenType;
import com.kennycason.soroban.lexer.tokenizer.BinaryTokenizer;
import com.kennycason.soroban.lexer.tokenizer.CharacterStream;
import org.junit.Test;

/**
 * Created by kenny on 3/1/16.
 */
public class BinaryTokenizerTest {

    private final BinaryTokenizer binaryTokenizer = new BinaryTokenizer();

    @Test
    public void basic() {
        TokenTestUtils.assertValid(binaryTokenizer.tokenize(new CharacterStream("1111000")),
                new Token("1111000", TokenType.NUMBER)
        );

        TokenTestUtils.assertValid(binaryTokenizer.tokenize(new CharacterStream("10101 + ")),
                new Token("10101", TokenType.NUMBER)
        );
    }

}
