package unalcol.language;

import unalcol.types.collection.Collection;

public interface AbstractLanguage<T,S>{
	public T process( Collection<S> reader ) throws LanguageException;
}
