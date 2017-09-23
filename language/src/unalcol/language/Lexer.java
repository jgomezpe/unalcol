package unalcol.language;

import unalcol.types.collection.array.Array;
import unalcol.types.collection.array.ImmutableArray;

public interface Lexer<T> {
	public Array<Token<T>> apply( ImmutableArray<T> buffer, int offset ) throws LanguageException;
}