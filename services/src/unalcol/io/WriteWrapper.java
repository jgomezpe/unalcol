package unalcol.io;

import java.io.IOException;
import java.io.Writer;

import unalcol.services.Service;

public class WriteWrapper<T> implements Write<T>{

	@Override
	public void write(T obj, Writer writer) throws IOException {
		try{ Service.run(Write.name, obj, writer ); }
		catch(IOException e){ throw e; }
		catch(Exception e1){ throw new IOException(e1.getMessage()); }
	}
}