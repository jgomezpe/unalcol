package unalcol.language.generalized;

import unalcol.language.Typed;

public interface GeneralizedParser<S> extends GeneralizedLanguage<Typed,GeneralizedToken<S>>{
	public int rule();
	public void setRule(int rule);
}
