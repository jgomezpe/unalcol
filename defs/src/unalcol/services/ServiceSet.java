package unalcol.services;

import unalcol.types.collection.vector.Vector;
import unalcol.types.tag.Tags;

public class ServiceSet extends Tags implements MicroService, TaggedMicroService{
	protected Vector<MicroService> services = new Vector<MicroService>();
	
	@Override
	public String[] provides(){	return services.get(0).provides();	}
	
	@Override
	public String name(){ return services.get(0).name(); }
	
	@Override
	public Object run(Object... args) throws Exception {
		Object caller = caller();
		String name = name();
		Vector<Object> objs = new Vector<Object>();
		for(MicroService s:services){
			s.setCaller(caller);
			s.setName(name);
			objs.add(s.run(args));
		}
		return objs;
	}
	
	public void add(MicroService service){ services.add(service); }


	public void remove(ServiceProvider service){
		int i=0; 
		while( i<services.size() && services.get(i)!=service) i++;
		if( i<services.size() ) services.remove(i);
	}
}