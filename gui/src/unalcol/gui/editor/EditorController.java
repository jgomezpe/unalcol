package unalcol.gui.editor;

import unalcol.gui.Controller;

public interface EditorController extends Controller{
// Methods called by the View	
	public void text( String text );
	public void position( int row, int col );
}