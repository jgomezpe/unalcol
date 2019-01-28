package unalcol.gui.paint;

import unalcol.gui.paint.Color;
import unalcol.json.JSON;

public interface Canvas{
	
	void setScale( double scale );
	
	double scale();
	
	default int scale( int value ){ return (int)(value*scale()); }
	
	default int[] scale( int[] value ){
		if( value == null ) return null;
		int[] svalue = new int[value.length];
		for( int i=0; i<svalue.length; i++ ) svalue[i] = scale(value[i]);
		return svalue;
	}
	
	/**
	 * Sets the new painting color, returns the previous color
	 * @param color
	 * @return
	 */
	public Color setColor( Color color );
	
	public void drawLine( int start_x, int start_y, int end_x, int end_y );

	public void drawPolygon( int[] x, int[] y );	

	public void drawImage( int start_x, int start_y, int width, int height, int rot, boolean reflex, String image_url ); 
	
	public void drawString( int x, int y, String str ); 
	
	public void drawArc(int x, int y, int width, int height, int startAngle, int endAngle); 
	
	public void drawFillArc(int x, int y, int width, int height, int startAngle, int endAngle);

	public default void drawPolyline( int[] x, int[] y ){
		int e = x.length-1;
		for( int i=0; i<e; i++ ) drawLine( x[i], y[i], x[i+1], y[i+1] );
	}	
	
	default void drawRect( int x, int y, int width, int height ){ drawPolyline( new int []{x, x+width, x+width, x, x}, new int[]{y, y, y+height, y+height, y} ); }

	default void drawFillRect( int x, int y, int width, int height ){ drawPolygon( new int []{x, x+width, x+width, x, x}, new int[]{y, y, y+height, y+height, y} );	}
	
	default void drawOval( int x, int y, int width, int height ){ drawArc( x, y, width, height, 0, 360); }
	
	default void drawFillOval( int x, int y, int width, int height ){ drawFillArc( x, y, width, height, 0, 360); }
	
	default void drawJSON( JSON json ){
		JSONDrawer drawer = new JSONDrawer(json);
		drawer.draw(this);
	}
}