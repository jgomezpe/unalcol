package unalcol.real.matrix;


import unalcol.exception.ParamsException;
import unalcol.iterator.Backable;

public class PlainRead implements Read{
	public static final String integer = "Read.integer";
	public static final String real = "Read.double";

	protected boolean read_dimension = true;
	
	protected int n;
	protected int m;

	protected unalcol.integer.Read ri = new unalcol.integer.PlainRead();
	protected unalcol.real.Read rr = new unalcol.real.PlainRead();
	
	/**
	 * Character used for separating the values in the array
	 */
	protected char separator = ' ';

	/**
	 * Creates an integer array persistent method that uses an space for separatng the array values
	 */
	public PlainRead(){ this(' '); }

	/**
	 * Creates a double matrix persistent method that uses the give charater for separating the matrix values
	 * @param separator Character used for separating the matrix values
	 */
	public PlainRead(char separator) { this.separator = separator; }

	/**
	 * Creates a double matrix persistent method that uses the give charater for separating the matrix values
	 * @param separator Character used for separating the matrix values
	 */
	public PlainRead(int n, int m) { this(n,m,' '); }

	/**
	 * Creates a double matrix persistent method that uses the give charater for separating the matrix values
	 * @param separator Character used for separating the matrix values
	 */
	public PlainRead(int n, int m, char separator) {
		this.separator = separator;
		this.read_dimension = false;
		this.n=n;
		this.m=m;
	}

	public void setReadInt( unalcol.integer.Read ri ){ this.ri = ri; }
	public void setReadDouble( unalcol.real.Read rr ){ this.rr = rr; }
	
	/**
	 * Reads an array from the input stream (the first value is the array's size and the following values are the values in the array)
	 * @param reader The reader object
	 * @throws IOException IOException
	 */
	public double[][] read(Backable<Integer> reader) throws ParamsException {
		if( read_dimension ){
			n = ri.read(reader);
			unalcol.io.Read.readSeparator(reader, separator);
			m = ri.read(reader);
			unalcol.io.Read.readSeparator(reader, separator);            
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