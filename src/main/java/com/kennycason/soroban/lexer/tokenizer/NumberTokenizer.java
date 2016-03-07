package com.kennycason.soroban.lexer.tokenizer;

import com.kennycason.soroban.lexer.token.NumberToken;
import com.kennycason.soroban.lexer.token.NumberToken.Base;
import com.kennycason.soroban.lexer.token.Token;

/**
 * Created by kenny on 2/29/16.
 */
public class NumberTokenizer {
    private BinaryTokenizer binaryTokenizer = new BinaryTokenizer();
    private HexadecimalTokenizer hexadecimalTokenizer = new HexadecimalTokenizer();

    public Token tokenize(final CharacterStream tokenStream) {
        final StringBuilder stringBuilder = new StringBuilder();

        final char firstChar = tokenStream.next();
        // handle special cases of hex/binary
        if (firstChar == '0') {
            switch (tokenStream.peek()) {
                case 'b':
                    tokenStream.next(); // throw away token
                    return binaryTokenizer.tokenize(tokenStream);

                case 'x':
                    tokenStream.next(); // throw away token
                    return hexadecimalTokenizer.tokenize(tokenStream);
            }
        }
        stringBuilder.append(firstChar);
        while (tokenStream.hasNext()) {
            switch (tokenStream.peek()) {
                case '0': case '1': case '2': case '3': case '4':
                case '5': case '6': case '7': case '8': case '9':
                case '.':
                    stringBuilder.append(tokenStream.next());
                    break;

                default:
                    return new NumberToken(stringBuilder.toString(), Base.TEN);
            }
        }
        return new NumberToken(stringBuilder.toString(), Base.TEN);
    }

}
