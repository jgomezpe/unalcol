package unalcol.gui.render;

import javax.swing.JPanel;

import unalcol.vc.Side;

public class AWTTextRender implements AWTRender, TextRender{
	protected String id;
	protected Side side;
	protected JPanel panel = null;

	public AWTTextRender(String id) { this.id = id; }
	
	@Override
	public void setSide(Side side){ this.side = side; }

	@Override
	public Side side(){ return side; }

	@Override
	public void setId(String id) { this.id = id; }
	
	@Override
	public String id(){ return id; }

	@Override
	public void setPanel(JPanel panel) { this.panel=panel; }

	@Override
	public JPanel panel() { return panel; }

	@Override
	public void render(String str) {
		((TextRenderPanel)panel).textArea.setText(str);
	}
}