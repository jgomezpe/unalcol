package unalcol.language.programming;

import unalcol.io.ShortTermMemoryReader;
import unalcol.language.LanguageException;
import unalcol.language.Typed;
import unalcol.language.programming.lexer.Lexer;
import unalcol.language.programming.lexer.Token;
import unalcol.language.programming.meaner.Meaner;
import unalcol.language.programming.parser.Parser;
import unalcol.language.symbol.Encoder;
import unalcol.types.collection.array.Array;

public class ProgrammingLanguage<T,S> {
	protected Encoder symbols;
	protected Lexer lexer;
	protected Parser parser;
	protected Meaner<T> meaner;
	protected int main;
	
	public ProgrammingLanguage( Encoder symbols, Lexer lexer, Parser parser, Meaner<T> meaner, int main ){
		this.symbols = symbols;
		this.lexer = lexer;
		this.parser = parser;
		this.meaner = meaner;
		this.main = main;
	}

	public T process( ShortTermMemoryReader reader ) throws LanguageException{
	    int offset=0;
		Array<Token> tokens = lexer.apply(reader,offset, symbols);
		Typed rule = parser.apply(tokens, offset);
		return meaner.apply(rule);				
	}
}