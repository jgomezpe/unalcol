package unalcol.instance;

import java.lang.reflect.Constructor;

import unalcol.services.Service;

public interface Instanteable {
	/**
	 * Generates an instance belonging to the class <i>type</i> according to the parameters (it does not support VarArgs constructors).
	 * @param type Class of instances that will be generated.
	 * @param args Arguments for creating an instance.
	 * @return An instance belonging to the class <i>type</i> using the parameters.
	 */
	default Object create( Object... args ){ return service().create(this,args); }
	
	default Instance service(){ return Instanteable.service(this); }
	
	public static Instance service( Object caller ){
		Instance obj;
		try{ obj = (Instance)Service.provider(Instance.class, caller); }
		catch(Exception e){
			obj = new Instance(){
	    		/**
	    		 * Generates an instance belonging to the class <i>type</i> according to the parameters (it does not support VarArgs constructors).
	    		 * @param type Class of instances that will be generated.
	    		 * @param args Arguments for creating an instance.
	    		 * @return An instance belonging to the class <i>type</i> using the parameters.
	    		 */
	    		public Object create( Class<?> type, Object... args ){
	    			Class<?>[] argsTypes = new Class<?>[args.length];
	    			for( int i=0; i<argsTypes.length; i++) argsTypes[i] = args[i].getClass();
	    			Constructor<?>[] c = type.getConstructors();
	    			Constructor<?> cc = null;
	    			// @TODO: Check var ags constructors.. 
	    			for( int i=0; i<c.length; i++ ){
	    				Class<?>[] parms = c[i].getParameterTypes();
	    				if( parms.length==args.length ){
	    					boolean assignable = true;
	    					boolean exact = true;
	    					for( int k=0; k<args.length && assignable; k++ ){
	    						assignable = parms[k].isAssignableFrom(argsTypes[k]);
	    						exact = parms[k] == argsTypes[k];
	    					}
	    					if( exact ) try{ return c[i].newInstance(args); }catch(Exception e){}
	    					if( assignable ) cc = c[i];
	    				}
	    			}
	    			if( cc != null ) try{ return cc.newInstance(args); }catch(Exception e){} 
	    			return null;
	    		}

	    		/**
	    		 * Generates an instance belonging to the class <i>type</i> according to the parameters (it does not support VarArgs constructors).
	    		 * @param obj Object that defined the class of instances that will be generated.
	    		 * @param args Arguments for creating an instance.
	    		 * @return An instance belonging to the class <i>obj.getClass()</i> using the parameters.
	    		 */
				@Override
				public Object create(Object obj, Object... args){ if(obj instanceof Class<?>) return create( (Class<?>)obj, args ); else return create(obj.getClass(), args); }

				@Override
				public String toString(){ return "Instance"; }
			};
			Service.register(obj, Object.class);
		}
		return obj;
	}
	
	public static Instanteable cast( Object obj ){
		if( obj instanceof Instanteable ) return (Instanteable)obj;
		return new Instanteable(){
			public Instance service(){ return Instanteable.service(obj); } 
			public Object create( Object... args ){ return service().create(obj,args); }
		};
	}
}