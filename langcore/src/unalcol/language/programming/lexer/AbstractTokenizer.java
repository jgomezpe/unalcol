package unalcol.language.programming.lexer;

import unalcol.language.symbol.AbstractEncoder;
import unalcol.types.collection.Collection;
import unalcol.types.collection.UnalcolIterator;
import unalcol.types.collection.array.Array;
import unalcol.types.collection.vector.Vector;

public class AbstractTokenizer<S> {
	protected int error = Integer.MIN_VALUE;
	protected AbstractEncoder<S> encoder;
	protected AbstractLexer<S> lexer;
	public AbstractTokenizer( AbstractEncoder<S> encoder, AbstractLexer<S> lexer ){
		this.lexer = lexer;
		this.encoder = encoder;
	}
			
	public AbstractTokenizer( AbstractEncoder<S> encoder, AbstractLexer<S> lexer, int error ){
		this.lexer = lexer;
		this.encoder = encoder;
	}
			
	public Array<Token<?>> apply(Collection<S> reader){
		UnalcolIterator<?, S> iter = reader.unalcol();
		Vector<Token<?>> tokens = new Vector<Token<?>>();
		boolean flag = false;
		while(!flag){
			try{
				Array<Token<?>> toks = lexer.apply(iter,  encoder);
				for(Token<?> t:toks) tokens.add(t);
				flag = true;
			}catch( Exception e ){
				tokens.add(new Token<Object>(error, iter.key()));
				try{ iter.next();	}catch(Exception ex){}
			}
		}
		return tokens;
	}
}