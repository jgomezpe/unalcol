package unalcol.types.integer;

import unalcol.io.Read;
import unalcol.io.ShortTermMemoryReader;
import unalcol.types.tag.AbstractTags;
import unalcol.services.Service;

public interface UsesIntRead extends AbstractTags{
	public final static String READ_INT="Read.int";
	public default void setReadInt( Read<Integer> read ){ set(READ_INT,read); }
	public default int readInt(ShortTermMemoryReader reader) throws IOException{
		@SuppressWarnings("unchecked")
		Read<Integer> read = (Read<Integer>)data(READ_INT);
		if( read!=null ) return read.read(reader);
		return (int)Service.run(Read.name, Integer.class, reader);
	}
}