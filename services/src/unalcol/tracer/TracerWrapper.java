package unalcol.tracer;

import unalcol.services.Service;

public class TracerWrapper extends Tracer {

	@Override
	public void add(Object owner, Object... obj) {
		try{ Service.run(Tracer.name, owner, obj); }catch(Exception e){}
	}

	@Override
	public Object get() {
		try{ Service.run(Tracer.get); }catch(Exception e){}
		return null;
	}

	@Override
	public void clean() {
		try{}catch(Exception e){}
	}

	@Override
	public void close() {
		try{}catch(Exception e){}
	}
}