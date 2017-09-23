package unalcol.language;

import java.io.InputStream;

import unalcol.types.collection.array.Array;
import unalcol.types.collection.array.ImmutableArray;

public class Language<T,S> {
	protected Lexer<S> lexer;
	protected Parser<S> parser;
	protected Meaner<T> meaner;
	protected int main;
	
	public Language( Lexer<S> lexer, Parser<S> parser, Meaner<T> meaner, int main ){
		this.lexer = lexer;
		this.parser = parser;
		this.meaner = meaner;
		this.main = main;
	}
	
	public T process( ImmutableArray<S> buffer ) throws LanguageException{
	    int offset=0;
		Array<Token<S>> tokens = lexer.apply(buffer,offset);
		Array<Typed> rules = parser.apply(tokens, offset, buffer);
		Typed t = null;
		if( rules.size() == 1 && rules.get(0).type() == main )	t = rules.get(0);
		else t = new TypedValue<Array<Typed>>(main,rules);			
		return meaner.apply(t);				
	}

	public T process( InputStream is ) throws Exception{
		throw new Exception("Unimplemented method..");
	};
	
	public T process( String fileName ) throws Exception{
		throw new Exception("Unimplemented method..");
	}
	
	/*
	public T process( InputStream is ) throws Exception{
		byte[] buffer = new byte[is.available()]; 
		is.read(buffer);
		return process(buffer);
	}
	
	public T process( String fileName ) throws Exception{
		return process( Files.readAllBytes( Paths.get(fileName)) );
	}
	*/	
}