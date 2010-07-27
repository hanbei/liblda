package de.fhg.iais.kd.tm.liblda;

import de.fhg.iais.kd.tm.liblda.inference.gibbs.TestLDASparseGibbsampler;
import org.junit.Before;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class DataReader {

	protected List<ArrayList<String>> corpus;
	protected Set<String> stopWords = new TreeSet<String>();

	public DataReader() {
		super();
	}

	protected File[] collectFiles(File directory) {
		ArrayList<File> allDPAFiles = new ArrayList<File>();

		File[] files = directory.listFiles(new FileFilter() {

			public boolean accept(File file) {
				return file.getName().endsWith("txt");
			}
		});

		for (File file : files) {
			if (file.isDirectory()) {
				File[] subFiles = collectFiles(file);
				allDPAFiles.addAll(Arrays.asList(subFiles));
			} else {
				allDPAFiles.add(file);
			}
		}
		return allDPAFiles.toArray(new File[allDPAFiles.size()]);
	}

	protected ArrayList<String> readFile(File txtFile) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(txtFile));
		String text = "";
		String line;
		while ((line = reader.readLine()) != null) {
			if (line.matches("\\s")) {
				continue;
			}
			text = text + " " + line;
		}
		reader.close();
		ArrayList<String> doc = new ArrayList<String>();
		StringTokenizer tokenizer = new StringTokenizer(text, " ,:.;");
		while (tokenizer.hasMoreTokens()) {
			String token = tokenizer.nextToken().toLowerCase();
			if (!stopWords.contains(token)) {
				token = token.replace("\"", "");
				doc.add(token);
			}
		}
		return doc;
	}

	protected void logRunTime(long startTime) {
		long seconds = Math.round((System.currentTimeMillis() - startTime) / 1000.0);
		long minutes = seconds / 60;
		seconds %= 60;
		long hours = minutes / 60;
		minutes %= 60;
		long days = hours / 24;
		hours %= 24;
		StringBuffer buffer = new StringBuffer();
		if (days != 0) {
			buffer.append(days);
			buffer.append(" days ");
		}
		if (hours != 0) {
			buffer.append(hours);
			buffer.append(" hours ");
		}
		if (minutes != 0) {
			buffer.append(minutes);
			buffer.append(" minutes ");
		}
		buffer.append(seconds);
		buffer.append(" seconds");
		System.out.println("\nTotal time:" + buffer.toString());
	}

	protected void readEnglishStopwords() throws IOException {
		InputStream stopWordFile = DataReader.class.getResourceAsStream("/english_stopWords.txt");
		stopWords.clear();
		BufferedReader reader = new BufferedReader(new InputStreamReader(stopWordFile));
		String line;
		while ((line = reader.readLine()) != null) {
			stopWords.add(line.trim());
		}
	}

	@Before
	public void readData() throws URISyntaxException, IOException {
		corpus = new ArrayList<ArrayList<String>>();
		readEnglishStopwords();

		File[] txtFiles;
		URL url = DataReader.class.getResource("/data");
		File inputFile = new File(url.toURI());
		txtFiles = collectFiles(inputFile);

		for (File txtFile : txtFiles) {
			ArrayList<String> doc = readFile(txtFile);
			corpus.add(doc);
		}
	}
}