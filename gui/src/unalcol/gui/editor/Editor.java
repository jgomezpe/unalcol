package unalcol.gui.editor;

import unalcol.gui.GUIComponent;

public interface Editor extends GUIComponent{
	public String getText();
	public void setText( String text );
	public void highlight( int row );
	public void locate( int row, int col );
	public int row();
	public int column();
	public int[] position();
}
