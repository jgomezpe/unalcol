package unalcol.language.parser.lookahead;

import unalcol.language.Typed;
import unalcol.types.collection.iterator.UnalcolIterator;

public abstract class Lexeme<S> extends Typed{
	
	public Lexeme( int type ){ super( type ); }
	
    public abstract int get(UnalcolIterator<S> buffer );
}