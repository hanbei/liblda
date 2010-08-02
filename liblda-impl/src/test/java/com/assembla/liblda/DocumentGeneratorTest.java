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

import com.assembla.liblda.inference.InferenceEngine;
import com.assembla.liblda.io.LDAReader;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class DocumentGeneratorTest {

    private LDA model;
    private DocumentGenerator<String> docGenerator;
    // private int[] expectedTopics;
    // private int[] expectedTypes;
    private String[] document;
    private static final double EPSILON = 0.00001;

    @Before
    public void setUp() {
        readModel();
        docGenerator = new DocumentGenerator<String>(model);
        docGenerator.setMinimumDocumentLength(10);
        docGenerator.setMaximumDocumentLength(10);

        // expectedTopics = new int[] { 0, 5, 9, 5, 4, 0, 6, 3, 8, 5 };
        // expectedTypes = new int[] { 804, 3258, 4778, 581, 4321, 3040, 2910,
        // 2677, 3, 3115 };
        document = new String[]{"saver", "refrigerator", "member", "condense", "united", "eco-house",
                "eat", "idea", "gives", "house"};

        InferenceEngine inferenceEngine = model.getInferenceEngine();
        inferenceEngine.setRandomEngine(new RandomEngine(new Random(0)));
    }

    @Ignore
    @Test
    public void testDocumentGeneration() {
        List<String> actualDocument = docGenerator.generateDocument();
        assertEquals(10, actualDocument.size());
        assertArrayEquals(document, actualDocument.toArray(new String[actualDocument.size()]));
    }

    @Ignore
    @Test
    public void testDocumentGenerationWithPredefinedTopics() {
        List<String> actualDocument = docGenerator.generateDocument(new int[]{7, 2, 3}, new double[]{1.0 / 3.0,
                1.0 / 3.0, 1.0 / 3.0});
        assertEquals(10, actualDocument.size());
        List<Topic> topics = model.inference(actualDocument);
        assertEquals(0.04232997768260616, topics.get(0).getProbability(), EPSILON);
        assertEquals(0.11367962722882735, topics.get(1).getProbability(), EPSILON);

        assertEquals(0.21096868167065422, topics.get(2).getProbability(), EPSILON);
        assertEquals(0.2665455760052199, topics.get(3).getProbability(), EPSILON);

        assertEquals(0.05564800747353246, topics.get(4).getProbability(), EPSILON);
        assertEquals(0.10135655268740858, topics.get(5).getProbability(), EPSILON);
        assertEquals(0.03723532114655079, topics.get(6).getProbability(), EPSILON);

        assertEquals(0.09406302265164554, topics.get(7).getProbability(), EPSILON);

        assertEquals(0.0430298161341024, topics.get(8).getProbability(), EPSILON);
        assertEquals(0.03514341731945272, topics.get(9).getProbability(), EPSILON);
    }

    @SuppressWarnings("unchecked")
    private void readModel() {
        InputStream in = DocumentGeneratorTest.class.getResourceAsStream("/topic.lda");
        LDAReader reader = new LDAReader();
        model = reader.readLDAModel(in);
    }

}
