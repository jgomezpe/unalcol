package unalcol.io;

import java.io.IOException;
import java.io.Writer;

import unalcol.services.Service;
import unalcol.services.MicroService;

public class WriteWrapper<T> extends MicroService<T> implements Write<T>{
	@Override
	public void write(Writer writer) throws IOException {
		try{ Service.run(Write.name, caller(), writer ); }
		catch(IOException e){ throw e; }
		catch(Exception e1){ throw new IOException(e1.getMessage()); }
	}
}