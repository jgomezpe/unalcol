package unalcol.js.vc;

import unalcol.collection.KeyMap;
import unalcol.vc.BackEnd;
import unalcol.vc.Component;

public interface JSModelLoader {
	KeyMap<String, Component>  frontend( String url );
	BackEnd backend( String url );
}
