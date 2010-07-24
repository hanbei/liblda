package de.fhg.iais.kd.tm.liblda.inference.gibbs;

import org.junit.Test;

public class TestThreadedGibssSampler extends AbstractGibbsSamplerTestFixture {

	// private ArrayList<ArrayList<String>> corpus;

	// @Before
	// public void readData() throws URISyntaxException, IOException {
	// corpus = new ArrayList<ArrayList<String>>();
	// readEnglishStopwords();
	//
	// File[] txtFiles;
	// File inputFile = new
	// File(TestThreadedGibssSampler.class.getResource("/data").toURI());
	// txtFiles = collectFiles(inputFile);
	//
	// for (File txtFile : txtFiles) {
	// ArrayList<String> doc = readFile(txtFile);
	// corpus.add(doc);
	// }
	// }

	@Test
	public void testLDATraining() {
		// LDAImpl<String> lda = new LDAImpl<String>(10);
		// lda.setInferenceEngine(new ThreadedGibbsSampler());
		// lda.getInferenceEngine().getRandomEngine().setSeed(0);
		// lda.getInferenceEngine().setNumberOfIterations(1000);
		// lda.setTrainingData(corpus);
		//
		// long startTime = System.currentTimeMillis();
		// lda.train();
		// logRunTime(startTime);
		//
		// HashMap<Integer, List<String>> topFeatures = lda.getTopFeatures();
		//
		// for (int topic = 0; topic <
		// lda.getInferenceEngine().getLDAParameter().getNumberOfTopics();
		// topic++) {
		// System.out.print(topic + ": ");
		// List<String> topWords = topFeatures.get(topic);
		// for (String word : topWords) {
		// System.out.print("\"" + word + "\", ");
		// }
		// System.out.println();
		// }
		//
		// assertEquals("topic0", TOPIC0_TOPWORDS, topFeatures.get(0));
		// assertEquals("topic1", TOPIC1_TOPWORDS, topFeatures.get(1));
		// assertEquals("topic2", TOPIC2_TOPWORDS, topFeatures.get(2));
		// assertEquals("topic3", TOPIC3_TOPWORDS, topFeatures.get(3));
		// assertEquals("topic4", TOPIC4_TOPWORDS, topFeatures.get(4));
		// assertEquals("topic5", TOPIC5_TOPWORDS, topFeatures.get(5));
		// assertEquals("topic6", TOPIC6_TOPWORDS, topFeatures.get(6));
		// assertEquals("topic7", TOPIC7_TOPWORDS, topFeatures.get(7));
		// assertEquals("topic8", TOPIC8_TOPWORDS, topFeatures.get(8));
		// assertEquals("topic9", TOPIC9_TOPWORDS, topFeatures.get(9));

	}

	@Test
	public void testLDAInference() {
		// LDAImpl<String> lda = new LDAImpl<String>(10);
		// lda.setInferenceEngine(new ThreadedGibbsSampler());
		// lda.setTrainingData(corpus);
		// lda.getInferenceEngine().getRandomEngine().setSeed(0);
		//
		// long startTime = System.currentTimeMillis();
		// lda.train();
		// logRunTime(startTime);
		// HashMap<Integer, List<String>> topFeatures = lda.getTopFeatures();
		// for (int topic : topFeatures.keySet()) {
		// System.out.print(topic + ": ");
		// List<String> topWords = topFeatures.get(topic);
		// for (String word : topWords) {
		// System.out.print("\"" + word + "\", ");
		// }
		// System.out.println();
		// }
		//
		// List<? extends String> inferenceData = lda.getTopFeaturesForTopic(6);
		//
		// List<Topic> topicScores = lda.inference(inferenceData);
		//
		// for (Topic topic : topicScores) {
		// if (topic.getId() == 6) {
		// assertEquals("topic " + topic.getId(), 1.0, topic.getProbability(),
		// 0.0001);
		// } else {
		// assertEquals("topic " + topic.getId(), 0.0, topic.getProbability(),
		// 0.0001);
		// }
		// System.out.format("(%d, %f) \n", topic.getId(),
		// topic.getProbability());
		// }
	}

}
