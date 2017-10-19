package unalcol.language;

import unalcol.io.CharReader;

public interface Language<T> extends AbstractLanguage<T,Integer>{
	public default T process( String reader ) throws LanguageException{
		return process(new CharReader(reader));
	}
}