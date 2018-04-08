package unalcol.gui;

public interface Editor {
	public String getText();
	public void setText( String text );
	public void highlight( int row );
	public void locate( int row, int col );
	public int row();
	public int column();
	public int[] position();
}
