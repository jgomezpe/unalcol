package unalcol.types.tag;

import java.util.ArrayList;
import java.util.Iterator;

import unalcol.types.collection.keymap.KeyMap;

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
public class TaggedObject<T> extends Tags{
    /**
     * Object that is being tagged.
     */
    protected T object;
	
    /**
     * Creates a TaggedObject from the given <i>object</i>.
     * @param object Object to be tagged.
     */
    public TaggedObject( T object ){ this.object = object; }
 
    /**
     * Creates a TaggedObject from the given <i>object</i>.
     * @param object Object to be tagged.
     * @param tags Set of tags from which the tags (or just the TaggedMethods) will be copied
     * @param copyAllTags Defines if all tags are copied (<i>true</i>) or just the TaggedMethods (<i>false</i>)
     */
    public TaggedObject( T object, KeyMap<String, Object> tags, boolean copyAllTags ){
    	this.object = object;
    	cloneTags( tags, copyAllTags);
    }
 
    /**
     * Copies the tags (only the TaggedMethods if required) from a given set of tags
     * @param tags Set of tags from which the tags (or just the TaggedMethods) will be copied
     * @param copyAllTags Defines if all tags are copied (<i>true</i>) or just the TaggedMethods (<i>false</i>)
     */
    protected void cloneTags( KeyMap<String, Object> tags, boolean copyAllTags ){
    	info.clear();
		Iterator<String> keys = tags.keys();
		while(keys.hasNext()){
			String k=keys.next();
			Object tagObj = tags.get(k);
		    if( copyAllTags || tagObj instanceof TaggedMethod ){
		    	info.put(k, tagObj);
		    }	
		}		
    }
    
    /**
     * Removes all tag associated to this object that is not a TaggedMethod.
     */
    public void removeNonTaggedMethods(){
		Iterator<String> keys = info.keys();
		ArrayList<String> dKeys = new ArrayList<String>();
		while(keys.hasNext()){
			String k = keys.next();
		    Object obj = data(k);
		    if( !(obj instanceof TaggedMethod) ){ dKeys.add(k); }
		}			
		for(String k:dKeys)	info.remove(k);
    }
   
    /**
     * Gets the set of tags
     * @return Tags associated to the object
     */
    public KeyMap<String, Object> tags(){ return info; }
   
    /**
     * Sets the object that is being tagged. Removes all the non TaggedMethods associated to this object.
     * @param object Object that is being tagged.
     */
    public void set( T object ){
    	removeNonTaggedMethods();
    	this.object = object;
    }
	
    /**
     * Gets the object that is being tagged.
     * @return tagged object.
     */
    public T object(){ return object; }
	
    /**
     * Gets the information associated to a given tag. If the tag is a TaggedMethod it will be computed on this object.
     * @param key Tag.
     * @return The information associated to a given tag. If the tag is a TaggedMethod it will be computed on this object.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Object info( String key ){
	Object obj = info.get(key);
	if( obj instanceof TaggedMethod ){
	    try{
		return ((TaggedMethod)obj).apply(this);
	    }catch(Exception e){
		e.printStackTrace();
	    }
	    return null;
	} 
	return obj;
    }	
}