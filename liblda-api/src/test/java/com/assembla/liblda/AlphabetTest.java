/*
 *
 *  This file is part of liblda.
 *
 *  liblda is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  any later version.
 *
 *  liblda is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with liblda.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.assembla.liblda;

import com.assembla.liblda.Alphabet;
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
