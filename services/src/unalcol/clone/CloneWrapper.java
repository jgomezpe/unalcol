package unalcol.clone;

import unalcol.services.TaggedCallerNamePair;
import unalcol.services.Service;
import unalcol.types.tag.Tags;

public class CloneWrapper<T> extends Tags implements TaggedCallerNamePair<T>, Clone<T>{
	@SuppressWarnings("unchecked")
	@Override
	public T clone(){
		T caller = caller();
		try{ return (T)Service.run( Clone.name, caller ); }catch(Exception e){}
		return (T)caller;
	}
}