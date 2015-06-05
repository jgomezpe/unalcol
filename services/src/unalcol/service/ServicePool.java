package unalcol.service;

import java.util.ArrayList;
import java.util.Hashtable;

public class ServicePool {

	protected Hashtable<Object, ServiceOwner> objects = new Hashtable<Object, ServiceOwner>();
	
	public ServicePool(){}
	
    public Object get( Class<?> owner, Class<?> type ){
    	Object x = null;
    	if( owner != null ){
	    	ServiceOwner instance = objects.get(owner);
	    	if( instance != null ){
	    		x = instance.get(type);
	    	}
    		if( x == null ){
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
    	ServiceOwner instance = objects.get(owner);
    	if( instance != null ){
    		x = instance.get(type);
    	}
    	
    	if( x == null ){
    		x = get( owner.getClass(), type );
    	}
    	return x;
    }
    
    protected void getAll( Class<?> owner, Class<?> type, ArrayList<Object> list ){
    	if( owner != null ){
	    	ServiceOwner instance = objects.get(owner);
	    	if( instance != null ) instance.getAll(type, list);
			getAll( owner.getSuperclass(), type, list );
			Class<?>[] superTypes = owner.getInterfaces();
			for( int i=0; i<superTypes.length; i++ ){
				getAll( superTypes[i], type, list );
			}
    	}	
    }
	
    public Object[] getAll( Class<?> owner, Class<?> type ){
    	ArrayList<Object> list = new ArrayList<Object>();
    	getAll( owner, type, list );
    	return list.toArray();
    }
	
    public Object[] getAll( Object owner, Class<?> type ){
    	ArrayList<Object> list = new ArrayList<Object>();
    	ServiceOwner instance = objects.get(owner);
    	if( instance != null ){
    		instance.getAll(type, list);
    	}    	
  		getAll( owner.getClass(), type, list );
    	return list.toArray();
    }
	
    public boolean set( Object owner, Class<?> type, Object instance ){
    	ServiceOwner obj = objects.get(owner);
    	if( obj == null ){
    		obj = new ServiceOwner();
    		objects.put(owner, obj);
    	}
    	return obj.set(type, instance);
    }
    
    public boolean set( Object owner, Object instance ){
    	return set(owner, instance.getClass(), instance);
    }
    
    public boolean remove( Object owner ){
    	return objects.remove(owner)!=null;
    }
}
