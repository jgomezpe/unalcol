package unalcol.instance;

import unalcol.AbstractThing;

public interface UsesInstance extends AbstractThing{
	public default void setInstance( String id, Instance<?> instance ){ set(id, instance); }
	public default Instance<?> getInstance(String id){
		if( valid(id) )  return (Instance<?>)get(id); else return new InstanceWrapper<Object>();
	}
}