package unalcol.instance;

import unalcol.types.tag.AbstractTags;

public interface UsesInstance<T> extends AbstractTags{
	public default void setInstance( Instance<T> instance ){ set(Instance.name, instance); }
	public default Instance<T> getInstance(T caller){
		@SuppressWarnings("unchecked")
		Instance<T> instance = (Instance<T>)data(Instance.name);
		if(instance==null) instance = new InstanceWrapper<T>();
		return instance;
	}
}