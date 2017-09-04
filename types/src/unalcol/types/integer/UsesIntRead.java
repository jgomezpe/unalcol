package unalcol.types.integer;

import java.io.IOException;

import unalcol.io.Read;
import unalcol.io.ReadWrapper;
import unalcol.io.ShortTermMemoryReader;
import unalcol.types.tag.AbstractTags;
import unalcol.services.Service;

public interface UsesIntRead extends AbstractTags{
	public final static String READ_INT="Read.int";
	public default void setReadInt( Read<Integer> read ){ set(Service.USES+READ_INT,read); }
	public default int readInt(ShortTermMemoryReader reader) throws IOException{
		@SuppressWarnings("unchecked")
		Read<Integer> read = (Read<Integer>)data(Service.USES+READ_INT);
		if( read==null ) read = new ReadWrapper<Integer>(); 
		read.setCaller(0);
		return read.read(reader);
	}
}