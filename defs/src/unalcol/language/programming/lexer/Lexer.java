package unalcol.language.programming.lexer;

import unalcol.io.ShortTermMemoryReader;
import unalcol.language.LanguageException;
import unalcol.language.symbol.Encoder;
import unalcol.types.collection.array.Array;

public interface Lexer {
	public Array<Token> apply( ShortTermMemoryReader buffer, int offset, Encoder encoder ) throws LanguageException;
}