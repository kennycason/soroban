package com.kennycason.soroban.lexer;


import com.kennycason.soroban.lexer.exception.EndOfStreamException;
import com.kennycason.soroban.lexer.tokenizer.CharacterStream;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by kenny on 1/26/16.
 */
public class CharacterStreamTest {

    @Test
    public void basicTest() {
        final CharacterStream tokenStream = new CharacterStream("abc");
        assertTrue(tokenStream.hasNext());
        assertEquals('a', tokenStream.peek());
        assertEquals('a', tokenStream.peek()); // peeking does not increment pointer
        assertEquals('a', tokenStream.next()); // get next token and increment internal pointer
        assertEquals('b', tokenStream.peek()); // verify pointer was incremented
        assertEquals('b', tokenStream.next()); // get next
        assertEquals('c', tokenStream.next()); // get next
        assertFalse(tokenStream.hasNext());    // stream should be consumed
    }

    @Test(expected = EndOfStreamException.class)
    public void endOfStream() {
        final CharacterStream tokenStream = new CharacterStream("a");
        tokenStream.next();
        assertFalse(tokenStream.hasNext());
        tokenStream.next(); // should throw exception
    }

    @Test
    public void reset() {
        final CharacterStream tokenStream = new CharacterStream("a");
        tokenStream.next();
        assertFalse(tokenStream.hasNext());
        tokenStream.reset();
        assertTrue(tokenStream.hasNext());
        assertEquals('a', tokenStream.peek());
    }

}
