package unalcol.clone;

import unalcol.services.MicroService;
import unalcol.services.Service;

public class CloneWrapper<T> extends MicroService<T> implements Clone<T>{
	@SuppressWarnings("unchecked")
	@Override
	public T clone(){
		T caller = caller();
		try{ return (T)Service.run( Clone.name, caller ); }catch(Exception e){}
		return (T)caller;
	}
}