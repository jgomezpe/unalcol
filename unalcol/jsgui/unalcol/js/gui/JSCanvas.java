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
	
	@Override
	public void drawLine( int start_x, int start_y, int end_x, int end_y ){
		System.out.println("[JSCanvas.drawLine]");
		JSON json = new JSON();
		System.out.println("[JSCanvas.drawLine]1");
		json.set("canvas", render.jsId());
		System.out.println("[JSCanvas.drawLine]2");
		json.set(COMMAND, LINE);
		System.out.println("[JSCanvas.drawLine]3");
		json.set(X, cast( start_x, end_x ));
		System.out.println("[JSCanvas.drawLine]4");
		json.set(Y, cast( start_y, end_y ));
		System.out.println("[JSCanvas.drawLine]5");
		if(color!=null) json.set(ColorInstance.COLOR, cinstance.store(color));
		System.out.println("[JSCanvas.drawLine]"+json);
		render.execute("drawJSON", json.toString());		
	}

	@Override
	public void drawPolygon(int[] arg0, int[] arg1) {
		// TODO Auto-generated method stub
		
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
}
