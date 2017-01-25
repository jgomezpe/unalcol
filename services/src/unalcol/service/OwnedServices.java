package unalcol.service;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
//
//Unalcol Service structure Pack 1.0 by Jonatan Gomez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/services/
//
/**
*
* OwnedServices
* <P> Set of services (concrete instances of services) that are specifically associated (owned) by an object (possible owned by a class)
*
* <P><A HREF="https://github.com/jgomezpe/unalcol/blob/master/services/src/unalcol/service/OwnedServices.java" target="_blank">
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

public class OwnedServices {
    /**
     * Services owned by the object associated to this class by the ServicePool  
     * Here, the service instance is associated with a service class (by default, to the service class)
     */
    protected Hashtable<Class<?>, Object> instances = new Hashtable<Class<?>,Object>();

    /**
     * Instance currently associated to the service by the owner
     */
    protected Hashtable<Class<?>, Object> current = new Hashtable<Class<?>,Object>();

    /**
     * Associates a service to an object for answering request of a type of services  
     * The class of the service <i>instance</i> must be assignable to service class <i>type</i> 
     * @param type Type of the service that <i>instance</i> will provide
     * @param instance Service that will provide the <i>type</i> of service
     * @return A boolean value indicating if the service <i>instance</i> 
     * was set for providing the class of service <i>type</i> 
     */
    public boolean set( Class<?> type,  Object instance ){
		instances.put(type, instance);
    	if( type!= null && instance != null && type.isAssignableFrom(instance.getClass()) ){
    		current.put(type, instance);
    		return true;
    	}
    	return false;
    }
    
    /**
     * Associates a service to an object for answering request of the service type  
     * @param instance Service that will provide the service
     * @return A boolean value indicating if the service <i>instance</i> 
     * was set for providing the class of service (the <i>instance</i> class) 
     */
    public boolean set( Object instance ){
    	return set( instance.getClass(), instance );
    }
    
    /**
     * Obtains the service instance that provides the requested <i>type</i> of service.
     * If there is not a service instance associated to the requested <i>type</i>, it will
     * look for a registered instance that can be assignable (according to instances class type) to the requested <i>type</i>. 
     * @param type Type of service requested
     * @return A service instance that provides the requested <i>type</i> of service if available, <i>null</i> otherwise
     */
    public Object get( Class<?> type ){
    	Object obj = current.get(type);
    	if( obj == null ){
    		obj = instances.get(type);
    	}
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
 
    /**
     * Obtains the set of service instances providing the requested <i>type</i> of service.
     * It looks for every registered instance that can be assignable 
     * (according to instances class type) to the requested <i>type</i>.
     * Services that can be associated to the requested <i>type</i> are added to 
     * the ArrayList <i>list</i>. 
     * @param type Type of service requested.
     * @param list ArrayList where found services are added.
     */
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