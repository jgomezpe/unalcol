package unalcol.io;

import java.io.IOException;

import unalcol.services.Service;

public class ReadWrapper<T> implements Read<T>{
	protected T caller;
	
	public ReadWrapper(T caller){ this.caller = caller; }
	
	@SuppressWarnings("unchecked")
	@Override
	public T read(ShortTermMemoryReader reader) throws IOException {
		try{ return (T)Service.run(Read.name, caller, reader ); }
		catch(IOException e){ throw e; }
		catch(Exception e1){ throw new IOException(e1.getMessage()); }
	}
}