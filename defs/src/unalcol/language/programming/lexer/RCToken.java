package unalcol.language.programming.lexer;

import unalcol.io.Position;
import unalcol.types.collection.vector.Vector;

public class RCToken extends Token implements Position{
	protected int row;
	protected int column;
	public RCToken( int type, int offset, int[] lexeme, int row, int column ){
		super(type,offset,lexeme);
		this.row = row;
		this.column = column;
	}
	
	public RCToken( int type, int offset, Vector<Integer> lexeme, int row, int column ){
		super(type,offset,lexeme);
		this.row = row;
		this.column = column;
	}
	
	public RCToken( int offset, int row, int column ){
		super(offset);
		this.row = row;
		this.column = column;
	}
	
	@Override
	public int row(){ return row; }

	@Override
	public int column(){ return column; }

	@Override
	public void setRow(int row){ this.row = row; }

	@Override
	public void setColumn(int column) { this.column = column; }
}