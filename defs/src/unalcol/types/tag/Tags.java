package unalcol.types.tag;

import unalcol.types.collection.keymap.HTKeyMap;
import unalcol.types.collection.keymap.KeyMap;

public class Tags implements AbstractTags{
    /**
     * Set of tags associated to the object.
     */
    protected KeyMap<String, Object> info = new HTKeyMap<String,Object>();
	
    /**
     * Gets the actual tag data. If the the tag is a TaggedMethod it will return the TaggedMethod.
     * @param key Tag.
     * @return The actual tag data.
     */
    public Object data( String key ){ return info.get(key); }
	
    /**
     * Sets the data associated to a tag.
     * @param key Tag.
     * @param value Value associated to the given tag.
     */
    public void set( String key, Object value ){ info.put(key, value); }	
}