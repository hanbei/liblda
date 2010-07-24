package de.fhg.iais.kd.tm.liblda.datastructures;

import java.io.Serializable;

public interface Matrix extends Serializable {

	public abstract double get(int rowIndex, int colIndex);

	public abstract void set(int rowIndex, int colIndex, double value);

	public abstract int getNumberOfColumns();

	public abstract int getNumberOfRows();

	public abstract void increment(int rowIndex, int colIndex);

	public abstract void decrement(int rowIndex, int colIndex);

}