package unalcol.vc;

import unalcol.types.object.basic.BasicThing;

public class DefaultComponent extends BasicThing implements Component{

	protected Side side;
	
	public DefaultComponent(String id){ super(id); }

	@Override
	public Side side(){ return side; }

	@Override
	public void setSide(Side side){ this.side = side; }
}