package unalcol.language;

public interface Meaner<T> {
	public T apply( Typed g_obj ) throws LanguageException;
}