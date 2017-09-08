package unalcol.io;

import java.io.IOException;

import unalcol.services.Service;
import unalcol.services.MicroService;

public class ReadWrapper<T>  extends MicroService<T> implements Read<T>{	
	@SuppressWarnings("unchecked")
	@Override
	public T read(ShortTermMemoryReader reader) throws IOException {
		try{ return (T)Service.run(Read.name, caller(), reader ); }
		catch(IOException e){ throw e; }
		catch(Exception e1){ throw new IOException(e1.getMessage()); }
	}
}