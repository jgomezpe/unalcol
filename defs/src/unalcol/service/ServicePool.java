package unalcol.service;

import java.util.ArrayList;
import java.util.Hashtable;
//
//Unalcol Service structure Pack 1.0 by Jonatan Gomez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/services/
//
/**
*
* ServicePool
* <P> Set of services that have been associated to objects.
*
* <P>
* <A HREF="https://github.com/jgomezpe/unalcol/blob/master/services/src/unalcol/service/ServicePool.java" target="_blank">
* Source code </A> is available.
*
* <h3>License</h3>
*
* Copyright (c) 2014 by Jonatan Gomez-Perdomo. <br>
* All rights reserved. <br>
*
* <p>Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions are met:
* <ul>
* <li> Redistributions of source code must retain the above copyright notice,
* this list of conditions and the following disclaimer.
* <li> Redistributions in binary form must reproduce the above copyright notice,
* this list of conditions and the following disclaimer in the documentation
* and/or other materials provided with the distribution.
* <li> Neither the name of the copyright owners, their employers, nor the
* names of its contributors may be used to endorse or promote products
* derived from this software without specific prior written permission.
* </ul>
* <p>THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
* AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
* IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
* DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNERS OR CONTRIBUTORS BE
* LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
* CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
* SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
* INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
* CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
* ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
* POSSIBILITY OF SUCH DAMAGE.
*
*
*
* @author <A HREF="http://disi.unal.edu.co/profesores/jgomezpe"> Jonatan Gomez-Perdomo </A>
* (E-mail: <A HREF="mailto:jgomezpe@unal.edu.co">jgomezpe@unal.edu.co</A> )
* @version 1.0
*/
public class ServicePool {
    /**
     * Services that have been associated (registered) to an object
     */
	protected Hashtable<Object, OwnedServices> objects = new Hashtable<Object, OwnedServices>();
	
	/**
     * Obtains the service instance that provides the requested <i>type</i> of service to the given class <i>owner</i>.
     * If there is not a service instance associated to the given class <i>owner</i>, it will
     * look in the super classes of the class <i>owner</i>. 
	 * @param owner Class that owns the service
     * @param type Type of service requested
     * @return A service instance that provides the requested <i>type</i> of service to the class <i>owner</i> 
     * if available, <i>null</i> otherwise.
	 */
    public Object get( Class<?> owner, Class<?> type ){
    	Object x = null;
    	if( owner != null ){
	    	OwnedServices instance = objects.get(owner);
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
    
	/**
     * Obtains the service instance that provides the requested <i>type</i> of service to the given object <i>owner</i>.
     * If there is not a service instance associated to the given object <i>owner</i>, it will
     * look if there is a service associated to the class (or super classes) of the object <i>owner</i>, and
     * it will look in the super classes of the class <i>owner</i>.  
	 * @param owner Object that owns the service
     * @param type Type of service requested
     * @return A service instance that provides the requested <i>type</i> of service to the object <i>owner</i> 
     * if available, <i>null</i> otherwise.
	 */
    public Object get( Object owner, Class<?> type ){
    	Object x = null;
    	OwnedServices instance = objects.get(owner);
    	if( instance != null ){
    		x = instance.get(type);
    	}
    	
    	if( x == null ){
    		x = get( owner.getClass(), type );
    	}
    	return x;
    }
    
    /**
     * Obtains the set of service instances providing the requested <i>type</i> of service for 
     * the given class <i>owner</i>. It looks for every registered instance that can be assignable 
     * (according to instances class type) to the requested <i>type</i>, and
     * it will look in the super classes of the class <i>owner</i>.  
     * Services that can be associated to the requested <i>type</i> are added to 
     * the ArrayList <i>list</i>. 
	 * @param owner Class that owns the service
     * @param type Type of service requested.
     * @param list ArrayList where found services are added.
     */
   protected void getAll( Class<?> owner, Class<?> type, ArrayList<Object> list ){
    	if( owner != null ){
	    	OwnedServices instance = objects.get(owner);
	    	if( instance != null ) instance.getAll(type, list);
			getAll( owner.getSuperclass(), type, list );
			Class<?>[] superTypes = owner.getInterfaces();
			for( int i=0; i<superTypes.length; i++ ){
				getAll( superTypes[i], type, list );
			}
    	}	
    }
	
   /**
    * Obtains the set of service instances providing the requested <i>type</i> of service for 
    * the given class <i>owner</i>. It looks for every registered instance that can be assignable 
    * (according to instances class type) to the requested <i>type</i>, and
    * it will look in the super classes of class <i>owner</i>.  
    * @param owner Class that owns the service
    * @param type Type of service requested.
    * @return Array with the found services.
    */
    public Object[] getAll( Class<?> owner, Class<?> type ){
    	ArrayList<Object> list = new ArrayList<Object>();
    	getAll( owner, type, list );
    	return list.toArray();
    }
	
    /**
     * Obtains the set of service instances providing the requested <i>type</i> of service for 
     * the given object <i>owner</i>. It looks for every registered instance that can be assignable 
     * (according to instances class type) to the requested <i>type</i>, and
     * it will look in the class and super classes of class <i>owner</i>.  
     * @param owner Class that owns the service
     * @param type Type of service requested.
     * @return Array with the found services.
     */
    public Object[] getAll( Object owner, Class<?> type ){
    	ArrayList<Object> list = new ArrayList<Object>();
    	OwnedServices instance = objects.get(owner);
    	if( instance != null ){
    		instance.getAll(type, list);
    	}    	
  		getAll( owner.getClass(), type, list );
    	return list.toArray();
    }
	
    /**
     * Sets the service <i>instance</i> that will be associated to the service <i>type</i> to the object <i>owner</i>. 
     * @param owner Object that will own the service
     * @param type Type of service that will by provided
     * @param instance Service that will provide the service.
     * @return If the service <i>instance</i> was associated to the service <i>type</i> for the object <i>owner</i>.
     */
    public boolean set( Object owner, Class<?> type, Object instance ){
    	OwnedServices obj = objects.get(owner);
    	if( obj == null ){
    		obj = new OwnedServices();
    		objects.put(owner, obj);
    	}
    	return obj.set(type, instance);
    }
    
    /**
     * Sets the service <i>instance</i> to the object <i>owner</i>. 
     * @param owner Object that will own the service.
     * @param instance Service that will provide the service.
     * @return If the service <i>instance</i> was associated to the object <i>owner</i>.
     */
    public boolean set( Object owner, Object instance ){
    	return set(owner, instance.getClass(), instance);
    }
    
    /**
     * Removes the object <i>owner</i> from the service pool (removes all services associated
     * to the object <i>owner</i>)
     * @param owner Object that will be removed from the service pool
     * @return If the object <i>owner</i> was removed from the service pool.
     */
    public boolean remove( Object owner ){
    	return objects.remove(owner)!=null;
    }
}