package unalcol.vc;

import unalcol.object.Thing;

public abstract class DefaultSide extends Thing implements Side{
	public DefaultSide(String id) { super(id); }

	protected Model model;
	
	@Override
	public void setModel(Model model){ this.model = model; }

	@Override
	public Model model(){ return model; }
}