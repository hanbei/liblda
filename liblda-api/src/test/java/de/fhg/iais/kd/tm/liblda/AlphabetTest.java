package de.fhg.iais.kd.tm.liblda;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AlphabetTest {

    private Alphabet<String> alphabet;

    @Before
    public void setUp() {
        alphabet = new Alphabet<String>();
    }

    @Test
    public void testPutLookup() {
        alphabet.add("test1");
        alphabet.add("test2");

        assertEquals(0, alphabet.lookupIndex("test1"));
        assertEquals(1, alphabet.lookupIndex("test2"));

        assertEquals("test1", alphabet.lookupFeature(0));
        assertEquals("test2", alphabet.lookupFeature(1));
    }

    @Test
    public void testAddReturnsIndex() {
        assertEquals(0, alphabet.add("test1"));
        assertEquals(0, alphabet.add("test1"));

        assertEquals(1, alphabet.add("test2"));
        assertEquals(1, alphabet.add("test2"));
        assertEquals(1, alphabet.add("test2"));
    }

    @Test
    public void testSize() {
        assertEquals(0, alphabet.size());
        alphabet.add("test1");
        assertEquals(1, alphabet.size());
        alphabet.add("test1");
        assertEquals(1, alphabet.size());
        alphabet.add("test2");
        assertEquals(2, alphabet.size());
    }

}
