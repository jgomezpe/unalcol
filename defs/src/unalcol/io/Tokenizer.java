package unalcol.io;

import unalcol.language.programming.lexer.Lexer;
import unalcol.language.programming.lexer.Token;
import unalcol.language.symbol.Encoder;
import unalcol.types.collection.array.Array;
import unalcol.types.collection.vector.Vector;

public class Tokenizer {
	protected int error = Integer.MIN_VALUE;
	protected Encoder encoder;
	protected Lexer lexer;
	public Tokenizer( Encoder encoder, Lexer lexer ){
		this.lexer = lexer;
		this.encoder = encoder;
	}
	
	public Tokenizer( Encoder encoder, Lexer lexer, int error ){
		this.lexer = lexer;
		this.encoder = encoder;
	}
	
	protected boolean apply( Vector<Token> tokens, ShortTermMemoryReader reader ){
		try{
			Array<Token> toks = lexer.apply(reader, 0, encoder);
			for(Token t:toks) tokens.add(t);
			return true;
		}catch( Exception e ){
			tokens.add(new Token(error, reader.position()));
			try{ reader.read();	}catch(Exception ex){}
		}
		return false;
	}
	
	public Array<Token> apply(ShortTermMemoryReader reader){
		Vector<Token> tokens = new Vector<Token>();
		while(!apply(tokens,reader)){}
		return tokens;
	}

	public Array<Token> apply(String input){ return apply( new CharReader(input) ); }
}