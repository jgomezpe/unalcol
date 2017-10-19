package unalcol.language.programming.lexer;

import unalcol.language.Typed;
import unalcol.types.collection.vector.Vector;

public class Token<K> extends Typed{
	public static final int EOF = -1;
	
	protected K pos;
	protected int[] lexeme;
	
	public Token( int type, K pos ){
		super(type);		
		this.pos = pos;
		this.lexeme = new int[0];
	}

	public Token( int type, K pos, int[] lexeme ){
		this(type, pos);
		this.lexeme = (lexeme!=null)?lexeme:new int[0];
	}

	public Token( int type, K pos, Vector<Integer> lexeme ){
		this(type, pos);
		this.lexeme = new int[lexeme.size()];
		for( int i=0; i<this.lexeme.length; i++ ) this.lexeme[i] = lexeme.get(i);
	}

	public Token( K pos ){ this(EOF,pos); }

	public K pos(){ return pos; }
//	public int endOffset(){ return pos.offset()+length(); }

	public int[] lexeme(){ return lexeme; }
	public int length(){return lexeme.length;}	
}