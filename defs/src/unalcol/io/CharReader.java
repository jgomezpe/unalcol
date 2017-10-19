package unalcol.io;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class CharReader extends ReaderAsCollection{
	protected Reader is;

	/**
	 * Creates a short term memory reader that maintains at most the last <i>MEMORY_SIZE</i> (default) read symbols
	 * @param reader The underline Reader
	 */
	public CharReader(Reader is) { 
		this.is = is; 
		c=get();
		if( c==-1 ) close();
	}

	/**
	 * Creates a short term memory reader that maintains at most the last <i>MEMORY_SIZE</i> (default) read symbols
	 * @param reader The underline Reader
	 */
	public CharReader(String is) { this( new StringReader(is)); }

	@Override
	protected int get(){ try{ return is.read(); }catch(IOException e){ return -1; } }

	@Override
	public void close(){
		try{ 
			closed = true;
			is.close(); 
		}catch(IOException e){}
	}
}