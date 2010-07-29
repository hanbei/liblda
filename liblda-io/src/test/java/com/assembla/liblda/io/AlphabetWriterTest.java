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
import com.assembla.liblda.io.itemwriter.StringItemWriter;
import org.junit.Before;
import org.junit.Test;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;


/**
 * @author hanbei
 */
public class AlphabetWriterTest {

    private Alphabet<String> alphabet;
    private static final String TEST1 = "Test1";
    private static final String TEST2 = "Test2";
    private static final String TEST3 = "Test3";
    private static final String TEST4 = "Test4";
    private AlphabetWriter<String> alphabetWriter;
    private String expectedWrite;

    @Before
    public void setUp() throws Exception {
        alphabet = new Alphabet<String>();
        alphabet.add(TEST1);
        alphabet.add(TEST2);
        alphabet.add(TEST3);
        alphabet.add(TEST4);

        alphabetWriter = new AlphabetWriter<String>(new StringItemWriter());
        expectedWrite = TEST1 + TestConstants.LINE_SEPARATOR + "0" + TestConstants.LINE_SEPARATOR + TEST2 + TestConstants.LINE_SEPARATOR + "1" + TestConstants.LINE_SEPARATOR + TEST3 + TestConstants.LINE_SEPARATOR + "2" + TestConstants.LINE_SEPARATOR + TEST4 + TestConstants.LINE_SEPARATOR + "3" + TestConstants.LINE_SEPARATOR;
    }

    @Test
    public void testWriteWithOutputStream() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        alphabetWriter.write(alphabet, byteArrayOutputStream);
        assertArrayEquals(expectedWrite.getBytes(), byteArrayOutputStream.toByteArray());
    }

    @Test
    public void testWriteWithWriter() throws IOException {
        StringWriter writer = new StringWriter();
        alphabetWriter.write(alphabet, writer);
        assertEquals(expectedWrite, writer.toString());

    }

}
