package unalcol.services;

import unalcol.types.collection.keymap.HTKeyMap;

public class MicroServiceSet<T> extends MicroService<T>{
	protected HTKeyMap<AbstractMicroService<T>, Object> services = new HTKeyMap<AbstractMicroService<T>,Object>();
	
	@Override
	public String[] provides(){	
		if( services.isEmpty() ) return new String[0];
		return services.keys().iterator().next().provides(); 
	}
	
	@Override
	public Object run(Object... args) throws Exception {
		T caller = caller();
		String name = name();
		HTKeyMap<AbstractMicroService<T>, Object> map = new HTKeyMap<AbstractMicroService<T>,Object>();
		for(AbstractMicroService<T> service:services.keys()){
			service.setCaller(caller);
			service.setName(name);
			map.set(service, service.run(args));
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public void register(AbstractMicroService<?> service){
		services.set((AbstractMicroService<T>)service,0); 
	}

	public void remove(AbstractMicroService<T> service){
		services.remove(service);
	}
}