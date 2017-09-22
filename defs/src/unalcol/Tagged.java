package unalcol;


import unalcol.types.collection.keymap.KeyMap;
import unalcol.types.collection.keymap.KeyValue;

//
//Unalcol Service structure Pack 1.0 by Jonatan Gomez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/services/
//
/**
*
* TaggedObject
* <p>A tagged object (Object with a set of tags).</p>
*
* <P>
* <A HREF="https://github.com/jgomezpe/unalcol/blob/master/services/src/unalcol/reflect/tag/TaggedObject.java" target="_blank">
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
public class Tagged<T> extends Thing{
	/**
	 * Object that is being tagged.
	 */
	protected T object;

	/**
	 * Creates a TaggedObject from the given <i>object</i>.
	 * @param object Object to be tagged.
	 */
	public Tagged( T object ){ this.object = object; }
 
	/**
	 * Creates a TaggedObject from the given <i>object</i>.
	 * @param object Object to be tagged.
	 * @param tags Set of tags from which the tags (or just the TaggedMethods) will be copied
	 * @param copyAllTags Defines if all tags are copied (<i>true</i>) or just the TaggedMethods (<i>false</i>)
	 */
	public Tagged( T object, KeyMap<Object, Object> tags ){
		this.object = object;
		for(KeyValue<Object,Object> k:tags.pairs()) add(k);
	}
 
	/**
	 * Sets the object that is being tagged. Removes all the non TaggedMethods associated to this object.
	 * @param object Object that is being tagged.
	 */
	public void wrap( T object ){
		clear();
		this.object = object;
	}
	
	/**
	 * Gets the object that is being tagged.
	 * @return tagged object.
	 */
	public T unwrap(){ return object; }
	
	/**
	 * Obtains the set of objects that are actually tagged.
	 * @param obj Set of TaggedObject 's.
	 * @return Actual objects (without tags).
	 */
	public T[] unwrap( @SuppressWarnings("unchecked") Tagged<T>... obj ){ 
		@SuppressWarnings("unchecked")
		T[] t_obj = (T[])new Object[obj.length];
		for( int i=0; i<obj.length; i++ ) t_obj[i] = obj[i].unwrap();
		return t_obj;
	}  
	
	/**
	 * Creates a TaggedObject array from an array of (possibly non tagged) objects.
	 * @param obj Array of (possibly non tagged) objects to be Tagged.
	 * @return A TaggedObject array from an array of (possibly non tagged) objects.
	 */
	public Tagged<T>[] wrap( @SuppressWarnings("unchecked") T... obj ){
		@SuppressWarnings("unchecked")
		Tagged<T>[] t_obj = new Tagged[obj.length];
		for( int i=0; i<obj.length; i++ ) t_obj[i] = new Tagged<T>(obj[i]);
		return t_obj;
	}
	
	/**
	 * Creates an array of Tagged version of each object in <i>obj</i> using the TagMethods stored by <i>tags</i>. 
	 * @param tags Tags to be used by the tagged object.
	 * @param obj Objects to be Tagged.
	 * @return A Tagged version of each object in <i>obj</i> using the TagMethods stored by <i>tags</i>.
	 */
	public Tagged<T>[] wrap( AbstractThing tags, @SuppressWarnings("unchecked") T... obj ){
		@SuppressWarnings("unchecked")
		Tagged<T>[] n_pop = new Tagged[obj.length];
		for( int i=0;i<obj.length; i++ ) n_pop[i] = new Tagged<T>( obj[i], tags);
		return n_pop;
	}	    
}