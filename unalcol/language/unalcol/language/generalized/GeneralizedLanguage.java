package unalcol.language.generalized;

import unalcol.language.LanguageException;
import unalcol.collection.Collection;
import unalcol.iterator.Backable;

public interface GeneralizedLanguage<T,S>{
	default T process( Collection<S> reader ) throws LanguageException{ return process( reader.unalcol() ); }
	public T process( Backable<S> reader ) throws LanguageException;
}