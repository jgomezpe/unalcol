package unalcol.descriptors;

import java.lang.reflect.Array;
import unalcol.services.Service;

public interface Describable {
	/**
	 * Obtains the descriptors of (an array of double values describing) an object.
	 * @param obj Object to be described using double values (features).
	 * @return An array of double values used for describing the object.
	 */
	default double[] descriptors(){ return service().descriptors(this); }
	
	default Descriptors service(){ return Describable.service(this); }
	
	public static Descriptors service( Object caller ){
		Descriptors obj;
		try{ obj = (Descriptors)Service.provider(Descriptors.class, caller); }
		catch(Exception e){
			obj = new Descriptors() {
	    		/**
	    		 * Creates a clone (non shallow copy) of a Java primitive types array 
	    		 * @param obj Array of a primitive type values to be cloned 
	    		 * @return A clone of the array (non shallow copy)
	    		 */
	    		protected double[] array( double[] obj ){ return obj.clone(); }
	    		
	    		/**
	    		 * Creates a clone (non shallow copy) of a Java primitive types array 
	    		 * @param obj Array of a primitive type values to be cloned 
	    		 * @return A clone of the array (non shallow copy)
	    		 */
	    		protected double[] array( int[] obj ){
	    			double[] x = new double[obj.length];
	    			for( int i=0; i<obj.length; i++ ) x[i] = obj[i];
	    			return x;
	    		}
	    		
	    		/**
	    		 * Creates a clone (non shallow copy) of a Java primitive types array 
	    		 * @param obj Array of a primitive type values to be cloned 
	    		 * @return A clone of the array (non shallow copy)
	    		 */
	    		protected double[] array( long[] obj ){
	    			double[] x = new double[obj.length];
	    			for( int i=0; i<obj.length; i++ ) x[i] = obj[i];
	    			return x;
	    		}
	    		
	    		/**
	    		 * Creates a clone (non shallow copy) of a Java primitive types array 
	    		 * @param obj Array of a primitive type values to be cloned 
	    		 * @return A clone of the array (non shallow copy)
	    		 */
	    		protected double[] array( byte[] obj ){
	    			double[] x = new double[obj.length];
	    			for( int i=0; i<obj.length; i++ ) x[i] = obj[i];
	    			return x;
	    		}
	    		
	    		/**
	    		 * Creates a clone (non shallow copy) of a Java primitive types array 
	    		 * @param obj Array of a primitive type values to be cloned 
	    		 * @return A clone of the array (non shallow copy)
	    		 */
	    		protected double[] array( short[] obj ){
	    			double[] x = new double[obj.length];
	    			for( int i=0; i<obj.length; i++ ) x[i] = obj[i];
	    			return x;
	    		}
	    		
	    		/**
	    		 * Creates a clone (non shallow copy) of a Java primitive types array 
	    		 * @param obj Array of a primitive type values to be cloned 
	    		 * @return A clone of the array (non shallow copy)
	    		 */
	    		protected double[] array( char[] obj ){
	    			double[] x = new double[obj.length];
	    			for( int i=0; i<obj.length; i++ ) x[i] = obj[i];
	    			return x;
	    		}
	    		
	    		/**
	    		 * Creates a clone (non shallow copy) of a Java primitive types array 
	    		 * @param obj Array of a primitive type values to be cloned 
	    		 * @return A clone of the array (non shallow copy)
	    		 */
	    		protected double[] array( boolean[] obj ){
	    			double[] x = new double[obj.length];
	    			for( int i=0; i<obj.length; i++ ) x[i] = obj[i]?1:0;
	    			return x;
	    		}
	    		
	    		/**
	    		 * Creates a clone (non shallow copy) of a Java primitive types array 
	    		 * @param obj Array of a primitive type values to be cloned 
	    		 * @return A clone of the array (non shallow copy)
	    		 */
	    		protected double[] array( float[] obj ){
	    			double[] x = new double[obj.length];
	    			for( int i=0; i<obj.length; i++ ) x[i] = obj[i];
	    			return x;
	    		}
	    		
	    		
	    		/**
	    		 * Creates a clone (non shallow copy) of a Java primitive types array 
	    		 * @param obj Array of a primitive type values to be cloned 
	    		 * @return A clone of the array (non shallow copy)
	    		 */
	    		protected double[] primitiveArray( Object obj ){
	    			if( obj instanceof int[] ) return array((int[])obj);
	    			if( obj instanceof double[] ) return array((double[])obj);
	    			if( obj instanceof char[] ) return array((char[])obj);
	    			if( obj instanceof byte[] ) return array((byte[])obj); 
	    			if( obj instanceof long[] ) return array((long[])obj);
	    			if( obj instanceof short[] ) return array((short[])obj);
	    			if( obj instanceof float[] ) return array((float[])obj); 
	    			return array((boolean[])obj);
	    		}

	    		/**
	    		 * Creates a clone of an array. 
	    		 * It uses the clone service defined for each object in the array.
	    		 * @param obj Array to be cloned 
	    		 * @return A clone of the array
	    		 */
				protected double[] describeArray( Object obj ){
	    			Class<?> cl = obj.getClass().getComponentType();
	    			if( cl.isPrimitive() ) return primitiveArray(obj);
	    			int n = Array.getLength(obj);
	    			double[][] m = new double[n][];
	    			int k = 0;
	    			for( int i=0; i<n; i++ ){
	    				m[i] = new double[0];
	    				Object the_obj = Array.get(obj, i); 
	    				if(the_obj!=null){
							Describable d = Describable.cast(the_obj); 
	    					if( d != null ) m[i] = d.descriptors(); 
	    				}
	    				k += m[i].length;
	    			}
	    			double[] d = new double[k];
	    			k=0;
	    			for( int i=0; i<m.length; i++ ){
	    				System.arraycopy(m[i], 0, d, k, m[i].length);
	    				k += m[i].length;
	    			}
	    			return d;	
	    		}
	    		
	    		@Override
				public double[] descriptors(Object obj){
	    			System.out.println("[Describable]"+obj);
					if( obj instanceof Double || obj instanceof Integer ||  obj instanceof Character || obj instanceof Long ) return new double[]{(Double)obj};
					if( obj.getClass().isArray() ) return describeArray(obj);
					return null;
				}

				@Override
				public String toString(){ return "Descriptors"; }
			};
			Service.register(obj, Object.class);
    	}
    	return obj;
	}
	
	public static Describable cast( Object obj ){
		if( obj instanceof Describable ) return (Describable)obj;
		return new Describable(){
			public Descriptors service(){ return Describable.service(obj); } 
			public double[] descriptors(){ return service().descriptors(obj); }
		};
	}
}