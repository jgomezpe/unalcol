package unalcol.io.reader;

import java.io.IOException;
import java.io.StringReader;

import unalcol.io.Reader;

public class Char extends Reader{
	protected java.io.Reader is;

	/**
	 * Creates a short term memory reader that maintains at most the last <i>MEMORY_SIZE</i> (default) read symbols
	 * @param reader The underline Reader
	 */
	public Char(java.io.Reader is) { this( is, 0 ); }
	
	/**
	 * Creates a short term memory reader that maintains at most the last <i>MEMORY_SIZE</i> (default) read symbols
	 * @param reader The underline Reader
	 */
	public Char(java.io.Reader is, int src) {
		super( src );
		this.is = is; 
		c=get();
		if( c==-1 ) close();
	}

	/**
	 * Creates a short term memory reader that maintains at most the last <i>MEMORY_SIZE</i> (default) read symbols
	 * @param reader The underline Reader
	 */
	public Char(String is) { this( is, 0); }

	/**
	 * Creates a short term memory reader that maintains at most the last <i>MEMORY_SIZE</i> (default) read symbols
	 * @param reader The underline Reader
	 */
	public Char(String is, int src) { this( new StringReader(is), src); }

	@Override
	public int get(){ try{ return is.read(); }catch(IOException e){ return -1; } }

	@Override
	public void close(){
		try{ 
			closed = true;
			is.close(); 
		}catch(IOException e){}
	}
}