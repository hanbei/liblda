package de.fhg.iais.kd.tm.liblda.io;

import de.fhg.iais.kd.tm.liblda.Alphabet;
import de.fhg.iais.kd.tm.liblda.io.itemwriter.StringItemWriter;
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
