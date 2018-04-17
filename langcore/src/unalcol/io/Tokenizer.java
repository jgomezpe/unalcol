package unalcol.io;

import unalcol.language.programming.lexer.AbstractTokenizer;
import unalcol.language.programming.lexer.Lexer;
import unalcol.language.programming.lexer.Token;
import unalcol.language.symbol.Encoder;
import unalcol.types.collection.array.Array;

public class Tokenizer extends AbstractTokenizer<Integer> {
	public Tokenizer( Encoder encoder, Lexer lexer ){ super(  encoder, lexer ); }
	
	public Tokenizer( Encoder encoder, Lexer lexer, int error ){ super(encoder,lexer,error); }
	
	public Array<Token<?>> apply(String input){ return apply( new CharReader(input) ); }
}