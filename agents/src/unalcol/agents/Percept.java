package unalcol.agents;

import unalcol.types.collection.keymap.KeyMap;
import unalcol.types.collection.keymap.HashMap;
/**
 * <p>Title: Percept</p>
 *
 * <p>Description: Abstract definition of a perception</p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gomez
 * @version 1.0
 */
public class Percept {
	protected KeyMap<String,Object> table = new HashMap<String,Object>();
	
	public Percept() {}
	
	public Object getAttribute( String code ){ return table.get(code); }

	public void setAttribute( String key, Object value ){ table.set(key, value); }
}