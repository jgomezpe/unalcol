package unalcol.language.parser.bottom_up;

import unalcol.types.collection.vector.Vector;

public interface Rule<T> {
	public Token<T> next( Vector<T> elements );
}
