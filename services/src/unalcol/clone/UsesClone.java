package unalcol.clone;

import unalcol.types.tag.AbstractTags;

public interface UsesClone<T> extends AbstractTags{
	public default void setClone( Clone<T> clone ){ set(Clone.name, clone); }
	public default Clone<T> getClone(){
		@SuppressWarnings("unchecked")
		Clone<T> clone = (Clone<T>)data(Clone.name);
		if(clone==null) clone = new CloneWrapper<T>();
		return clone;
	}
}