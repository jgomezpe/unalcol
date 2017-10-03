package unalcol.language.programming.lexer;

import unalcol.io.Position;
import unalcol.language.Typed;
import unalcol.types.collection.vector.Vector;

public class Token extends Typed{
	public static final int EOF = -1;
	
	protected Position pos;
	protected int[] lexeme;
	
	public Token( int type, Position pos ){
		super(type);		
		this.pos = pos;
		this.lexeme = new int[0];
	}

	public Token( int type, Position pos, int[] lexeme ){
		this(type, pos);
		this.lexeme = (lexeme!=null)?lexeme:new int[0];
	}

	public Token( int type, Position pos, Vector<Integer> lexeme ){
		this(type, pos);
		this.lexeme = new int[lexeme.size()];
		for( int i=0; i<this.lexeme.length; i++ ) this.lexeme[i] = lexeme.get(i);
	}

	public Token( Position pos ){ this(EOF,pos); }

	public Position pos(){ return pos; }
	public int endOffset(){ return pos.offset()+length(); }

	public int[] lexeme(){ return lexeme; }
	public int length(){return lexeme.length;}	
}