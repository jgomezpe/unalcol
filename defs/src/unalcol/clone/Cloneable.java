package unalcol.clone;

import java.lang.reflect.Array;

import unalcol.services.Service;

public interface Cloneable {
	default Object clone(){ return service().clone(this); }
	
	default Clone service(){ return Cloneable.service(this); }
	
    public static Clone service( Object caller ){
    	Clone obj;
    	try { obj = (Clone)Service.provider(Clone.class,caller); }
    	catch (Exception e){
			obj = new Clone() {
	    		/**
	    		 * Creates a clone (non shallow copy) of a Java primitive types array 
	    		 * @param obj Array of a primitive type values to be cloned 
	    		 * @return A clone of the array (non shallow copy)
	    		 */
	    		protected Object clonePrimitiveArray( Object obj ){
	    			if( obj instanceof int[] ) return ((int[])obj).clone();
	    			if( obj instanceof double[] ) return ((double[])obj).clone();
	    			if( obj instanceof char[] ) return ((char[])obj).clone();
	    			if( obj instanceof byte[] ) return ((byte[])obj).clone(); 
	    			if( obj instanceof long[] ) return ((long[])obj).clone();
	    			if( obj instanceof short[] ) return ((short[])obj).clone();
	    			if( obj instanceof float[] ) return ((float[])obj).clone(); 
	    			return ((boolean[])obj).clone();
	    		}

	    		/**
	    		 * Creates a clone of an array. 
	    		 * It uses the clone service defined for each object in the array.
	    		 * @param obj Array to be cloned 
	    		 * @return A clone of the array
	    		 */
				protected Object cloneArray( Object obj ){
	    			Class<?> cl = obj.getClass().getComponentType();
	    			if( cl.isPrimitive() ) return clonePrimitiveArray(obj);
	    			int n = Array.getLength(obj);
	    			Object clone = Array.newInstance(cl, n);
	    			for( int i=0; i<n; i++ ){
	    				Object the_obj = Array.get(obj, i); 
	    				if(the_obj!=null){
							Cloneable c = Cloneable.cast(the_obj); 
	    					if( c != null ) Array.set(clone, i, c.clone()); else Array.set(clone, i, the_obj); 
	    				}else Array.set(clone, i, null);
	    			}
	    			return clone;	
	    		}
	    		
	    		@Override
				public Object clone(Object obj){
					if( obj instanceof String || obj instanceof Double || obj instanceof Integer ||  obj instanceof Character || obj instanceof Long ) return obj;
			        
					if( obj.getClass().isArray() ) return cloneArray(obj);
					
					try{ return obj.getClass().getMethod("clone").invoke(obj); }catch(Exception e){}
					return obj;
				}

				@Override
				public String toString(){ return "Clone"; }
			};
			Service.register(obj, Object.class);
    	}
    	return obj;
    }
    
    public static Cloneable cast( Object obj ){
    	if( obj instanceof Cloneable ) return (Cloneable)obj;
    	return new Cloneable(){
    		public Clone service(){ return Cloneable.service(obj); }
    		public Object clone(){ return service().clone(obj); }
    	};
    }
}