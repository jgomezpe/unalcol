package unalcol.language.generalized;

import unalcol.types.collection.Collection;

public interface GeneralizedLexer<S> extends GeneralizedLanguage<Collection<GeneralizedToken<S>>, S>{
	public GeneralizedEncoder<S> encoder();
	public void setEncoder(GeneralizedEncoder<S> encoder);
}