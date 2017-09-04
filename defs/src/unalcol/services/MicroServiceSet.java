package unalcol.services;

import unalcol.types.collection.vector.Vector;
import unalcol.types.tag.Tags;

public class MicroServiceSet<T> extends Tags implements MicroService<T>, TaggedCallerNamePair<T>{
	protected Vector<MicroService<T>> services = new Vector<MicroService<T>>();
	
	@Override
	public String[] provides(){	return services.get(0).provides();	}
	
	@Override
	public Object run(Object... args) throws Exception {
		T caller = caller();
		String name = name();
		Vector<Object> objs = new Vector<Object>();
		for(MicroService<T> s:services){
			s.setCaller(caller);
			s.setName(name);
			objs.add(s.run(args));
		}
		return objs;
	}
	
	public void add(MicroService<T> service){ services.add(service); }

	public void remove(ServiceProvider service){
		int i=0; 
		while( i<services.size() && services.get(i)!=service) i++;
		if( i<services.size() ) services.remove(i);
	}
}