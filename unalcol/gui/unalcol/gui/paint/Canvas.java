package unalcol.gui.paint;

import unalcol.gui.paint.Color;
import unalcol.json.JSON;
import unalcol.collection.array.ArrayUtil;
import unalcol.collection.keymap.HashMap;

public abstract class Canvas{
	public final static String COMMAND="command";
	public final static String COMPOUND="compound";
	public final static String LINE="line";
	public final static String POLYLINE="polyline";
	public final static String POLYGON="polygon";
	public final static String TEXT="text";
	public final static String RECT="rect";
	public final static String FILLRECT="fillrect";
	public final static String ARC="arc";
	public final static String FILLARC="fillarc";
	public final static String OVAL="oval";
	public final static String FILLOVAL="filloval";
	public final static String IMAGE="image";

	public final static String X="x";
	public final static String Y="y";
	public final static String WIDTH="width";
	public final static String HEIGHT="height";
	public final static String MESSAGE="message";
	public final static String IMAGE_URL="url";
	public final static String IMAGE_ROT="rotation";
	public final static String IMAGE_REF="reflection";
	public final static String START_ANGLE="start_angle";
	public final static String END_ANGLE="end_angle";
	public final static String COMMANDS="commands";
	public final static String DEF="def";

	protected double scale=1;

	protected HashMap<String, JSON> commands = new HashMap<String,JSON>();
	protected HashMap<String, Integer> primitives = new HashMap<String,Integer>();

	public Canvas(){
		primitives.set(COMPOUND,0);
		primitives.set(LINE,1);
		primitives.set(POLYLINE,2);
		primitives.set(POLYGON,3);
		primitives.set(TEXT,4);
		primitives.set(IMAGE,5);
		primitives.set(RECT,6);
		primitives.set(OVAL,7);
		primitives.set(ARC,8);
		primitives.set(FILLRECT,9);
		primitives.set(FILLOVAL,10);
		primitives.set(FILLARC,11);
	}

	/**
	 * Sets the new painting color, returns the previous color
	 * @param color
	 * @return
	 */
	public abstract Color setColor( Color color );
	
	public abstract void drawLine( int start_x, int start_y, int end_x, int end_y );
	
	public abstract void drawPolygon( int[] x, int[] y );	
	
	public abstract void drawImage( int start_x, int start_y, int width, int height, int rot, boolean reflex, String url ); 
	
	public abstract void drawString( int x, int y, String str ); 
	
	public abstract void drawArc(int x, int y, int width, int height, int startAngle, int endAngle); 

	public abstract void drawFillArc(int x, int y, int width, int height, int startAngle, int endAngle );

	public double scale(){ return scale; }

	public void setScale( double scale ){ this.scale = scale; }
	
	public int scale( int value ){ return (int)(value*scale()); }
	
	public int[] scale( int[] value ){
		if( value == null ) return null;
		int[] svalue = new int[value.length];
		for( int i=0; i<svalue.length; i++ ) svalue[i] = scale(value[i]);
		return svalue;
	}

	public void drawPolyline( int[] x, int[] y ){
		System.out.println("[Canvas.drawPolyline]");
		int e = x.length-1;
		for( int i=0; i<e; i++ ) drawLine( x[i], y[i], x[i+1], y[i+1] );
	}	
	
	public void drawRect( int x, int y, int width, int height ){ drawPolyline( new int []{x, x+width, x+width, x, x}, new int[]{y, y, y+height, y+height, y} ); }

	public void drawFillRect( int x, int y, int width, int height ){ drawPolygon( new int []{x, x+width, x+width, x, x}, new int[]{y, y, y+height, y+height, y} );	}
	
	public void drawOval( int x, int y, int width, int height ){ drawArc( x, y, width, height, 0, 360); }
	
	public void drawFillOval( int x, int y, int width, int height ){ drawFillArc( x, y, width, height, 0, 360); }
	
	// JSON drawing methods

	public JSON get(String command){ try{ return commands.get(command); }catch(Exception e){ return null; } }

	public boolean register(JSON command){
		try{
			String type = (String)command.get(COMMAND);
			if( isPrimitive(type) ) return false;
			commands.set(type, (JSON)command.get(DEF));
			return true;
		}catch(Exception e){ return false; }	
	} 

	public int[] coordinates( JSON json, String tag ){
		Object[] v = json.getArray(tag);
		System.out.println("[Canvas.coordinates]"+v.length);
		int n = v.length;
		int[] p = new int[n];
		for( int i=0; i<n; i++ ) p[i] = (Integer)v[i];
		return p;
	}

//	public 
	public int[] x( JSON json ){ return coordinates( json, X ); }

	public int[] y( JSON json ){ return coordinates( json, Y ); }
	
	protected ColorInstance cinstance = new ColorInstance();
	
	public Color color( JSON json ){
		try{
			Object obj = json.get(ColorInstance.COLOR);
			return cinstance.load((JSON)obj);	
		}catch( Exception e ){ return null; }
	}
	
	public boolean isPrimitive( String command ){ return primitives.valid(command); }
		
	public void drawJSON( JSON json ){
		System.out.println("[Canvas.drawJSON]"+json);
		String type;
		try{ type = (String)json.get(COMMAND); }catch(Exception e){ return; }
		JSON j = get(type);
		if( j != null ){ drawJSON( j ); return; } // Stored command
		
		Color color = color(json);
		if(color!=null) setColor(color);
		try{
			int c = primitives.get(type);
			if( c==0 ) { // It is a compound command
				Object[] v = json.getArray(COMMANDS);
				for( Object o:v ) if( o instanceof JSON ) drawJSON( (JSON)o );
				return;
			}	
			if( c<4 ){
				System.out.println("[Canvas.drawJSON]"+c);
				int[] x = coordinates( json, X );
				int[] y = coordinates( json, Y );
				switch( c ){
					case 1: drawLine(x[0], y[0], x[1], y[1]); break; // It is a line command					
					case 2: drawPolyline(x, y); break; // It is a polyline or polygon command
					default: drawPolygon(x, y); // it is a polygon command
				}	
				return; 
			}
			
			int x = json.getInt(X);
			int y = json.getInt(Y);
	
			if( c==4 ){ drawString(x, y, (String)json.get(MESSAGE)); return; }// It is a text command
	
			int width = json.getInt(WIDTH);
			int height = json.getInt(HEIGHT);
			
			switch( c ){
				case 5: drawImage(x, y, width, height, json.getInt(IMAGE_ROT), json.getBool(IMAGE_REF), (String)json.get(IMAGE_URL)); break; // It is an image command 
				case 6: drawRect(x, y, width, height); break; // It is a rect command 
				case 7: drawOval(x, y, width, height); break; // It is an oval command 
				case 8: drawArc(x, y, width, height, json.getInt(START_ANGLE), json.getInt(END_ANGLE)); break; // It is an arc command
				case 9: drawFillRect(x, y, width, height); break; // It is a fillRect command 
				case 10: drawFillOval(x, y, width, height); break; // It is a fillOval command 
				case 11: drawFillArc(x, y, width, height, json.getInt(START_ANGLE), json.getInt(END_ANGLE)); break; // It is a fillArc command 
			}
		}catch(Exception e){}	
	}
	
	public JSON jsonLine( int start_x, int start_y, int end_x, int end_y ){
		JSON json = new JSON();
		json.set(COMMAND, LINE);
		json.set(X, ArrayUtil.cast( start_x, end_x ));
		json.set(Y, ArrayUtil.cast( start_y, end_y ));
		return json;
	}
	
	public JSON json( String command, int[] x, int[] y ){
		JSON json = new JSON();
		json.set(COMMAND, command);
		json.set(X, unalcol.integer.Array.cast(x));
		json.set(Y, unalcol.integer.Array.cast(y));
		return json;
	}
	
	public JSON jsonPolygon( int[] x, int[] y ){ return json(POLYGON, x, y); }

	public JSON jsonPolyline( int[] x, int[] y ){ return json(POLYLINE, x, y); }

	public JSON json( String command, int x, int y, int width, int height ){
		JSON json = new JSON();
		json.set(COMMAND, command);
		json.set(X, x);
		json.set(Y, y);
		json.set(WIDTH, width);
		json.set(HEIGHT, height);
		return json;
	}

	public JSON jsonRect( int x, int y, int width, int height ){ return json( RECT, x, y, width, height ); }
	
	public JSON jsonFillRect( int x, int y, int width, int height ){ return json( FILLRECT, x, y, width, height ); }
	
	public JSON jsonOval( int x, int y, int width, int height ){ return json( OVAL, x, y, width, height ); }
	
	public JSON jsonFillOval( int x, int y, int width, int height ){ return json( FILLOVAL, x, y, width, height); }
	
	public JSON jsonImage( int x, int y, int width, int height, int rot, boolean reflex, String url ){
		JSON json = json(IMAGE, x, y, width, height );
		json.set(IMAGE_ROT, rot);
		json.set(IMAGE_REF, reflex);
		json.set(IMAGE_URL, url);
		return json;
	}
	
	public JSON jsonArc(int x, int y, int width, int height, int startAngle, int endAngle){
		JSON json = json(ARC, x, y, width, height );
		json.set(START_ANGLE, startAngle);
		json.set(END_ANGLE, endAngle);
		return json;
	} 
	
	public JSON jsonFillArc(int x, int y, int width, int height, int startAngle, int endAngle){
		JSON json = json(FILLARC, x, y, width, height );
		json.set(START_ANGLE, startAngle);
		json.set(END_ANGLE, endAngle);
		return json;
	}

	public JSON jsonString( int x, int y, String str ){
		JSON json = new JSON();
		json.set(COMMAND, TEXT);
		json.set(X, x);
		json.set(Y, y);
		json.set(MESSAGE, str);
		return json;
	} 	
}