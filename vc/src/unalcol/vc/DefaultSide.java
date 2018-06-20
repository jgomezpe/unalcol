package unalcol.vc;

import unalcol.types.object.basic.BasicThing;

public abstract class DefaultSide extends BasicThing implements Side{
	public DefaultSide(String id) { super(id); }

	protected Model model;
	
	@Override
	public void setModel(Model model){ this.model = model; }

	@Override
	public Model model(){ return model; }
}