package unalcol.vc;

import unalcol.collection.Array;
import unalcol.object.Named;

public interface Component extends Named{
	public Side side();
	public void setSide( Side side );	
		
	default Array<Component> children(){ return null; }
}