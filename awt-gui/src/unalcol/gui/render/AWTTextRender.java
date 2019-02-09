package unalcol.gui.render;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import unalcol.vc.Side;

public class AWTTextRender extends JPanel implements TextRender{
	protected String id;
	protected Side side;
	
	BorderLayout layout = new BorderLayout();
	JTextPane textArea = new JTextPane();
	JScrollPane scroll;
	/**
	 * 
	 */
	private static final long serialVersionUID = -8976916928457960190L;

	public AWTTextRender(){
		scroll = new JScrollPane(textArea);
		this.setLayout(layout);
		this.add(scroll, BorderLayout.CENTER);		
	}
	
	public AWTTextRender( String id ){
		this();
		this.id = id;
	}

	@Override
	public void setSide(Side side){ this.side = side; }

	@Override
	public Side side(){ return side; }

	@Override
	public String id(){ return id; }
	
	@Override
	public void setId(String id) { this.id = id; }

	@Override
	public void render(String str){ textArea.setText(str);	}
	
	@Override
	public void add(String str){ textArea.setText(textArea.getText()+str); }
	
	@Override
	public void init() { textArea.setText(""); }

	@Override
	public void render(){}
}
