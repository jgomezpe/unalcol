package unalcol.io;

import java.io.IOException;
import java.io.InputStream;

public class ByteReader extends ShortTermMemoryReader{
	protected InputStream is;
	/**
	 * Creates a short term memory reader that maintains at most the last <i>MEMORY_SIZE</i> read symbols
	 * @param MEMORY_SIZE Memory size (maintains at most the last <i>MEMORY_SIZE</i> read symbols)
	 * @param reader The underline InputStream
	 */
	public ByteReader(InputStream is, int MEMORY_SIZE ) {
		super(MEMORY_SIZE);
		this.is = is;
	}

	/**
	 * Creates a short term memory reader that maintains at most the last <i>MEMORY_SIZE</i> (default) read symbols
	 * @param reader The underline Reader
	 */
	public ByteReader(InputStream is) { this( is, MEMORY_SIZE); }


	@Override
	protected int get() throws IOException {
		return is.read();
	}

	@Override
	public void close() throws IOException { is.close(); }
}