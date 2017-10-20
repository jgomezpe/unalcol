package unalcol.language.programming;

import unalcol.io.CharReader;
import unalcol.language.Language;
import unalcol.language.LanguageException;
import unalcol.language.Typed;
import unalcol.language.programming.lexer.Lexer;
import unalcol.language.programming.lexer.Token;
import unalcol.language.programming.meaner.Meaner;
import unalcol.language.programming.parser.Parser;
import unalcol.language.symbol.Encoder;
import unalcol.types.collection.Collection;
import unalcol.types.collection.array.Array;

public class ProgrammingLanguage<T> implements Language<T>{
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
	
	public Array<Token<?>> lexer( String input ) throws LanguageException{
		return lexer(new CharReader(input));
	}

	public Array<Token<?>> lexer( Collection<Integer> reader ) throws LanguageException{
		return lexer.apply(reader, symbols);
	}
	
	public Typed parser(int rule, Array<Token<?>> tokens) throws LanguageException{
		return parser.apply(rule, tokens);
	}
	
	public T meaner( Typed t ) throws LanguageException{
		return meaner.apply(t);
	}
	
	public T process( Collection<Integer> reader, int rule ) throws LanguageException{
		Array<Token<?>> tokens = lexer.apply(reader, symbols);
		Typed r = parser.apply(rule, tokens);
		return meaner.apply(r);				
	}

	public T process( String reader, int rule ) throws LanguageException{
		return process(new CharReader(reader), rule );
	}

	public T process( Collection<Integer> reader ) throws LanguageException{
	    return process(reader,main);				
	}
}