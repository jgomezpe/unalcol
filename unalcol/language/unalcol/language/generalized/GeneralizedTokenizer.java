package unalcol.language.generalized;

import unalcol.collection.Array;
import unalcol.collection.Collection;
import unalcol.collection.Vector;
import unalcol.iterator.Backable;
import unalcol.iterator.Trackable;

public class GeneralizedTokenizer<S> {
	protected int error = Integer.MIN_VALUE;
	protected GeneralizedLexer<S> lexer;
	public GeneralizedTokenizer( GeneralizedEncoder<S> encoder, GeneralizedLexer<S> lexer ){
		this.lexer = lexer;
		this.lexer.setEncoder(encoder);
	}
			
	public GeneralizedTokenizer( GeneralizedEncoder<S> encoder, GeneralizedLexer<S> lexer, int error ){
		this( encoder, lexer );
		this.error = error;
	}
			
	@SuppressWarnings("unchecked")
	public Array<GeneralizedToken<S>> apply(Collection<S> reader){
		Backable<S> iter = (Backable<S>)reader.unalcol();
		Vector<GeneralizedToken<S>> tokens = new Vector<GeneralizedToken<S>>();
		boolean flag = false;
		while(!flag){
			try{
				Collection<GeneralizedToken<S>> toks = lexer.process(iter);
				for(GeneralizedToken<S> t:toks) tokens.add(t);
				flag = true;
			}catch( Exception e ){
				tokens.add(new GeneralizedToken<S>(error, ((Trackable<S>)iter).pos()));
				try{ iter.next();	}catch(Exception ex){}
			}
		}
		return tokens;
	}
}