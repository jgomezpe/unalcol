package unalcol.service;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

public class ServiceOwner {
   
    /**
     * Instances of this attribute class that are maintained by the owner
     */
    protected Hashtable<Class<?>, Object> instances = new Hashtable<Class<?>,Object>();
    
    public ServiceOwner(){}
    
    public boolean set( Class<?> type,  Object instance ){
    	if( type!= null && instance != null && type.isAssignableFrom(instance.getClass()) ){
    		instances.put(type, instance);
    		return true;
    	}
    	return false;
    }
    
    public boolean set( Object instance ){
    	return set( instance.getClass(), instance );
    }
    
    public Object get( Class<?> type ){
    	Object obj = instances.get(type);
    	if( obj == null ){
    		Enumeration<Object> x = instances.elements();
    		while( x.hasMoreElements() ){
    			Object y = x.nextElement();
    			if( type.isAssignableFrom(y.getClass()) ){
    				if( obj == null || y.getClass().isAssignableFrom(obj.getClass()) ){
    					obj = y;
    				}	
    			}
    		}
    	}
    	return obj;
    }  
    
    protected void getAll( Class<?> type, ArrayList<Object> list ){
    	Object obj = instances.get(type);
    	if( obj != null ) list.add(obj);
		Enumeration<Object> x = instances.elements();
		while( x.hasMoreElements() ){
			Object y = x.nextElement();
			if( type.isAssignableFrom(y.getClass()) )	list.add(y);	
		}    	
    }
}
