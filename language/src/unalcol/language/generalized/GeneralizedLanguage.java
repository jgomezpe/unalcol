package unalcol.language.generalized;

import unalcol.language.LanguageException;
import unalcol.types.collection.Collection;
import unalcol.types.collection.iterator.UnalcolIterator;

public interface GeneralizedLanguage<T,S>{
	default T process( Collection<S> reader ) throws LanguageException{ return process( reader.unalcol() ); }
	public T process( UnalcolIterator<S> reader ) throws LanguageException;
}