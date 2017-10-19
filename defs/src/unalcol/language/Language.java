package unalcol.language;

import unalcol.io.CharReader;
import unalcol.io.ShortTermMemoryReader;

public interface Language<T> {
	public T process( ShortTermMemoryReader reader ) throws LanguageException;
	public default T process( String reader ) throws LanguageException{
		return process(new CharReader(reader));
	}
}
