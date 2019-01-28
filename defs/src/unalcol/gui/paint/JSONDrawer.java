package unalcol.gui.paint;

import unalcol.json.JSON;
import unalcol.types.collection.Collection;
import unalcol.types.collection.FiniteCollection;

public class JSONDrawer implements Drawable{
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

	protected JSON json;
	
	public JSONDrawer( JSON json ) { this.json = json; }

	protected int get( JSON json, String tag ){
		Integer i = (Integer)json.get(tag);
		if( i==null ) return 0;
		return i;
	}
	
	protected boolean getBool( JSON json, String tag ){
		Boolean i = (Boolean)json.get(tag);
		if( i==null ) return false;
		return i;
	}
	
	protected int[] coordinates( FiniteCollection<Object> v ){
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
	protected int[] x( JSON json ){ return coordinates( (FiniteCollection<Object>)json.get(X) ); }

	@SuppressWarnings("unchecked")
	protected int[] y( JSON json ){ return coordinates( (FiniteCollection<Object>)json.get(Y) ); }
	
	protected ColorInstance cinstance = new ColorInstance();
	
	protected Color color( JSON json ){
		Object obj = json.get(ColorInstance.COLOR);
		if( obj == null ) return null;
		return cinstance.load((JSON)obj);	
	}
	
	/**
	 * Drawing a command. A command is a KeyMap with the following pairs
	 * type={line, image, ..., composed}
	 * x=[] array of integers  with the x coordinates of the start (x[0]) and end (x[1]) points of the line 
	 * y=[] array of integers  with the y coordinates of the start (y[0]) and end (y[1]) points of the line
	 * color=rgba value (optional) 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void draw(Canvas canvas) {
		String type = (String)json.get(COMMAND);
		if( type==null ) return;
		int c = type.charAt(0);
		Color color = color(json);
		if(color!=null) canvas.setColor(color);
		switch( c ){
			case 'l':  // It is a line command
			case 'p': // It is a polyline or polygon command
				int[] x = coordinates( (FiniteCollection<Object>)json.get(X) );
				int[] y = coordinates( (FiniteCollection<Object>)json.get(Y) );
				if( c=='l' ) canvas.drawLine(x[0], y[0], x[1], y[1]);	 // It is a line command
				else if(type.charAt(4)=='l' ) canvas.drawPolyline(x, y); // It is a polyline command
					 else canvas.drawPolygon(x, y); // it is a polygon command
			break;
			case 't': canvas.drawString(get(json,X), get(json,Y), (String)json.get(MESSAGE)); break; // It is a text command
			case 'c': // It is a compound command
				Collection<Object> v = (Collection<Object>)json.get(COMMANDS);
				for( Object o:v ) 
					if( o instanceof JSON ){
						JSONDrawer drawer = new JSONDrawer((JSON)o);
						drawer.draw( canvas );
					}
			break;
			default:
				int sx = get(json,X);
				int sy = get(json,Y);
				int width = get(json,WIDTH);
				int height = get(json,HEIGHT);
				switch(c){
					case 'i': canvas.drawImage(sx, sy, width, height, get(json,IMAGE_ROT), getBool(json,IMAGE_REF), (String)json.get(IMAGE_URL)); break; // It is an image command 
					case 'r': canvas.drawRect(sx, sy, width, height); break; // It is a rect command 
					case 'o': canvas.drawOval(sx, sy, width, height); break; // It is an oval command 
					case 'a': canvas.drawArc(sx, sy, width, height, get(json,START_ANGLE), get(json,END_ANGLE)); break; // It is an arc command
					case 'f': // It is a fill command
						switch( type.charAt(4) ){
							case 'R': canvas.drawFillRect(sx, sy, width, height); break; // It is a fillRect command 
							case 'O': canvas.drawOval(sx, sy, width, height); break; // It is a fillOval command 
							case 'A': canvas.drawFillArc(sx, sy, width, height, get(json,START_ANGLE), get(json,END_ANGLE)); break; // It is a fillArc command 
						}
					break;	
				}
		}		
	}

	@Override
	public int scale(int width, int height) {
		// TODO Auto-generated method stub
		return 0;
	}

}
