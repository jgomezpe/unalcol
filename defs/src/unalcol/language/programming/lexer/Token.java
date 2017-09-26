package unalcol.language.programming.lexer;

import unalcol.language.Typed;
import unalcol.types.collection.vector.Vector;

public class Token extends Typed{
	public static final int EOF = -1;
	
	protected int offset;
	protected int[] lexeme;
	
	public Token( int type, int offset, int[] lexeme ){
		super(type);		
		this.offset = offset;
		this.lexeme = (lexeme!=null)?lexeme:new int[0];
	}

	public Token( int type, int offset, Vector<Integer> lexeme ){
		super(type);
		this.offset = offset;
		this.lexeme = new int[lexeme.size()];
		for( int i=0; i<this.lexeme.length; i++ ) this.lexeme[i] = lexeme.get(i);
	}

	public Token( int offset ){ this(EOF,offset,new int[0]); }

	public int offset(){ return offset; }
	public int length(){return lexeme.length;}
	public int endOffset(){ return offset+length(); }
	public void shift( int shift ){ offset += shift; }
	public int[] lexeme(){ return lexeme; }
}