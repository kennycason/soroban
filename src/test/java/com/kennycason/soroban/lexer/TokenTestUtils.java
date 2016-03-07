package com.kennycason.soroban.lexer;

import com.kennycason.soroban.lexer.token.Token;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by kenny on 3/1/16.
 */
public class TokenTestUtils {

    public static void assertValid(final List<Token> actual, final Token... expected) {
        assertEquals(expected.length, actual.size());
        for (int i = 0; i < actual.size(); i++) {
            assertEquals(expected[i].getType(), actual.get(i).getType());
            assertEquals(expected[i].getValue(), actual.get(i).getValue());
        }
    }

    public static void assertValid(final Token actual, final Token expected) {
        assertEquals(expected.getType(), actual.getType());
        assertEquals(expected.getValue(), actual.getValue());
    }
}
