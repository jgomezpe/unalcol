package unalcol.vc;

import unalcol.types.collection.array.Array;
import unalcol.types.object.Named;

public interface Component extends Named{
	public Side side();
	public void setSide( Side side );	
		
	default Array<Component> children(){ return null; }
}