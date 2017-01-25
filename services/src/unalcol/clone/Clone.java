package unalcol.clone;

import unalcol.service.ServiceCore;

//
//Unalcol Service structure Pack 1.0 by Jonatan Gomez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/services/
//
/**
*
* Clone
* <P>The Clone Service of the unalcol library. Every clone service should be inherited from it.
*
* <P>
* <A HREF="https://github.com/jgomezpe/unalcol/blob/master/services/src/unalcol/clone/Clone.java" target="_blank">
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
* @param <T> Type of objects to be cloned.
*/
public abstract class Clone<T> {
	/**
     * Creates a clone of a given object
     * @param toClone Object to be cloned
     * @return A clone of the object
     */
	public abstract T clone(T toClone);
    
    /**
     * Obtains the Clone service associated to the given object.
     * @param owner Object that owns the clone service.
     * @return A Clone service for the given object (if available), <i>null</i> otherwise.
     */
	public static Clone<?> get(Object owner){
		if( ServiceCore.get(Object.class, Clone.class) == null )
			set(Object.class, new CloneWrapper());
		return (Clone<?>)ServiceCore.get(owner, Clone.class);
	}
    
    /**
     * Sets the clone <i>service</i> to the object <i>owner</i>. 
     * @param owner Object that will own the service.
     * @param service Instance that will provide the clone service to the object <i>owner</i>.
     * @return If the clone <i>service</i> was associated to the object <i>owner</i>.
     */
	public static boolean set( Object owner, Clone<?> service ){
		return ServiceCore.set(owner, Clone.class, service);
	}
    
    	/**
     * Creates a clone of a given object
     * @param obj Object to be cloned
     * @return A clone of the object, if a cloning service is available for the given object, <i>null</i> otherwise
     */
	@SuppressWarnings("unchecked")
	public static Object create( Object obj ){
		return ((Clone<Object>)get(obj)).clone(obj);
	}  
}