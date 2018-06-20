package unalcol.gui.paint;

import unalcol.types.collection.vector.Vector;
import unalcol.vc.View;

public abstract class Render implements View{
	protected Vector<RenderInstruction> instruction = new Vector<RenderInstruction>();
	
	public void init(){ instruction.clear(); }
	
	public void add( RenderInstruction code ){ instruction.add(code); }
	
	public abstract void render();
}