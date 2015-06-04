package unalcol.reflect;

import java.util.Hashtable;

public class UnalcolHierarchy {

	protected Hashtable<Object, UnalcolObject> objects = new Hashtable<Object, UnalcolObject>();
	
	public UnalcolHierarchy(){}
	
    public Object get( Class<?> owner, Class<?> type ){
    	Object x = null;
		System.out.println("CL Entering here 0" + owner);
    	if( owner != null ){
    		System.out.println("CL Entering here 1");
	    	UnalcolObject instance = objects.get(owner);
	    	if( instance != null ){
	    		x = instance.get(type);
	    	}
    		if( x == null ){
        		System.out.println("CL Entering here 2");
    			x = get( owner.getSuperclass(), type );
    			if( x==null ){
    				Class<?>[] superTypes = owner.getInterfaces();
    				for( int i=0; i<superTypes.length && x==null; i++ ){
    					x = get( superTypes[i], type );
    				}
    			}
    		}
    	}	
    	return x;
    }
    
    public Object get( Object owner, Class<?> type ){
    	Object x = null;
		System.out.println("Entering here 0");
    	UnalcolObject instance = objects.get(owner);
    	if( instance != null ){
    		System.out.println("Entering here 1");
    		x = instance.get(type);
    		if( x == null ){
        		System.out.println("Entering here 2");
    			x = get( owner.getClass(), type );
    		}
    	}
    	return x;
    }
	
    public boolean set( Object owner, Class<?> type, Object instance ){
    	UnalcolObject obj = objects.get(owner);
    	if( obj == null ){
    		obj = new UnalcolObject();
    		objects.put(owner, obj);
    	}
    	return obj.set(type, instance);
    }    
}
