package unalcol.services;

import unalcol.types.collection.vector.Vector;

public class MicroServiceSet<T> extends MicroService<T>{
	protected Vector<AbstractMicroService<T>> services = new Vector<AbstractMicroService<T>>();
	
	@Override
	public String[] provides(){	return services.get(0).provides();	}
	
	@Override
	public Object run(Object... args) throws Exception {
		T caller = caller();
		String name = name();
		Vector<Object> objs = new Vector<Object>();
		for(AbstractMicroService<T> s:services){
			s.setCaller(caller);
			s.setName(name);
			objs.add(s.run(args));
		}
		return objs;
	}

	public void add(AbstractMicroService<T> service){ services.add(service); }

	public void remove(AbstractMicroService<T> service){
		int i=0; 
		while( i<services.size() && services.get(i)!=service) i++;
		if( i<services.size() ) services.remove(i);
	}
}