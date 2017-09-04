package unalcol.instance;

import unalcol.services.Service;
import unalcol.types.tag.AbstractTags;

public interface UsesInstance<T> extends AbstractTags{
	public default void setInstance( Instance<T> instance ){ set(Service.USES+Instance.name, instance); }
	public default Instance<T> getInstance(Class<T> caller){
		@SuppressWarnings("unchecked")
		Instance<T> instance = (Instance<T>)data(Service.USES+Instance.name);
		if(instance==null) instance = new InstanceWrapper<T>();
		instance.setCaller(caller);
		return instance;
	}
}