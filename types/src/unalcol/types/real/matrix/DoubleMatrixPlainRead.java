package unalcol.types.real.matrix;

import java.io.IOException;

import unalcol.io.Read;
import unalcol.types.collection.UnalcolIterator;
import unalcol.types.integer.IntegerPlainRead;
import unalcol.types.integer.IntegerRead;
import unalcol.types.real.DoublePlainRead;
import unalcol.types.real.DoubleRead;

public class DoubleMatrixPlainRead implements DoubleMatrixRead{
	public static final String integer = "Read.integer";
	public static final String real = "Read.double";

	protected boolean read_dimension = true;
	
	protected int n;
	protected int m;

	protected IntegerRead ri = new IntegerPlainRead();
	protected DoubleRead rr = new DoublePlainRead();
	
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

	public void setReadInt( IntegerRead ri ){ this.ri = ri; }
	public void setReadDouble( DoubleRead rr ){ this.rr = rr; }
	
	/**
	 * Reads an array from the input stream (the first value is the array's size and the following values are the values in the array)
	 * @param reader The reader object
	 * @throws IOException IOException
	 */
	public double[][] read(UnalcolIterator<?,Integer> reader) throws IOException {
		if( read_dimension ){
			n = ri.read(reader);
			Read.readSeparator(reader, separator);
			m = ri.read(reader);
			Read.readSeparator(reader, separator);            
		}
		double[][] a = new double[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				a[i][j] = rr.read(reader);
		return a;
	}
	
	@Override
	public String toString(){ return "DoubleMatrixPlainRead"; }	
}