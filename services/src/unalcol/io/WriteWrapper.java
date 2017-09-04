package unalcol.io;

import java.io.IOException;
import java.io.Writer;

import unalcol.services.Service;
import unalcol.services.TaggedCallerNamePair;
import unalcol.types.tag.Tags;

public class WriteWrapper<T> extends Tags implements TaggedCallerNamePair<T>, Write<T>{
	@Override
	public void write(Writer writer) throws IOException {
		try{ Service.run(Write.name, caller(), writer ); }
		catch(IOException e){ throw e; }
		catch(Exception e1){ throw new IOException(e1.getMessage()); }
	}
}