package unalcol.reflect.tag;

import java.util.Hashtable;
import java.util.Set;

/**
*
* TaggedObject
* <p>A tagged object (Object with a set of tags).</p>
*
* <P>
* <A HREF="https://github.com/jgomezpe/unalcol/blob/master/src/unalcol/reflect/tag/TaggedObject.java">
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
public class TaggedObject<T>{
    /**
     * Object that is being tagged.
     */
    protected T object;
	
    /**
     * Set of tags associated to the object.
     */
    protected Hashtable<String, Object> info = new Hashtable<>();
	
    /**
     * Creates a TaggedObject from the given <i>object</i>.
     * @param object Object to be tagged.
     */
    public TaggedObject( T object ){ this.object = object; }
 
    /**
     * Associates every tag in <i>info</i> to the object. 
     * @param info Set of tags to be associated to the object.
     */
    public void cloneTags( Hashtable<String, Object> info ){
	this.info.clear();
	Set<String> keys = info.keySet();
	for(String k:keys){
	    Object obj = info.get(k);
	    this.info.put(k, obj);
	}
    }
    
    /**
     * Associates every tag associated to object <i>x</i> to this object.
     * @param x Object from which the tags are taken.
     */
    public void cloneTags( TaggedObject<T> x ){
	cloneTags(x.info);
    }
    
    /**
     * Associates just the TaggedMethods in the set of tags <i>info</i> to the object.
     * @param info Set of tags. 
     */
    public void cloneTaggedMethods( Hashtable<String, Object> info ){
	this.info.clear();
	Set<String> keys = info.keySet();
	for(String k:keys){
	    Object obj = info.get(k);
	    if( obj instanceof TaggedMethod ){
		this.info.put(k, obj);
	    }
	}
    }
	
    /**
     * Associates just the TaggedMethods associated to object <i>x</i> to this object.
     * @param x Object from which the TaggedMethods are taken.
     */
    public void cloneTaggedMethods( TaggedObject<T> x ){
	cloneTaggedMethods(x.info);
    }
    
    /**
     * Removes all tag associated to this object that is not a TaggedMethod.
     */
    public void removeNonTaggedMethods(){
	Set<String> keys = info.keySet();
	for(String k:keys){
	    Object obj = data(k);
	    if( !(obj instanceof TaggedMethod) ){
		info.remove(k);	
	    }
	}		
	
    }
   
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
	
    /**
     * Gets the actual tag data. If the the tag is a TaggedMethod it will return the TaggedMethod.
     * @param key Tag.
     * @return The actual tag data.
     */
    public Object data( String key ){
	return info.get(key);
    }
	
    /**
     * Sets the data associated to a tag.
     * @param key Tag.
     * @param value Value associated to the given tag.
     */
    public void set( String key, Object value ){
	info.put(key, value);
    }	
}