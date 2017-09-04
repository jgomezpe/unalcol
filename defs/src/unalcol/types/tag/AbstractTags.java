package unalcol.types.tag;

public interface AbstractTags{	
    /**
     * Gets the actual tag data. If the the tag is a TaggedMethod it will return the TaggedMethod.
     * @param key Tag.
     * @return The actual tag data.
     */
    public Object data( String key );
	
    /**
     * Sets the data associated to a tag.
     * @param key Tag.
     * @param value Value associated to the given tag.
     */
    public void set( String key, Object value );
}