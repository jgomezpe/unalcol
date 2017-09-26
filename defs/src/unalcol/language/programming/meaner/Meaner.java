package unalcol.language.programming.meaner;

import unalcol.language.LanguageException;
import unalcol.language.Typed;

public interface Meaner<T> {
	public T apply( Typed g_obj ) throws LanguageException;
}