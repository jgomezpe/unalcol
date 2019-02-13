package unalcol.language.generalized;

import unalcol.language.Typed;
import unalcol.collection.Vector;
import unalcol.iterator.PositionTrack;

public class GeneralizedToken<S> extends Typed{
	public static final int EOF = -1;
	
	protected PositionTrack pos;
	protected Vector<S> lexeme;
	
	public GeneralizedToken( int type, PositionTrack pos ){ this( type, pos, new Vector<S>()); }

	public GeneralizedToken( int type, PositionTrack pos, Vector<S> lexeme ){
		super(type);
		this.pos = pos;
		this.lexeme = lexeme;
	}

	public GeneralizedToken( int type, PositionTrack pos, S[] lexeme ){ this(type, pos, new Vector<>(lexeme)); }
	
	public GeneralizedToken( PositionTrack pos ){ this(EOF,pos); }

	public PositionTrack pos(){ return pos; }
//	public int endOffset(){ return pos.offset()+length(); }

	public Vector<S> lexeme(){ return lexeme; }
	public int length(){return lexeme.size();}	
}