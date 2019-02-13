package unalcol.gui.log;

import unalcol.vc.View;

public interface Log extends View{
    public void display( boolean output );	
	public void error( String message );
	public void out( String message );
}