package unalcol.types.integer;

import java.io.IOException;

import unalcol.io.Read;
import unalcol.types.collection.UnalcolIterator;

public interface IntegerRead extends Read{
	public Integer read( UnalcolIterator<?, Integer> reader ) throws IOException;
	
	@Override
	default Object read(Object one, UnalcolIterator<?, Integer> reader) throws IOException { return read(reader); }	
}