package unalcol.vc;

import unalcol.object.Thing;

public class DefaultComponent extends Thing implements Component{

	protected Side side;
	
	public DefaultComponent(String id){ super(id); }

	@Override
	public Side side(){ return side; }

	@Override
	public void setSide(Side side){ this.side = side; }
}