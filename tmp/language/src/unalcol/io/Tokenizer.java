package unalcol.io;

import unalcol.io.reader.CharReader;
import unalcol.language.Encoder;
import unalcol.language.Lexer;
import unalcol.language.generalized.GeneralizedTokenizer;
import unalcol.language.generalized.GeneralizedToken;
import unalcol.collection.array.Array;

public class Tokenizer extends GeneralizedTokenizer<Integer> {
	public Tokenizer( Encoder encoder, Lexer lexer ){ super(  encoder, lexer ); }
	
	public Tokenizer( Encoder encoder, Lexer lexer, int error ){ super(encoder,lexer,error); }
	
	public Array<GeneralizedToken<Integer>> apply(String input){ return apply( input, 0 ); }
	public Array<GeneralizedToken<Integer>> apply(String input, int src){ return apply( new CharReader(input, src) ); }	
}