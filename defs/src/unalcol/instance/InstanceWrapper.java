package unalcol.instance;

import unalcol.services.Service;
import unalcol.services.MicroService;

public class InstanceWrapper<T>  extends MicroService<Class<T>> implements Instance<T>{
	@SuppressWarnings("unchecked")
	@Override
	public T create(Object... args) {
		try{ return (T)Service.run(Instance.name, caller(), args); }catch(Exception e){}
		return null;
	}	
}