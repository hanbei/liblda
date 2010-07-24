package de.fhg.iais.kd.tm.liblda.io.datastructures;

import de.fhg.iais.kd.tm.liblda.datastructures.Matrix;
import de.fhg.iais.kd.tm.liblda.io.IWriter;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: hanbei
 * Date: 23.07.2010
 * Time: 18:52:22
 * To change this template use File | Settings | File Templates.
 */
public class MatrixWriter implements IWriter<Matrix> {

    public void write(Matrix matrix, OutputStream out) throws IOException {
        write(matrix, new OutputStreamWriter(out));
    }

    public void write(Matrix matrix, java.io.Writer writer) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        for (int row = 0; row < matrix.getNumberOfRows(); row++) {
            for (int column = 0; column < matrix.getNumberOfColumns(); column++) {
                double value = matrix.get(row, column);
                bufferedWriter.write(" " + value);
            }
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
    }
}
