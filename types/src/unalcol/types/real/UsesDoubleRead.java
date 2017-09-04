package unalcol.types.real;

import java.io.IOException;

import unalcol.io.Read;
import unalcol.io.ReadWrapper;
import unalcol.io.ShortTermMemoryReader;
import unalcol.types.tag.AbstractTags;
import unalcol.services.Service;

public interface UsesDoubleRead extends AbstractTags{
	public final static String READ_DOUBLE="Read.double";
	public default void setReadDouble( Read<Double> read ){ set(Service.USES+READ_DOUBLE,read); }
	public default double readDouble(ShortTermMemoryReader reader) throws IOException{
		@SuppressWarnings("unchecked")
		Read<Double> read = (Read<Double>)data(Service.USES+READ_DOUBLE);
		if( read==null ) read = new ReadWrapper<Double>(); 
		read.setCaller(0.0);
		return read.read(reader);
	}
}