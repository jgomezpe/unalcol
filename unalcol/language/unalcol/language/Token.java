package unalcol.language;

import unalcol.language.generalized.GeneralizedToken;
import unalcol.collection.Vector;
import unalcol.iterator.PositionTrack;

public class Token extends GeneralizedToken<Integer>{
	public Token( PositionTrack pos ){ this(EOF,pos); }
	
	public Token( int type, PositionTrack pos ){ this( type, pos, new Vector<Integer>()); }

	public Token(int type, PositionTrack pos, Vector<Integer> lexeme){ super(type, pos, lexeme); }
	public Token(int type, PositionTrack pos, Integer[] lexeme){ super(type, pos, lexeme); }
	public Token(int type, PositionTrack pos, int[] lexeme){
		this(type, pos);
		for(int i:lexeme) this.lexeme.add(i);
	}	
}