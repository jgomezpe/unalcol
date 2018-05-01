package unalcol.gui;

import javax.swing.JComponent;

import unalcol.vc.View;

public interface AWTView extends View{
	public JComponent awt();
	default public void update(){ awt().updateUI(); }
}