package unalcol.types.object.dynamic;

import unalcol.types.collection.keymap.KeyMap;
import unalcol.types.object.Thing;

public class KeyMapDynThing extends KeyMapDynObj implements Thing{
	public KeyMapDynThing(){ super(); }

	public KeyMapDynThing(KeyMap<String, Object> keymap){ super(keymap); }

	@Override
	public String id(){ return (String)get(Thing.ID); }

	@Override
	public void setId(String id) { set(Thing.ID, id); }
}