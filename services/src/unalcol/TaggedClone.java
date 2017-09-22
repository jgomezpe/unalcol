package unalcol;

import unalcol.clone.Clone;
import unalcol.clone.CloneWrapper;
import unalcol.instance.Instance;
import unalcol.instance.InstanceWrapper;
import unalcol.services.MicroService;
import unalcol.types.collection.keymap.HTKeyMap;
import unalcol.types.collection.keymap.KeyValue;
import unalcol.Tagged;

//
//Unalcol Service structure Pack 1.0 by Jonatan Gomez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/services/
//
/**
*
* TaggedObjectClone
* <p>Copies a TaggedObject (object and TaggedMethods).</p>
* <P>
* <A HREF="https://github.com/jgomezpe/unalcol/blob/master/services/src/unalcol/reflect/tag/TaggedObjectClone.java" target="_blank">
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
public class TaggedClone<T> extends MicroService<Tagged<T>> implements Clone<Tagged<T>> {
	/**
	 * If the object that is tagged should be copied or a shallow clone is enough
	 */
	protected boolean cloneObject;

	protected static final String cloner = "cloner";
	protected static final String instancer = "instancer";
	/**
	 * Tags that will not be copied
	 */	
	protected HTKeyMap<Object,Object> noClonedTags = new HTKeyMap<Object,Object>();

	/**
	 * Creates a clone method for TaggedObjects. Clones tags, methods, and object if defined 
	 * @param cloneObject If the object that is tagged should be copied or a shallow clone is enough
	 */
	public TaggedClone( boolean cloneObject ){
		this.cloneObject = cloneObject;
		setMicroService(cloner, new CloneWrapper<T>());
	}

	public void nonCloneTag( Object tag ){
		noClonedTags.set(tag, tag);
	}

	public void cloneTag( Object tag ){
		noClonedTags.remove(tag);
	}

	public MicroService<?> wrap(String id){
		if( id.equals(cloner) )	return new CloneWrapper<T>();
		if( id.equals(instancer) ) return new InstanceWrapper<Tagged<T>>();
		return null;
	}

	/**
	 * Creates a clone of the TaggedObject (including just the TaggedMethods).
	 * @param obj TaggedObject to be  non-strictly copied. 
	 * @return A copy of the TaggedObject (including or the tags or just the TaggedMethods).
	 */
	@SuppressWarnings("unchecked")
	public Tagged<T> clone(){
		Tagged<T> obj = caller();
		T tObj = obj.unwrap();
		if( cloneObject ){
			Clone<T> cloner = (Clone<T>)getMicroService(TaggedClone.cloner);
			cloner.setCaller(tObj);
			tObj = cloner.clone();
		}
		Instance<Tagged<T>> instance = (Instance<Tagged<T>>)getMicroService(instancer);
		instance.setCaller((Class<Tagged<T>>)obj.getClass());
		Tagged<T> nObj = instance.create(tObj);
		for(KeyValue<Object, Object> kv:obj.pairs()) if(noClonedTags.get(kv.key())==null) nObj.add(kv);
		return nObj;
	}
}