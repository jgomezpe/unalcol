package unalcol.io;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class CharReader extends ShortTermMemoryReader{
	protected Reader is;
	/**
	 * Creates a short term memory reader that maintains at most the last <i>MEMORY_SIZE</i> read symbols
	 * @param MEMORY_SIZE Memory size (maintains at most the last <i>MEMORY_SIZE</i> read symbols)
	 * @param reader The underline InputStream
	 */
	public CharReader(Reader is, int MEMORY_SIZE ) {
		super(MEMORY_SIZE);
		this.is = is;
	}

	/**
	 * Creates a short term memory reader that maintains at most the last <i>MEMORY_SIZE</i> (default) read symbols
	 * @param reader The underline Reader
	 */
	public CharReader(Reader is) { this( is, MEMORY_SIZE); }

	/**
	 * Creates a short term memory reader that maintains at most the last <i>MEMORY_SIZE</i> (default) read symbols
	 * @param reader The underline Reader
	 */
	public CharReader(String is) { this( new StringReader(is), is.length()); }

	@Override
	protected int get() throws IOException { return is.read(); }

	@Override
	public void close() throws IOException { is.close(); }
}