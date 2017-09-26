package unalcol.language.lexer;

import unalcol.language.LanguageException;
import unalcol.language.Token;
import unalcol.types.collection.array.Array;
import unalcol.types.collection.array.ImmutableArray;
import unalcol.types.collection.vector.Vector;

public class LookAHeadLexer<T> implements Lexer<T> {
	protected Vector<Lexeme<T>> lexeme;
	protected int[] dontcarelexeme;
	
	public LookAHeadLexer(){
		lexeme = null;
		dontcarelexeme = null;
	}

	public LookAHeadLexer( Vector<Lexeme<T>> lexema ){
		this( lexema, new int[0] );
	
	}
	
	public LookAHeadLexer( Vector<Lexeme<T>> lexema, int[] dontcare_lexema ){
		this.lexeme = lexema;
		this.dontcarelexeme = dontcare_lexema;
	}

	public Array<Token<T>> apply( ImmutableArray<T> buffer ) throws LanguageException{
		return apply(buffer,0);
	}	

	public Array<Token<T>> apply( ImmutableArray<T> buffer, int offset ) throws LanguageException{
		boolean flag = true;
		Vector<Token<T>> list = new Vector<Token<T>>();
		while( flag && offset<buffer.size() ){
			int max = 0;
			int lex = -1;
			for( int i=0; i<lexeme.size(); i++){
				int length = lexeme.get(i).get(buffer, offset);
				if( length > max ){
					max = length;
					lex = lexeme.get(i).type();
				}
			}
			flag = lex >= 0;
			if( flag ){
				int k=0;
				while( k<dontcarelexeme.length && lex != dontcarelexeme[k] ){ k++; }
				if( k==dontcarelexeme.length ){
					list.add(new Token<T>(lex, offset, max,buffer));
				}
				offset += max;
			}
		}
		if( !flag ){
			byte[] b = new byte[Math.min(20, buffer.size()-offset)];
			System.arraycopy(buffer, offset, b, 0, b.length);
			throw new LanguageException("Lexical error at position ["+offset+"]:"+new String(b));
		}
		return list;
	}	
}