package unalcol.tracer;

import unalcol.collection.keymap.HashMap;
import unalcol.services.ProvidersSet;
import unalcol.services.Service;

public interface Traceable {
	default boolean tracing(){
    	Tracer t = service();
    	if( t != null ) return t.tracing();
    	return false;
	}

	/**
	 * Starts the tracing of objects process
	 * @return <i>true</i> if the Tracer was tracing objects, <i>false</i>otherwise.
	 */
	default void start_trace(){
    	Tracer t = service();
    	if( t != null ) t.start();
	}

	/**
	 * Stops the tracing of objects process
	 * @return <i>true</i> if the Tracer was tracing objects, <i>false</i>otherwise.
	 */
	default void stop_trace(){
    	Tracer t = service();
    	if( t != null ) t.stop();
	}

	/**
	 * Adds an object sent by an object to the tracer
	 * @param obj Traced information to be added
	 */
	default void trace(Object... obj){
		Tracer t = service();
    	if( t != null ) t.add(this,obj);
	}

	/**
	 * Returns the traced object
	 * @return An object representing the traced information
	 */
	default Object traced(){
		Tracer t = service();
    	if( t != null ) return t.get();
    	return null;
	}

	/**
	 * Cleans the traced information
	 */
	default void clean_trace(){
		Tracer t = service();
    	if( t != null ) t.clean(); 
    }

	/**
	 * Closes the tracer
	 */
	default void close_trace(){
		Tracer t = service();
    	if( t != null ) t.close(); 
	}
	
	default Tracer service(){ return Traceable.service(this); }

	
    public static Tracer service( Object caller ){
    	try{ 
    		ProvidersSet set = Service.providers(Tracer.class,caller);
    		Tracer tr = new Tracer() {
    			public boolean tracing(){
    		    	for( String s:set.options() )	if( ((Tracer)set.get(s)).tracing() ) return true;
    		    	return false;
    			}
    			/**
    			 * Starts the tracing of objects process
    			 * @return <i>true</i> if the Tracer was tracing objects, <i>false</i>otherwise.
    			 */
    			public void start(){ super.start(); for( String s:set.options() ) ((Tracer)set.get(s)).start(); }

    			/**
    			 * Stops the tracing of objects process
    			 * @return <i>true</i> if the Tracer was tracing objects, <i>false</i>otherwise.
    			 */
    			public void stop(){ super.stop(); for( String s:set.options() ) ((Tracer)set.get(s)).stop(); }

    			/**
    			 * Adds an object sent by an object to the tracer
    			 * @param obj Traced information to be added
    			 */
    			public void add(Object caller, Object... obj){ for( String s:set.options() ) ((Tracer)set.get(s)).add(caller,obj); }

    			/**
    			 * Returns the traced object
    			 * @return An object representing the traced information
    			 */
    			public Object get(){
    				HashMap<String,Object> v = new HashMap<String,Object>();
    				for( String s:set.options() ) v.set(s,((Tracer)set.get(s)).get());
    				return v;
    			}

    			/**
    			 * Cleans the traced information
    			 */
    			public void clean(){ for( String s:set.options() ) ((Tracer)set.get(s)).clean(); }
    			
    			/**
    			 * Closes the tracer
    			 */
    			public void close(){ for( String s:set.options() ) ((Tracer)set.get(s)).close(); }
			};
    		return tr;
    	}catch(Exception e){ return null; } 
    }
    
    public static Traceable cast(Object obj){
    	if( obj instanceof Traceable ) return (Traceable)obj;
    	return new Traceable() {
    		public Tracer service(){ return Traceable.service(obj); }
		};
    }
}