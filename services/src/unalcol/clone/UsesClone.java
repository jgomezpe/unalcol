package unalcol.clone;

import unalcol.services.Service;
import unalcol.types.tag.AbstractTags;

public interface UsesClone<T> extends AbstractTags{
	public default void setClone( Clone<T> clone ){ set(Service.USES+Clone.name, clone); }
	public default Clone<T> getClone( T caller ){
		@SuppressWarnings("unchecked")
		Clone<T> clone = (Clone<T>)data(Service.USES+Clone.name);
		if(clone==null) clone = new CloneWrapper<T>();
		clone.setCaller(caller);
		return clone;
	}
}