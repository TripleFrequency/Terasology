package org.terasology.utilities.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;

public class CharSequenceIteratorTest {
    private CharSequenceIterator sut;

    @Before
    public void setup() {
        CharSequence sequence = "Hello, World!";
        sut = new CharSequenceIterator(sequence);
    }

    @Test
    public void ifIteratorExists_whenHasNext_thenReturnHasNext() {
        assert(sut.hasNext());
    }

    @Test
    public void ifIteratorHasNext_whenNextChar_thenReturnNextChar() {
        assertEquals((Character) 'H', sut.next());
        assertEquals((Character) 'e', sut.next());
        assertEquals('l', sut.nextChar());
        assertEquals('l', sut.nextChar());
        assertEquals('o', sut.nextChar());
        assertEquals(',', sut.nextChar());
        assertEquals(' ', sut.nextChar());
        assertEquals('W', sut.nextChar());
        assertEquals((Character) 'o', sut.next());
        assertEquals((Character) 'r', sut.next());
        assertEquals((Character) 'l', sut.next());
        assertEquals((Character) 'd', sut.next());
        assertEquals('!', sut.nextChar());
    }

    @Test(expected = NoSuchElementException.class)
    public void ifAtEndOfSequence_whenNext_throwOutOfBoundsException() {
        for (int i = 0; i < 13; i++) {
            sut.next();
        }
        sut.nextChar();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void ifIteratorExists_whenRemove_thenThrowException() {
        sut.remove();
    }
}
