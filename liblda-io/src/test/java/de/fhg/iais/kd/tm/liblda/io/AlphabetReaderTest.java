package de.fhg.iais.kd.tm.liblda.io;

import de.fhg.iais.kd.tm.liblda.Alphabet;
import de.fhg.iais.kd.tm.liblda.io.itemwriter.StringItemReader;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.StringReader;

import static junit.framework.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA. User: fschulz Date: Jul 29, 2010 Time: 10:38:09 AM To change this template use File |
 * Settings | File Templates.
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
