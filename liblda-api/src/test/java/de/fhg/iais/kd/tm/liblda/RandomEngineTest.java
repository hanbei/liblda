package de.fhg.iais.kd.tm.liblda;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class RandomEngineTest {

    private static final double EPSILON = 0.000001;
    private RandomEngine random;
    private double[] alphas;
    private double[] expectedDistribution;
    private int[] expectedInts;

    @Before
    public void setUp() {
        random = new RandomEngine(new Random(0));
        alphas = new double[]{0.6125968452677445, 0.7021263369523334, 0.8104565321643618, 0.6704615910882477,
                0.8464015500087296, 0.8451512496921193, 0.7007855117536164, 0.8922581539918372, 0.6915600088831061,
                0.6182354730470828};
        expectedDistribution = new double[]{0.09588444935979229, 0.07646295602537441, 0.03219472089956069,
                0.009097649133636657, 0.010964672153721449, 0.486776459019379, 0.12796816387993246,
                0.016576160367324885, 0.09806976321152473, 0.04600500594975344};
        expectedInts = new int[]{6, 5, 5, 5, 8, 5, 1, 5, 1, 5};
    }

    @Test
    public void testNextDiscrete() {
        for (int i = 0; i < 10; i++) {
            assertEquals("At " + i, expectedInts[i], random.nextDiscrete(expectedDistribution, 1.0));
            random.nextDiscrete(expectedDistribution, 1.0);
        }
    }

    @Test
    public void testNextDistribution() {
        // random.nextInt(10);
        double[] distribution = random.nextDistribution(alphas);
        assertEquals(10, distribution.length);
        double sum = 0.0;
        for (double alpha : distribution) {
            sum += alpha;
        }
        assertArrayEquals(expectedDistribution, distribution, EPSILON);
        assertEquals(1.0, sum, EPSILON);
    }
}
