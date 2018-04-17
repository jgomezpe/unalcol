package unalcol.reflect.xml;

import unalcol.types.collection.SearchCollection;

public interface XMLElement {
	public static final String TAG="tag";
	public SearchCollection<String,Object> attributes();
	public SearchCollection<Integer,XMLElement> children();
	default String getTag(){ return (String)getAttribute(TAG); }
	public void setAttribute( String key, Object obj );
	default void setAttributeRecursive( String key, Object obj ){
		setAttribute(key, obj);
		for( XMLElement e:children() ) e.setAttributeRecursive(key, obj);
	}
	default Object getAttribute( String key ){ return attributes().get(key); }
	default XMLElement child(int index){ return children().get(index); }
}