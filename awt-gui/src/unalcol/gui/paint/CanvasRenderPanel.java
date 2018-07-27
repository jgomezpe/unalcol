package unalcol.gui.paint;

import java.awt.Color;
import java.awt.Graphics;

import unalcol.gui.render.RenderPanel;

public class CanvasRenderPanel extends RenderPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1691456845015325692L;

	public CanvasRenderPanel( String id ){ this( id, null ); }

	public CanvasRenderPanel( String id, Drawable drawable ){
		super(new AWTCanvasRender(id));
		render.setPanel(this);
		setBackground(new Color(255,255,255));
		if( drawable!=null ) render.render(drawable);
	}
	

	/**
	 * Paints the graphic component
	 * @param g Graphic component
	 */
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		AWTCanvasRender r = (AWTCanvasRender)render;
		r.setGraphics( g );
		if( r.scale()>0 ) r.render();
	}		
}