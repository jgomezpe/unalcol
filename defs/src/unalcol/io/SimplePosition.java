package unalcol.io;

public class SimplePosition implements Position{
	protected int row;
	protected int column;
	
	public SimplePosition(){
		row = 0;
		column = 0;
	}
	
	public SimplePosition( Position pos ){
		this.row = pos.row();
		this.column = pos.column();
	}
	
	public SimplePosition( int row, int column ){
		this.column = column;
		this.row = row;
	}
	
	public SimplePosition( String pos ){
		int k = pos.indexOf(','); 
		row = Integer.parseInt(pos.substring(1, k));
		column = Integer.parseInt(pos.substring(k+1,pos.length()-1));
	}
	
	public void setRow(int row){ this.row = row; }
	public void setColumn(int column){ this.column = column; }
	
	public int row(){ return row; }
	public int column(){ return column; }
	
	public String toString(){
		return "("+row+","+column+")";
	}	
}