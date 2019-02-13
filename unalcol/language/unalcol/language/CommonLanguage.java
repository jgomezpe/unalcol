package unalcol.language;

import unalcol.io.reader.Char;
import unalcol.language.generalized.GeneralizedToken;
import unalcol.collection.Collection;
import unalcol.iterator.Backable;

public class CommonLanguage<T> implements Language<T>{
	protected Encoder symbols;
	protected Lexer lexer;
	protected Parser parser;
	protected Meaner<T> meaner;
	protected int main;
	
	public CommonLanguage( Encoder symbols, Lexer lexer, Parser parser, Meaner<T> meaner, int main ){
		this.symbols = symbols;
		this.lexer = lexer;
		this.parser = parser;
		this.meaner = meaner;
		this.main = main;
	}
	
	public Collection<GeneralizedToken<Integer>> lexer( String input ) throws LanguageException{
		lexer.setEncoder(symbols);
		return lexer(new Char(input));
	}

	public Collection<GeneralizedToken<Integer>> lexer( Collection<Integer> reader ) throws LanguageException{
		lexer.setEncoder(symbols);
		return lexer.process(reader);
	}
	
	public Typed parser(int rule, Collection<GeneralizedToken<Integer>> tokens) throws LanguageException{
		parser.setRule(rule);
		return parser.process(tokens);
	}
	
	public T meaner( Typed t ) throws LanguageException{
		return meaner.apply(t);
	}
	
	public T process( int rule, String reader, int src ) throws LanguageException{ return process(rule, (new Char(reader,src)).unalcol() ); }
	public T process( int rule, String reader ) throws LanguageException{ return process(rule, reader, 0); }

	public T process( int rule, Backable<Integer> reader ) throws LanguageException{
		lexer.setEncoder(symbols);
		Collection<GeneralizedToken<Integer>> tokens = lexer.process(reader);
		parser.setRule(rule);
		Typed r = parser.process(tokens);
		return meaner.apply(r);				
	}

	@Override
	public T process( Backable<Integer> reader ) throws LanguageException{ return process(main,reader); }
}