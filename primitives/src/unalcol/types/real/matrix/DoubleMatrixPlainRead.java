package unalcol.types.real.matrix;

import java.io.IOException;
import java.io.StreamTokenizer;

import unalcol.io.Read;
import unalcol.io.ShortTermMemoryReader;
import unalcol.services.MicroService;

public class DoubleMatrixPlainRead extends MicroService<double[][]> implements Read<double[][]> {
    /**
     * Character used for separating the values in the array
     */
    protected char separator = ' ';

    /**
     * Creates an integer array persistent method that uses an space for separatng the array values
     */
    public DoubleMatrixPlainRead() {}

    /**
     * Creates a double matrix persistent method that uses the give charater for separating the matrix values
     * @param separator Character used for separating the matrix values
     */
    public DoubleMatrixPlainRead(char separator) {
        this.separator = separator;
    }

    /**
     * Reads an array from the input stream (the first value is the array's size and the following values are the values in the array)
     * @param reader The reader object
     * @throws IOException IOException
     */
    public double[][] read(ShortTermMemoryReader reader) throws IOException {
        StreamTokenizer tok = new StreamTokenizer(reader);
        tok.nextToken();
        int n = (int) tok.nval;
        tok.nextToken();
        int m = (int) tok.nval;
        double[][] a = new double[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                tok.nextToken();
                a[i][j] = tok.nval;
            }
        }
        return a;
    }
}
