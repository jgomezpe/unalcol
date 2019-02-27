package unalcol.gui.paint;

import unalcol.clone.Cloneable;
import unalcol.json.JSON;

public class Command extends JSON{
	public final static String COMMAND="command";
	public final static String COMPOUND="compound";
	public final static String MOVETO="moveTo";
	public final static String LINETO="lineTo";
	public final static String QUADTO="quadTo";
	public final static String CURVETO="curveTo";
	public final static String TEXT="text";
	public final static String IMAGE="image";
	public final static String BEGIN="beginPath";
	public final static String CLOSE="closePath";
	public final static String STROKE="stroke";
	public final static String FILL="fill";
	public final static String STROKESTYLE="strokeStyle";
	public final static String FILLSTYLE="fillStyle";
	public final static String LINE="line";
	public final static String POLYLINE="polyline";
	public final static String POLYGON="polygon";
	// Arguments of the command
	public final static String COMMANDS="commands";
	public final static String X="x";
	public final static String Y="y";
	public final static String MESSAGE="message";
	public final static String IMAGE_URL="url";
	public final static String IMAGE_ROT="rotation";
	public final static String IMAGE_REF="reflection";
	public final static String LINEWIDTH="lineWidth";
	public final static String RADIAL="radial";
	public final static String STARTCOLOR="startcolor";
	public final static String ENDCOLOR="endcolor";
	public final static String R="r";
	
	public Command( String type ){ set(COMMAND, type); }
	
	public Command( JSON source ){
		try{
			for( String key:source.keys() ){
				if( key.equals(COMMANDS) ){
					Object[] commands = source.getArray(COMMANDS);
					Object[] cs = new Object[commands.length];
					for( int i=0; i<commands.length; i++ ) cs[i] = new Command((JSON)commands[i]);
					set(key, cs);	
				}else{
					Object obj = source.get(key);
					Cloneable c = Cloneable.cast(obj);
					set(key, c.clone());
				}
			}
		}catch(Exception e){}	
	}
	
	@Override
	public Object clone(){ return new Command(this); }
	
	public double[] end(){
		if( x()==null ) return null;
		if( x() instanceof Object[] ){
			Object[] x = getArray(X); 
			Object[] y = getArray(Y); 
			return new double[]{(Double)x[x.length-1], (Double)y[y.length-1]}; 
		}else return new double[]{getReal(X), getReal(Y)};
	}
	
	public String type(){ return getString(COMMAND); }
	
	public Object x(){ try{ return get(X); }catch(Exception e){ return null; } }

	public Object y(){ try{ return get(Y); }catch(Exception e){ return null; } }
	
	public Command[] commands(){
		Object[] c = getArray(COMMANDS);
		Command[] commands = new Command[c.length];
		for( int i=0; i<c.length; i++ ) commands[i] = (Command)c[i];
		return commands; 
	}

	public void translate( double dx, double dy ){
		if( type().equals(COMPOUND) ){
			Command[] commands = (Command[])getArray(COMMANDS);
			for(Command c:commands ) c.translate(dx, dy);
			return;
		}
		if( x()==null ) return;
		if( x() instanceof Object[] ){
			double[] x = getRealArray(X);
			double[] y = getRealArray(Y);
			for( int i=0; i<x.length; i++ ){
				x[i] += dx;
				y[i] += dy;
			}
			set(X,x);
			set(Y,y);
		}else{
			set(X,dx+getReal(X));
			set(Y,dy+getReal(Y));
		}	
	}
	
	protected double angle( double x1, double y1, double x2, double y2 ){
		double a = (x2-x1); 
		double b = (y2-y1);
		double r = Math.sqrt(a*a+b*b);
		if( r>1e-6 ){
			double alpha = Math.acos(a/r);
			if( b<0 ) alpha = 2.0*Math.PI - alpha;
			return alpha;
		}else return 0.0;
	}
	
	protected double[] rotate( double cx, double cy, double px, double py, double angle ){
		double alpha = angle( cx, cy, px, py ) + angle;
		if( alpha>1e-6 ){
			double a = (px-cx); 
			double b = (py-cy);
			double r = Math.sqrt(a*a+b*b);
			return new double[]{ cx + r*Math.cos(alpha), cy + r*Math.sin(alpha) };
		}else return new double[]{px,py};			
	}
	
	public void rotate( double cx, double cy, double angle ){
		if( type().equals(COMPOUND) ){
			Command[] commands = (Command[])getArray(COMMANDS);
			for(Command c:commands ) c.rotate(cx, cy, angle);
			return;
		}
		if( x()==null ) return;
		if( x() instanceof Object[] ){
			double[] x = getRealArray(X);
			double[] y = getRealArray(Y);
			for( int i=0; i<x.length; i++ ){
				double[] p = rotate( cx, cy, x[i], y[i], angle );
				x[i] = p[0];
				y[i] = p[1];
			}	
			set(X,x);
			set(Y,y);			
		}else{
			double[] p = rotate( cx, cy, getReal(X),getReal(Y), angle);
			set(X,(Double)p[0]);
			set(Y,(Double)p[1]);
		}
	}
	
	protected static Command point( String command, double x, double y ){
		Command json = new Command(command);
		json.set(X, (Double)x);
		json.set(Y, (Double)y);
		return json;		
	}
	
	public static Command moveTo( double x, double y ){ return point(MOVETO,x,y); }
	
	public static Command lineTo( double x, double y ){ return point(LINETO,x,y); }

	public static Command poly( String command, double[] x, double[] y ){
		Command json = new Command(command);
		json.set(X, x);
		json.set(Y, y);
		return json;
	}
	
	public static Command quadTo( double cp1x, double cp1y, double x, double y )
	{ return poly(QUADTO, new double[]{cp1x,x}, new double[]{cp1y,y});	}
	
	public static Command curveTo( double cp1x, double cp1y, double cp2x, double cp2y, double x, double y )
	{ return poly(CURVETO, new double[]{cp1x,cp2x,x}, new double[]{cp1y,cp2y,y});	}
	
	public static Command line( double start_x, double start_y, double end_x, double end_y )
	{ return poly( LINE, new double[]{ start_x, end_x }, new double[]{ start_y, end_y }); }
	
	public static Command polygon( double[] x, double[] y ){ return poly(POLYGON, x, y); }

	public static Command polyline( double[] x, double[] y ){ return poly(POLYLINE, x, y); }

	public static Command rect( double x, double y, double width, double height )
	{ return polyline( new double[]{x, x+width, x+width, x, x}, new double[]{y,y,y+height,y+height, y} ); }

	public static Command image( double x, double y, double width, double height, int rot, boolean reflex, String url ){
		Command img = rect( x, y, width, height );
		img.set(COMMAND, IMAGE);
		img.set(IMAGE_ROT, rot);
		img.set(IMAGE_REF, reflex);
		img.set(IMAGE_URL, url);
		return img;
	}

	public static Command text( double x, double y, String str ){
		Command json = point(TEXT, x, y);
		json.set(MESSAGE, str);
		return json;
	} 	
	
	public static Command beginPath(){ return new Command(BEGIN); }
	public static Command closePath(){ return new Command(CLOSE); }
	public static Command stroke(){ return new Command(STROKE); }
	public static Command fill(){ return new Command(FILL); }
	
	protected static ColorInstance ci = new ColorInstance();

	protected static Command colorStyle( String STYLE, Color color ){
		Command c = new Command(STYLE);
		c.set(ColorInstance.COLOR, ci.store(color));
		return c;
	} 
	
	protected static Command linearGradientStyle( String STYLE, double x1, double y1, double x2, double y2, Color startcolor, Color endcolor ){
		Command c = poly(STYLE, new double[]{x1,x2}, new double[]{y1,y2});
		c.set(STARTCOLOR, ci.store(startcolor));
		c.set(ENDCOLOR, ci.store(endcolor));
		return c;
	} 

	protected static Command radialGradientStyle( String STYLE, double x, double y, double r, Color startcolor, Color endcolor ){ 
		Command c = linearGradientStyle(STYLE, x, y, x, y, startcolor, endcolor);
		c.set(X, (Double)x);
		c.set(Y, (Double)y);
		c.set(R, (Double)r);
		return c;
	} 
	
	public static Command strokeStyle( Color color ){ return colorStyle(STROKESTYLE, color); }
	
	public static Command strokeStyle( Color color, int lineWidth ){
		Command c = strokeStyle(color); 
		c.set(LINEWIDTH, lineWidth);
		return c;
	}
	
	public static Command strokeStyle( double x1, double y1, double x2, double y2, Color startcolor, Color endcolor )
	{ return linearGradientStyle(STROKESTYLE, x1, y1, x2, y2, startcolor, endcolor); } 

	public static Command strokeStyle( double x1, double y1, double x2, double y2, Color startcolor, Color endcolor, int lineWidth ){
		Command c = strokeStyle(x1, y1, x2, y2, startcolor, endcolor);
		c.set(LINEWIDTH, lineWidth);
		return c;
	} 
	
	public static Command strokeStyle( double x, double y, double r, Color startcolor, Color endcolor )
	{ return radialGradientStyle(STROKESTYLE, x, y, r, startcolor, endcolor); } 

	public static Command strokeStyle( double x, double y, double r, Color startcolor, Color endcolor, int lineWidth ){
		Command c = strokeStyle(x, y, r, startcolor, endcolor);
		c.set(LINEWIDTH, lineWidth);
		return c;
	} 
	
	public static Command fillStyle( Color color ){ return colorStyle(FILLSTYLE, color); }
	
	public static Command fillStyle( double x1, double y1, double x2, double y2, Color startcolor, Color endcolor )
	{ return linearGradientStyle(FILLSTYLE, x1, y1, x2, y2, startcolor, endcolor); } 

	public static Command fillStyle( double x, double y, double r, Color startcolor, Color endcolor )
	{ return radialGradientStyle(FILLSTYLE, x, y, r, startcolor, endcolor); } 
	
/*	
	public JSON jsonRect( int x, int y, int width, int height ){ return json( RECT, x, y, width, height ); }
	
	public JSON jsonFillRect( int x, int y, int width, int height ){ return json( FILLRECT, x, y, width, height ); }
	
	public JSON jsonOval( int x, int y, int width, int height ){ return json( OVAL, x, y, width, height ); }
	
	public JSON jsonFillOval( int x, int y, int width, int height ){ return json( FILLOVAL, x, y, width, height); }
	
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

*/	
}