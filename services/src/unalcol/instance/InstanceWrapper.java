package unalcol.instance;

import unalcol.services.Service;
import unalcol.services.TaggedCallerNamePair;
import unalcol.types.tag.Tags;

public class InstanceWrapper<T> extends Tags implements TaggedCallerNamePair<Class<T>>, Instance<T>{
	@SuppressWarnings("unchecked")
	@Override
	public T create(Object... args) {
		try{ return (T)Service.run(Instance.name, caller(), args); }catch(Exception e){}
		return null;
	}	
}