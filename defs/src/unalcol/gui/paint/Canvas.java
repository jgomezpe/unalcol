package unalcol.gui.paint;

import unalcol.gui.paint.Color;
import unalcol.json.JSON;
import unalcol.types.collection.Collection;
import unalcol.types.collection.FiniteCollection;

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
	
	// JSON drawing methods
	public final static String COMMAND="command";
	public final static String X="x";
	public final static String Y="y";
	public final static String WIDTH="width";
	public final static String HEIGHT="height";
	public final static String COMPOUND="compound";
	public final static String COMMANDS="commands";
	public final static String LINE="line";
	public final static String POLYGON="polygon";
	public final static String POLYLINE="polyline";
	public final static String TEXT="text";
	public final static String MESSAGE="message";
	public final static String IMAGE="image";
	public final static String IMAGE_URL="url";
	public final static String IMAGE_ROT="rotation";
	public final static String IMAGE_REF="reflection";
	public final static String RECT="rect";
	public final static String FILLERECT="fillrect";
	public final static String ARC="arc";
	public final static String START_ANGLE="start_angle";
	public final static String END_ANGLE="end_angle";
	public final static String FILLEARC="fillarc";

	default int[] coordinates( FiniteCollection<Object> v ){
		int n = v.size();
		int[] p = new int[n];
		int i=0;
		for( Object o:v ){
			p[i] = (Integer)o;
			i++;
		}
		return p;
	}

	@SuppressWarnings("unchecked")
	default int[] x( JSON json ){ return coordinates( (FiniteCollection<Object>)json.get(X) ); }

	@SuppressWarnings("unchecked")
	default int[] y( JSON json ){ return coordinates( (FiniteCollection<Object>)json.get(Y) ); }
	
	ColorInstance cinstance = new ColorInstance();
	
	default Color color( JSON json ){
		Object obj = json.get(ColorInstance.COLOR);
		if( obj == null ) return null;
		return cinstance.load((JSON)obj);	
	}
		
	default void drawJSON( JSON json ){
		String type = (String)json.get(COMMAND);
		if( type==null ) return;
		int c = type.charAt(0);
		Color color = color(json);
		if(color!=null) setColor(color);
		switch( c ){
			case 'l':  // It is a line command
			case 'p': // It is a polyline or polygon command
				@SuppressWarnings("unchecked") int[] x = coordinates( (FiniteCollection<Object>)json.get(X) );
				@SuppressWarnings("unchecked") int[] y = coordinates( (FiniteCollection<Object>)json.get(Y) );
				if( c=='l' ) drawLine(x[0], y[0], x[1], y[1]);	 // It is a line command
				else if(type.charAt(4)=='l' ) drawPolyline(x, y); // It is a polyline command
					 else drawPolygon(x, y); // it is a polygon command
			break;
			case 't': drawString(json.getInt(X), json.getInt(Y), (String)json.get(MESSAGE)); break; // It is a text command
			case 'c': // It is a compound command
				@SuppressWarnings("unchecked") Collection<Object> v = (Collection<Object>)json.get(COMMANDS);
				for( Object o:v ) if( o instanceof JSON )	drawJSON( (JSON)o );
			break;
			default:
				int sx = json.getInt(X);
				int sy = json.getInt(Y);
				int width = json.getInt(WIDTH);
				int height = json.getInt(HEIGHT);
				switch(c){
					case 'i': drawImage(sx, sy, width, height, json.getInt(IMAGE_ROT), json.getBool(IMAGE_REF), (String)json.get(IMAGE_URL)); break; // It is an image command 
					case 'r': drawRect(sx, sy, width, height); break; // It is a rect command 
					case 'o': drawOval(sx, sy, width, height); break; // It is an oval command 
					case 'a': drawArc(sx, sy, width, height, json.getInt(START_ANGLE), json.getInt(END_ANGLE)); break; // It is an arc command
					case 'f': // It is a fill command
						switch( type.charAt(4) ){
							case 'R': drawFillRect(sx, sy, width, height); break; // It is a fillRect command 
							case 'O': drawOval(sx, sy, width, height); break; // It is a fillOval command 
							case 'A': drawFillArc(sx, sy, width, height, json.getInt(START_ANGLE), json.getInt(END_ANGLE)); break; // It is a fillArc command 
						}
					break;	
				}
		}		
	}
}