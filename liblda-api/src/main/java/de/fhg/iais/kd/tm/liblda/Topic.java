package de.fhg.iais.kd.tm.liblda;

public class Topic implements Comparable<Topic> {

	private int id;
	private double probability;
	private String label;

	public Topic() {
		this(-1, "-1");
	}

	public Topic(int id, String label) {
		this(id, label, 0.0);
	}

	public Topic(int id, String label, double prob) {
		this.id = id;
		this.label = label;
		probability = prob;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getProbability() {
		return probability;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return label + "(" + id + "):" + probability;
	}

	@Override
	public int compareTo(Topic o) {
		int result = new Double(o.probability).compareTo(probability);
		if (result == 0) {
			return new Integer(id).compareTo(o.id);
		}
		return result;
	}
}
