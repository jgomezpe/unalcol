package unalcol.instance;

import unalcol.services.Service;
import unalcol.services.TaggedMicroService;
import unalcol.types.tag.Tags;

public class InstanceWrapper<T> extends Tags implements TaggedMicroService, Instance<T>{
	@SuppressWarnings("unchecked")
	@Override
	public T create(Object... args) {
		try{ return (T)Service.run(Instance.name, caller(), args); }catch(Exception e){}
		return null;
	}	
}