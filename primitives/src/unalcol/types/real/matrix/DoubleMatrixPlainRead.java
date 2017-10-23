package unalcol.types.real.matrix;

import java.io.IOException;

import unalcol.io.Read;
import unalcol.services.MicroService;
import unalcol.types.collection.UnalcolIterator;

public class DoubleMatrixPlainRead extends MicroService<double[][]> implements Read<double[][]> {
	public static final String integer = "Read.integer";
	public static final String real = "Read.double";

	protected boolean read_dimension = true;
	
	protected int n;
	protected int m;
	
	/**
	 * Character used for separating the values in the array
	 */
	protected char separator = ' ';

	/**
	 * Creates an integer array persistent method that uses an space for separatng the array values
	 */
	public DoubleMatrixPlainRead(){ this(' '); }

	/**
	 * Creates a double matrix persistent method that uses the give charater for separating the matrix values
	 * @param separator Character used for separating the matrix values
	 */
	public DoubleMatrixPlainRead(char separator) { this.separator = separator; }

	/**
	 * Creates a double matrix persistent method that uses the give charater for separating the matrix values
	 * @param separator Character used for separating the matrix values
	 */
	public DoubleMatrixPlainRead(int n, int m) { this(n,m,' '); }

	/**
	 * Creates a double matrix persistent method that uses the give charater for separating the matrix values
	 * @param separator Character used for separating the matrix values
	 */
	public DoubleMatrixPlainRead(int n, int m, char separator) {
		this.separator = separator;
		this.read_dimension = false;
		this.n=n;
		this.m=m;
	}

	/**
	 * Reads an array from the input stream (the first value is the array's size and the following values are the values in the array)
	 * @param reader The reader object
	 * @throws IOException IOException
	 */
	public double[][] read(UnalcolIterator<?,Integer> reader) throws IOException {
		if( read_dimension ){
			@SuppressWarnings("unchecked")
			Read<Integer> ri = (Read<Integer>)getMicroService(integer);
			ri.setCaller(n);
			n = ri.read(reader);
			Read.readSeparator(reader, separator);
			m = ri.read(reader);
			Read.readSeparator(reader, separator);            
		}
		@SuppressWarnings("unchecked")
		Read<Double> rr = (Read<Double>)getMicroService(real);
		rr.setCaller(0.0);
		double[][] a = new double[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				a[i][j] = rr.read(reader);
		return a;
	}
}