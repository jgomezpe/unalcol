package unalcol.gui.render;

import javax.swing.JPanel;

public interface AWTRender extends Render{
	public void setPanel(JPanel panel);
	public JPanel panel();	
}