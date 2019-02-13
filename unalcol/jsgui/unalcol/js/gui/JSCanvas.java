package unalcol.js.gui;

import unalcol.collection.Vector;
import unalcol.gui.paint.Canvas;
import unalcol.gui.paint.Color;
import unalcol.gui.paint.ColorInstance;
import unalcol.js.vc.JSView;
import unalcol.json.JSON;

public class JSCanvas extends Canvas{
	protected JSView view;
	protected Color color;
	
	public void setView( JSView view ){ this.view = view; }

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

	protected Vector<Object> cast(Object... args){
		Vector<Object> v = new Vector<Object>();
		for( Object o:args) v.add(o);
		return v;
	}
	
	@Override
	public void drawLine( int start_x, int start_y, int end_x, int end_y ){
		JSON json = new JSON();
		json.set(COMMAND, LINE);
		json.set(X, cast( start_x, end_x ));
		json.set(Y, cast( start_y, end_y ));
		json.set(ColorInstance.COLOR, cinstance.store(color));
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
