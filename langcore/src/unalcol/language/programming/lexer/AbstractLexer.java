package unalcol.language.programming.lexer;

import java.util.Iterator;

import unalcol.io.Position;
import unalcol.language.LanguageException;
import unalcol.language.symbol.AbstractEncoder;
import unalcol.types.collection.Collection;
import unalcol.types.collection.ShortTermMemoryIterator;
import unalcol.types.collection.UnalcolIterator;
import unalcol.types.collection.array.Array;

public interface AbstractLexer<S> {
	public int src();
	public void setSrc( int src );
	
	public Array<Token<?>> apply( UnalcolIterator<?,S> buffer, AbstractEncoder<S> encoder ) throws LanguageException;
	
	@SuppressWarnings("unchecked")
	public default Array<Token<?>> apply( Collection<S> buffer, AbstractEncoder<S> encoder ) throws LanguageException{
		Iterator<S> iter = buffer.iterator();
		UnalcolIterator<?, S> riter;
		if( iter instanceof UnalcolIterator) riter = (UnalcolIterator<?, S>)iter;
		else riter = new ShortTermMemoryIterator<Position, S>(iter) {
			@Override
			protected Position key(S data) { return new Position(src(),pos); }
		};
		return apply( riter, encoder );
	}
}