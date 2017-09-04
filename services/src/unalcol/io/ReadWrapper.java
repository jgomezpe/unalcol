package unalcol.io;

import java.io.IOException;

import unalcol.services.Service;
import unalcol.services.TaggedCallerNamePair;
import unalcol.types.tag.Tags;

public class ReadWrapper<T> extends Tags implements TaggedCallerNamePair<T>, Read<T>{	
	@SuppressWarnings("unchecked")
	@Override
	public T read(ShortTermMemoryReader reader) throws IOException {
		try{ return (T)Service.run(Read.name, caller(), reader ); }
		catch(IOException e){ throw e; }
		catch(Exception e1){ throw new IOException(e1.getMessage()); }
	}
}