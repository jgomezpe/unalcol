package unalcol.gui.paint;

import unalcol.collection.keymap.HashMap;
import unalcol.json.JSON;

public abstract class Canvas{
	protected double scale=1;

	protected HashMap<String, Integer> primitives = new HashMap<String,Integer>();

	public double scale(){ return scale; }
	public void setScale( double scale ){ this.scale = scale; }
	public double scale( double value ){ return (value*scale()); }
	public double[] scale( double[] value ){
		if( value == null ) return null;
		double[] svalue = new double[value.length];
		for( int i=0; i<svalue.length; i++ ) svalue[i] = scale(value[i]);
		return svalue;
	}
	
	public Canvas(){
		primitives.set(Command.COMPOUND,0);
		primitives.set(Command.MOVETO,1);
		primitives.set(Command.LINETO,2);
		primitives.set(Command.QUADTO,3);
		primitives.set(Command.CURVETO,4);
		primitives.set(Command.TEXT,5);
		primitives.set(Command.IMAGE,6);
		primitives.set(Command.BEGIN,7);
		primitives.set(Command.CLOSE,8);
		primitives.set(Command.STROKE,9);
		primitives.set(Command.FILL,10);
		primitives.set(Command.STROKESTYLE,11);
		primitives.set(Command.FILLSTYLE,12);
		primitives.set(Command.LINE,13);
		primitives.set(Command.POLYLINE,14);
		primitives.set(Command.POLYGON,15);
	}

	public abstract void moveTo( Command c );
	public abstract void lineTo( Command c );
	public abstract void quadTo( Command c );
	public abstract void curveTo( Command c );
	public abstract void image( Command c );
	public abstract void text( Command c );
	public abstract void beginPath();
	public abstract void closePath();
	public abstract void strokeStyle( Command c );
	public abstract void fillStyle( Command c );
	public abstract void stroke();
	public abstract void fill();

	public void compound( Command c ){
		Command[] commands =c.commands();
		for( Command v : commands ){ command(v); }
	}
	
	public void line( Command c ){
		double[] x = c.getRealArray(Command.X);
		double[] y = c.getRealArray(Command.Y);
		beginPath();
		moveTo(Command.moveTo(x[0],y[0]));
		lineTo(Command.lineTo(x[1],y[1]));
		stroke();		
	}

	protected void poly( Command c ){
		beginPath();
		double[] x = c.getRealArray(Command.X);
		double[] y = c.getRealArray(Command.Y);
		moveTo(Command.moveTo(x[0],y[0]));
		for( int i=1; i<x.length; i++) lineTo(Command.lineTo(x[i],y[i]));
	}
	
	public void polyline( Command c ){
		poly(c);
		stroke();
	}

	public void polygon( Command c ){
		poly(c);
		fill();
	}
	
	public void command( Command c ){
		String type = c.type();
		if( type == null ) return;
		try{
			int cId = primitives.get(type);
			switch(cId){
				case 0: compound(c); break; 
				case 1: moveTo(c); break; 
				case 2: lineTo(c); break; 
				case 3: quadTo(c); break; 
				case 4: curveTo(c); break; 
				case 5: text(c); break; 
				case 6: image(c); break; 
				case 7: beginPath(); break; 
				case 8: closePath(); break; 
				case 9: stroke(); break; 
				case 10: fill(); break; 
				case 11: strokeStyle(c); break; 
				case 12: fillStyle(c); break; 
				case 13: line(c); break; 
				case 14: polyline(c); break; 
				case 15: polygon(c); break; 
			}
		}catch(Exception e){}	
	}
	
	protected ColorInstance cinstance = new ColorInstance();
	
	public Color color( JSON json ){ return color(json, ColorInstance.COLOR); }
	
	public Color color( JSON json, String tag ){
		try{
			Object obj = json.get(tag);
			return cinstance.load((JSON)obj);	
		}catch( Exception e ){ return null; }
	}
		
	/**
	public abstract Color setColor( Color color );
	
	public abstract void drawLine( int start_x, int start_y, int end_x, int end_y );
	
	public abstract void drawPolygon( int[] x, int[] y );	
	
	public abstract void drawImage( int start_x, int start_y, int width, int height, int rot, boolean reflex, String url ); 
	
	public abstract void drawString( int x, int y, String str ); 
	
	public abstract void drawArc(int x, int y, int width, int height, int startAngle, int endAngle); 

	public abstract void drawFillArc(int x, int y, int width, int height, int startAngle, int endAngle );

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
*/	
}