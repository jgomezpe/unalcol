package unalcol.search.variation;

import unalcol.Tagged;
import unalcol.search.space.Space;
import unalcol.services.AbstractMicroService;

public interface Variation<T> extends AbstractMicroService<T>{
	public default int arity(){ return 0; };

	public default int range_arity(){ return 0; };
	
	@SuppressWarnings("unchecked")
	public default T[] apply(T... pop){ return get(apply(set(pop))); }
    
    @SuppressWarnings("unchecked")
	public default T[] apply(Space<T> space, T... pop){ return space.repair(apply(pop)); }
    
	@SuppressWarnings("unchecked")
	public default Tagged<T>[] apply(Tagged<T>... pop){ return set(apply(get(pop))); }    
    
	@SuppressWarnings("unchecked")
	public default Tagged<T>[] apply(Space<T> space, Tagged<T>... pop){ return space.repair(apply(pop)); } 
	
	@SuppressWarnings("unchecked")
	public default T[] get(Tagged<T>... pop){
		int n = pop.length;
		T[] b_pop = (T[])(new Object[n]);
		for( int i=0; i<n; i++) b_pop[i] = pop[i].unwrap();
		return b_pop;
	}
	
	@SuppressWarnings("unchecked")
	public default Tagged<T>[] set(T... pop){
		int n = pop.length;
		Tagged<T>[] s_pop = (Tagged<T>[])(new Tagged[n]);
		for( int i=0; i<n; i++) s_pop[i] = new Tagged<T>(pop[i]);
		return s_pop;
	}
	
	// The MicroService methods
	public static String name="variation";
	
	public static final String[] methods = new String[]{name};
	
	@Override
	public default String[] provides(){ return methods; }

	@SuppressWarnings("unchecked")
	@Override
	public default Object run( Object... args ) throws Exception{
		String service = name();
		if(service.equals(name)){
			Space<T> space =  (args[0] instanceof Space)?(Space<T>)args[0]:null;
			int index = (space!=null)?1:0;
			if(args[index] instanceof Tagged ){
				Tagged<T>[] objs = new Tagged[args.length-index];
				for( int i=0;i<objs.length; i++ ) objs[i] = (Tagged<T>)args[i+index];
				if(space!=null) return apply(space,objs); else return apply(objs);
			}else{
				T[] objs = (T[])new Object[args.length-index];
				for( int i=0;i<objs.length; i++ ) objs[i] = (T)args[i+index];
				if(space!=null) return apply(space,objs); else return apply(objs);				
			}
		}
		throw new Exception("Undefined service "+service);		
	}
	
}