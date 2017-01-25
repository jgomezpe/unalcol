package unalcol.reflect.tag;

import java.util.Hashtable;
import java.util.Set;

/**
*
* TaggedObjectManager 
* <p>A set of methods used for going from an object to its TaggedObject version an backwards.</p>
*
* <P>
* <A HREF="https://github.com/jgomezpe/unalcol/blob/master/services/src/unalcol/reflect/tag/TaggedObjectManager.java" target="_blank">
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
public interface TaggedObjectManager<T> {	
    /**
     * Obtains the actual object (without tags) that has being tagged.
     * @param obj  TaggedObject.
     * @return Actual object (without tags) that has being tagged.
     */
    public default T unwrap( TaggedObject<T> obj ){
	return obj.object();
    }
	
    /**
     * Creates an array of <i>n</i> objects (according to the interface parameter).
     * @param n Size of the array to be created.
     * @return An array of <i>n</i> objects (according to the interface parameter).
     */
    @SuppressWarnings("unchecked")
    public default T[] obj_array( int n ){
	return (T[])new Object[n];
    }
    
    /**
     * Obtains the set of objects that are actually tagged.
     * @param obj Set of TaggedObject 's.
     * @return Actual objects (without tags).
     */
    public default T[] unwrap( @SuppressWarnings("unchecked") TaggedObject<T>... obj ){
	T[] t_obj = obj_array(obj.length);
	for( int i=0; i<obj.length; i++ ) t_obj[i] = unwrap(obj[i]);
	return t_obj;
    }  
	
    /**
     * Creates a Tagged object from an (possibly non tagged) object.
     * @param obj Object to be tagged.
     * @return A TaggedObject version of the given object.
     */
    public default TaggedObject<T> wrap( T obj ){
	return new TaggedObject<T>(obj);
    }

    /**
     * Creates a Tagged version of <i>obj</i> using the TagMethods stored by <i>tags</i>. 
     * @param obj Object to be Tagged.
     * @param tags Tags to be used by the tagged object
     * @return A Tagged version of <i>obj</i> using the TagMethods stored by <i>tags</i>.
     */
    public default TaggedObject<T> wrap( T obj, Hashtable<String, Object> tags ){
    	TaggedObject<T> tobj = new TaggedObject<T>(obj);
		Set<String> keys = tags.keySet();
		for(String k:keys){
			Object tagObj = tags.get(k);
		    if( tagObj instanceof TaggedMethod ){
		    	tobj.info.put(k, tagObj);
		    }	
		}		
    	return tobj;
    }

    /**
     * Creates an array of TaggedObjects.
     * @param n Size of the array.
     * @return An array of TaggedObject of size <i>n</i>.
     */
    @SuppressWarnings("unchecked")
    public default TaggedObject<T>[] tagged_array( int n ){
	return (TaggedObject<T>[])new TaggedObject[n];
    }

    /**
     * Creates a TaggedObject array from an array of (possibly non tagged) objects.
     * @param obj Array of (possibly non tagged) objects to be Tagged.
     * @return A TaggedObject array from an array of (possibly non tagged) objects.
     */
    public default TaggedObject<T>[] wrap( @SuppressWarnings("unchecked") T... obj ){
	TaggedObject<T>[] t_obj = tagged_array(obj.length);
	for( int i=0; i<obj.length; i++ ) t_obj[i] = wrap(obj[i]);
	return t_obj;
    }
	
    /**
     * Creates an array of Tagged version of each object in <i>obj</i> using the TagMethods stored by <i>tags</i>. 
     * @param tags Tags to be used by the tagged object.
     * @param obj Objects to be Tagged.
     * @return A Tagged version of each object in <i>obj</i> using the TagMethods stored by <i>tags</i>.
     */
    public default TaggedObject<T>[] wrap( Hashtable<String, Object> tags, @SuppressWarnings("unchecked") T... obj ){
	TaggedObject<T>[] n_pop = tagged_array(obj.length);
    	int i=0;
    	for( T x:obj ){
    	    n_pop[i] = wrap( x, tags);
    	    i++;
    	}
    	return n_pop;
    }	
}