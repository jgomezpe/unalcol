package unalcol.js.gui;

import unalcol.collection.Grow;
import unalcol.collection.Vector;
import unalcol.gui.paint.Canvas;
import unalcol.gui.paint.CanvasRender;
import unalcol.gui.paint.Drawable;
import unalcol.js.vc.view.JSSimpleView;

public class JSCanvasRender extends JSSimpleView implements CanvasRender{
	protected Vector<Drawable> objects = new Vector<Drawable>();
	protected JSCanvas canvas;

	public JSCanvasRender(String id){
		super(id);
		canvas = new JSCanvas(this); 
	}
	
	@Override
	public Canvas getCanvas(){ return canvas; }

	@Override
	public Grow<Drawable> objects(){ return objects; }

	@Override
	public void setCanvas(Canvas canvas){}
	
	@Override
	public void render( Object obj ){
		add(obj);
		render();
	}	
}
