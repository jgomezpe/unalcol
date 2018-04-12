package unalcol.gui.editor;

import unalcol.gui.GUIComponent;

public interface EditorView extends GUIComponent{
	public void setText( String text );
	public void highlight( int row );
	public void locate( int row, int col );
}