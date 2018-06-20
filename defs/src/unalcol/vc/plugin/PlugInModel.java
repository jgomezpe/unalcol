package unalcol.vc.plugin;

import unalcol.vc.Model;
import unalcol.vc.Side;
import unalcol.xml.XMLElement;

public interface PlugInModel extends Model{
	default void init(XMLElement doc){
		for( Side s:sides() ) if( s instanceof PlugInSide ) ((PlugInSide)s).init(doc);
	}
}