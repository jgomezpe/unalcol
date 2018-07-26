package unalcol.gui.paint;

import unalcol.types.collection.vector.Vector;
import unalcol.vc.View;

public abstract class Render implements View{
	protected Canvas canvas = new Canvas( this );
	protected Vector<RenderInstruction> instruction = new Vector<RenderInstruction>();
	
	public void init(){ instruction.clear(); }
	
	public void add( RenderInstruction code ){ instruction.add(code); }
	
	public abstract void render();
	
	public void render( Drawable obj ){
		init();
		obj.draw(canvas);
	}
}