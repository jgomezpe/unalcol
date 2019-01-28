package unalcol.vc;

import unalcol.types.collection.Collection;
import unalcol.types.collection.keymap.HashMap;

public class KeyMapModel implements Model{
	protected HashMap<String, Side> sides = new HashMap<String,Side>();

	@Override
	public Side side(String id){ return sides.get(id); }
	
	@Override
	public Collection<Side> sides(){ return sides; }

	@Override
	public void clear(){ sides.clear(); }
	
	@Override
	public void register( Side side ){
		sides.set(side.id(), side);
		if( side.model() != this ) side.setModel(this);
	}
}