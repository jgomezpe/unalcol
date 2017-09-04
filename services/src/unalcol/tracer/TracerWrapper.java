package unalcol.tracer;

import unalcol.services.Service;
import unalcol.services.TaggedCallerNamePair;
import unalcol.types.tag.Tags;

public class TracerWrapper<T>  extends Tags implements TaggedCallerNamePair<T>, Tracer<T> {
 	@Override
	public void add(Object... obj) {
		try{ Service.run(Tracer.name, caller(), obj); }catch(Exception e){}
	}

	@Override
	public Object get() {
		try{ Service.run(Tracer.get,caller()); }catch(Exception e){}
		return null;
	}

	@Override
	public void clean() {
		try{ Service.run(Tracer.clean,caller()); }catch(Exception e){}
	}

	@Override
	public void close() {
		try{ Service.run(Tracer.close,caller()); }catch(Exception e){}
	}

	@Override
	public boolean tracing() {
		try{ return (boolean)Service.run(Tracer.tracing,caller()); }catch(Exception e){}
		return false;
	}

	@Override
	public boolean start() {
		try{ return (boolean)Service.run(Tracer.start,caller()); }catch(Exception e){}
		return false;
	}

	@Override
	public boolean stop() {
		try{ return (boolean)Service.run(Tracer.stop,caller()); }catch(Exception e){}
		return false;
	}
}