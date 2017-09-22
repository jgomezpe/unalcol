package unalcol.instance;

import unalcol.AbstractThing;

public interface UsesInstance extends AbstractThing{
	public default void setInstance( String id, Instance<?> instance ){ set(id, instance); }
	public default Instance<?> getInstance(String id){
		Instance<?> instance = (Instance<?>)get(id);
		if(instance==null) instance = new InstanceWrapper<Object>();
		return instance;
	}
}