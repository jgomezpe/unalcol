package unalcol.language.programming.parser;

import unalcol.language.LanguageException;
import unalcol.language.Typed;
import unalcol.language.programming.lexer.Token;
import unalcol.types.collection.array.Array;

public interface Parser {
	public Typed apply( int rule, Array<Token<?>> tokens ) throws LanguageException;
}