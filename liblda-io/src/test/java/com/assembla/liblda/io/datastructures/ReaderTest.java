/**
 *
 *  This file is part of ${PROJECT}.
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
 *
 *  @author Florian Schulz
 */
package com.assembla.liblda.io.datastructures;

import com.assembla.liblda.io.TestConstants;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNull;/*
 *
 *  @author Florian Schulz
 */

public class ReaderTest {

    private static final String TEST1 = "Test1";
    private static final String TEST2 = "Test2";
    private static final String TEST3 = "Test3";
    private static final String TEST4 = "Test4";

    private String expectedRead = TEST1 + TestConstants.LINE_SEPARATOR + "0" + TestConstants.LINE_SEPARATOR + TEST2 + TestConstants.LINE_SEPARATOR + "1" + TestConstants.LINE_SEPARATOR + TEST3 + TestConstants.LINE_SEPARATOR + "2" + TestConstants.LINE_SEPARATOR + TEST4 + TestConstants.LINE_SEPARATOR + "3" + TestConstants.LINE_SEPARATOR;

    @Test
    public void testReader() throws IOException {
        StringReader reader = new StringReader(expectedRead);
        BufferedReader itemReader = new BufferedReader(reader);
        //BufferedReader numberReader = new BufferedReader(itemReader);

        assertEquals(TEST1, itemReader.readLine());
        assertEquals("0", itemReader.readLine());
        assertEquals(TEST2,itemReader.readLine());
        assertEquals("1", itemReader.readLine());
    }
}
