package unalcol.gui.paint;

import unalcol.gui.paint.Color;
import unalcol.vc.frontend.View;

public interface Canvas extends View{
	public final static int SCALE=100;
	
	public Color setColor( Color color );
	public int setScale( int scale );
	public int getScale();
	
	public void drawLine( int start_x, int start_y, int end_x, int end_y );	
	public void drawPolygon( int[] x, int[] y );	
	public void drawPolyline( int[] x, int[] y );	
	public void drawImage( int start_x, int start_y, int width, int height, int rot, Object image );	
	public void drawString( int x, int y, String str );
	public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle);
	public void drawFillArc(int x, int y, int width, int height, int startAngle, int arcAngle);
	
	default void drawRect( int x, int y, int width, int height ){
		drawPolyline( new int []{x, x+width, x+width, x, x}, new int[]{y, y, y+height, y+height, y} ); 
	}

	default void drawFillRect( int x, int y, int width, int height ){
		drawPolygon( new int []{x, x+width, x+width, x, x}, new int[]{y, y, y+height, y+height, y} ); 
	}
	
	default void drawOval( int x, int y, int width, int height ){
		drawArc( x, y, width, height, 0, 360);
	}
	
	default void drawFillOval( int x, int y, int width, int height ){
		drawFillArc( x, y, width, height, 0, 360);
	}
	
	default int scale( int value ){	return value*getScale()/SCALE; }
	
	default int[] scale( int[] value ){
		int[] svalue = new int[value.length];
		for( int i=0; i<svalue.length; i++ ) svalue[i] = scale(value[i]);
		return svalue;
	}
}