package unalcol.clone;

import unalcol.services.TaggedMicroService;
import unalcol.services.Service;
import unalcol.types.tag.Tags;

public class CloneWrapper<T> extends Tags implements TaggedMicroService, Clone<T>{
	@SuppressWarnings("unchecked")
	@Override
	public T clone(){
		Object caller = caller();
		try{ return (T)Service.run( Clone.name, caller ); }catch(Exception e){}
		return (T)caller;
	}
}