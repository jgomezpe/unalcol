package unalcol.gui.paint;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class RenderPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1691456845015325692L;

	protected AWTRender render; 
	
	public RenderPanel( String id ){
		this( id, null );
		
	}

	public RenderPanel( String id, Drawable drawable ){
		setBackground(new Color(255,255,255));
		render = new AWTRender(id,this);
		if( drawable!=null ) render.render(drawable);
	}
	

	/**
	 * Paints the graphic component
	 * @param g Graphic component
	 */
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		render.setGraphics( g );
		if( render.scale()>0 ) render.render();
	}	
	
	public Render render(){ return render; }
	
	public void render( Drawable drawable ){ render.render(drawable); }	
}