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
package de.hanbei.liblda;

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
