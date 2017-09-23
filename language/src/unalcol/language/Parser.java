package unalcol.language;

import unalcol.types.collection.array.Array;
import unalcol.types.collection.array.ImmutableArray;

public interface Parser<T> {
	public Array<Typed> apply( Array<Token<T>> tokens, int offset, ImmutableArray<T> buffer ) throws LanguageException;
}