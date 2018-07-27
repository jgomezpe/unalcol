package unalcol.gui.paint;

import unalcol.gui.paint.Color;

public class Canvas{
	public final static int SCALE=100;
	protected Color color = new Color(0,0,0,0);
	protected CanvasRender render;
	
	public Canvas( CanvasRender render ){ this.render = render; }
	
	public Color setColor( Color color ){
		Color p = this.color;
		this.color = color;
		render.add(new ColorInstruction(color));
		return p;
	}
	
	public void drawLine( int start_x, int start_y, int end_x, int end_y ){ render.add(new LineInstruction(start_x, start_y, end_x, end_y)); }

	public void drawPolygon( int[] x, int[] y ){ render.add(new PolygonInstruction(x,y) );}	

	public void drawPolyline( int[] x, int[] y ){
		int e = x.length-1;
		for( int i=0; i<e; i++ ) drawLine( x[i], y[i], x[i+1], y[i+1] );
	}	
	
	public void drawImage( int start_x, int start_y, int width, int height, int rot, String image ){
		render.add(new ImageInstruction(start_x, start_y, width, height, rot, image));
	}	
	public void drawString( int x, int y, String str ){ render.add(new TextInstruction(x, y, str));}
	
	public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle){
		render.add(new ArcInstruction(x, y, width, height, startAngle, arcAngle));
	}
	
	public void drawFillArc(int x, int y, int width, int height, int startAngle, int arcAngle){
		render.add(new FillArcInstruction(x, y, width, height, startAngle, arcAngle));
	}
	
	public void drawRect( int x, int y, int width, int height ){
		drawPolyline( new int []{x, x+width, x+width, x, x}, new int[]{y, y, y+height, y+height, y} ); 
	}

	public void drawFillRect( int x, int y, int width, int height ){
		drawPolygon( new int []{x, x+width, x+width, x, x}, new int[]{y, y, y+height, y+height, y} ); 
	}
	
	public void drawOval( int x, int y, int width, int height ){
		drawArc( x, y, width, height, 0, 360);
	}
	
	public void drawFillOval( int x, int y, int width, int height ){ drawFillArc( x, y, width, height, 0, 360); }	
}