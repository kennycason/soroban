package com.kennycason.soroban.lexer.tokenizer;

import com.kennycason.soroban.lexer.token.NumberToken;
import com.kennycason.soroban.lexer.token.NumberToken.Base;
import com.kennycason.soroban.lexer.token.Token;

/**
 * Created by kenny on 2/29/16.
 */
public class HexadecimalTokenizer {

    public Token tokenize(final CharacterStream tokenStream) {
        final StringBuilder stringBuilder = new StringBuilder();
        while (tokenStream.hasNext()) {
            switch (Character.toLowerCase(tokenStream.peek())) {
                case 'a': case 'b': case 'c':
                case 'd': case 'e': case 'f':
                case '0': case '1': case '2': case '3': case '4':
                case '5': case '6': case '7': case '8': case '9':
                    stringBuilder.append(Character.toLowerCase(tokenStream.next()));
                    break;

                default:
                    return new NumberToken(stringBuilder.toString(), Base.HEXADECIMAL);
            }
        }
        return new NumberToken(stringBuilder.toString(), Base.HEXADECIMAL);
    }
}
