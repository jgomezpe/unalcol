package unalcol.gui.paint;

import unalcol.types.collection.vector.Vector;
import unalcol.vc.frontend.SimpleView;

public abstract class Render extends SimpleView{
	protected Vector<RenderInstruction> instruction = new Vector<RenderInstruction>();
	
	public void init(){ instruction.clear(); }
	
	public void add( RenderInstruction code ){ instruction.add(code); }
	
	public abstract void render();
}