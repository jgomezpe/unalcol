package unalcol.types.real;

import unalcol.io.Read;
import unalcol.io.ShortTermMemoryReader;
import unalcol.types.tag.AbstractTags;
import unalcol.services.Service;

public interface UsesDoubleRead extends AbstractTags{
	public final static String READ_DOUBLE="Read.double";
	public default void setReadDouble( Read<Double> read ){ set(READ_DOUBLE,read); }
	public default double readDouble(ShortTermMemoryReader reader) throws Exception{
		@SuppressWarnings("unchecked")
		Read<Double> read = (Read<Double>)data(READ_DOUBLE);
		if( read!=null ) return read.read(reader);
		return (double)Service.run(Read.name, Double.class, reader);
	}
}