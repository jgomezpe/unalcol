package unalcol.gui.render;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class TextRenderPanel extends RenderPanel{
	BorderLayout layout = new BorderLayout();
	JTextPane textArea = new JTextPane();
	JScrollPane scroll;
	/**
	 * 
	 */
	private static final long serialVersionUID = -8976916928457960190L;

	public TextRenderPanel( String id ){
		super(new AWTTextRender(id));
		render.setPanel(this);
		scroll = new JScrollPane(textArea);
		this.setLayout(layout);
		this.add(scroll, BorderLayout.CENTER);
	}
}
