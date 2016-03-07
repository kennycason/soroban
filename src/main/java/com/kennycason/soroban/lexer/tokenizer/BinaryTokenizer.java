package com.kennycason.soroban.lexer.tokenizer;

import com.kennycason.soroban.lexer.token.NumberToken;
import com.kennycason.soroban.lexer.token.NumberToken.Base;
import com.kennycason.soroban.lexer.token.Token;

/**
 * Created by kenny on 2/29/16.
 */
public class BinaryTokenizer {

    public Token tokenize(final CharacterStream tokenStream) {
        final StringBuilder stringBuilder = new StringBuilder();
        while (tokenStream.hasNext()) {
            switch (tokenStream.peek()) {
                case '0':
                case '1':
                    stringBuilder.append(tokenStream.next());
                    break;

                default:
                    return new NumberToken(stringBuilder.toString(), Base.BINARY);
            }
        }
        return new NumberToken(stringBuilder.toString(), Base.BINARY);
    }

}
