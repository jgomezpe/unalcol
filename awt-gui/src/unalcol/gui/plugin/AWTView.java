package unalcol.gui.plugin;

import javax.swing.JComponent;

import unalcol.vc.frontend.View;

public interface AWTView extends View{
	public JComponent awt();
	default public void update(){ awt().updateUI(); }
}