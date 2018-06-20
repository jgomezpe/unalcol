package unalcol.vc;

import unalcol.types.collection.array.Array;
import unalcol.types.object.Thing;

public interface Component extends Thing{
	public Side side();
	public void setSide( Side side );	
		
	default Array<Component> children(){ return null; }
}