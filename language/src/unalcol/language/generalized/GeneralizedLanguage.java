package unalcol.language.generalized;

import unalcol.language.LanguageException;
import unalcol.collection.Collection;
import unalcol.iterator.BackableIterator;

public interface GeneralizedLanguage<T,S>{
	default T process( Collection<S> reader ) throws LanguageException{ return process( reader.unalcol() ); }
	public T process( BackableIterator<S> reader ) throws LanguageException;
}