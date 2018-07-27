package unalcol.gui.paint;

import unalcol.gui.render.Render;
import unalcol.types.collection.vector.Vector;

public abstract class CanvasRender implements Render{
	protected Canvas canvas = new Canvas( this );
	protected Vector<RenderInstruction> instruction = new Vector<RenderInstruction>();
	
	public void init(){ instruction.clear(); }
	
	public void add( RenderInstruction code ){ instruction.add(code); }
	
	public abstract void render();
	
	public void render( Drawable obj ){
		init();
		obj.draw(canvas);
	}
	
	public void render( Object obj ){ render((Drawable)obj); }
}