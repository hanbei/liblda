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
package com.assembla.liblda.io;

import com.assembla.liblda.Alphabet;
import com.assembla.liblda.io.itemwriter.StringItemReader;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.StringReader;

import static junit.framework.Assert.assertEquals;

/**
 */
public class AlphabetReaderTest {

    private static final String TEST1 = "Test1";
    private static final String TEST2 = "Test2";
    private static final String TEST3 = "Test3";
    private static final String TEST4 = "Test4";

    private AlphabetReader<String> alphabetReader;
    private Alphabet<String> alphabet;
    private String expectedRead;

    @Before
    public void setUp() throws Exception {
        alphabet = new Alphabet<String>();
        alphabet.add(TEST1);
        alphabet.add(TEST2);
        alphabet.add(TEST3);
        alphabet.add(TEST4);

        alphabetReader = new AlphabetReader<String>(new StringItemReader());
        expectedRead = TEST1 + TestConstants.LINE_SEPARATOR + "0" + TestConstants.LINE_SEPARATOR + TEST2 + TestConstants.LINE_SEPARATOR + "1" + TestConstants.LINE_SEPARATOR + TEST3 + TestConstants.LINE_SEPARATOR + "2" + TestConstants.LINE_SEPARATOR + TEST4 + TestConstants.LINE_SEPARATOR + "3" + TestConstants.LINE_SEPARATOR;
    }

    @Test
    public void testReadWithInputStream() throws Exception {
        ByteArrayInputStream bytesIn = new ByteArrayInputStream(expectedRead.getBytes());
        assertEquals(alphabet,alphabetReader.read(bytesIn));
    }

    @Test
    public void testReadWithReader() throws Exception {
        StringReader reader = new StringReader(expectedRead);
        assertEquals(alphabet,alphabetReader.read(reader));

    }
}
