package unalcol.js.gui;

import unalcol.gui.paint.Canvas;
import unalcol.gui.paint.Color;
import unalcol.gui.paint.ColorInstance;
import unalcol.json.JSON;

public class JSCanvas extends Canvas{
	protected Color color=null;
	protected JSCanvasRender render;
	
	public JSCanvas( JSCanvasRender render ){ this.render = render; }
	
	@Override
	public void drawArc(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawFillArc(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void drawImage(int arg0, int arg1, int arg2, int arg3, int arg4, boolean arg5, String arg6) {
		// TODO Auto-generated method stub
		
	}

	protected Object[] cast(Object... args){
		return args;
	}

	protected Object[] cast(int[] x){
		Object[] args = new Object[x.length];
		for( int i=0; i<x.length; i++ ) args[i] = x[i];
		return args;
	}
	

	@Override
	public void drawLine( int start_x, int start_y, int end_x, int end_y ){
		JSON json = new JSON();
		json.set("canvas", render.jsId());
		json.set(COMMAND, LINE);
		json.set(X, cast( start_x, end_x ));
		json.set(Y, cast( start_y, end_y ));
		if(color!=null) json.set(ColorInstance.COLOR, cinstance.store(color));
		render.execute("drawJSON", json.toString());		
	}

	@Override
	public void drawPolygon(int[] x, int[] y) {
		JSON json = new JSON();
		json.set("canvas", render.jsId());
		json.set(COMMAND, POLYGON);
		json.set(X, cast(x));
		json.set(Y, cast(y));
		if(color!=null) json.set(ColorInstance.COLOR, cinstance.store(color));
		render.execute("drawJSON", json.toString());		
	}

	@Override
	public void drawString(int arg0, int arg1, String arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Color setColor(Color color) {
		Color c = this.color;
		this.color = color;
		return c;
	}
	
	@Override
	public void drawJSON( JSON json ){
		render.execute("drawJSON", json.toString());		
	}
}