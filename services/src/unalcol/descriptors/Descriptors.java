package unalcol.descriptors;

import unalcol.service.*;

//
//Unalcol Service structure Pack 1.0 by Jonatan Gomez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/services/
//
/**
*
* Descriptors
* <p>Obtains a set of real value descriptors for a given object.</p>
*
* <P>
* <A HREF="https://github.com/jgomezpe/unalcol/blob/master/services/src/unalcol/descriptors/Descriptors.java" target="_blank">
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
* @param <T> Type of objects to be described.
*/
public abstract class Descriptors<T> {
	/**
     * Obtains the descriptors of (an array of double values describing) an object.
     * @param obj Object to be described using double values (features).
     * @return An array of double values used for describing the object.
     */
	public abstract double[] descriptors(T obj);
    
	/**
     * Obtains the descriptor service associated to a given object.
     * @param owner Object to be described. 
     * @return Descriptor service associated to a given object.
     */
	public static Descriptors<?> get(Object owner){
		if( ServiceCore.get(Object.class, Descriptors.class) == null )
			set(Object.class, new DescriptorsWrapper());
		return (Descriptors<?>)ServiceCore.get(owner, Descriptors.class);
	}
    
	/**
     * Associates to a given object <i>owner</i> its descriptor <i>service</i>.
     * @param owner Object that will be associated to the descriptors <i>service</i>.
     * @param service Descriptors service used by the object <i>owner</i>.
     * @return If the <i>owner</i> was associated to the descriptors <i>service</i>.
     */
	public static boolean set( Object owner, Descriptors<?> service ){
		return ServiceCore.set(owner, Descriptors.class, service);
	}
    
	/**
     * Obtains the descriptors of an object.
     * @param obj Object to be described
     * @return An array of double values used for describing the object.
     */
	@SuppressWarnings("unchecked")
	public static double[] obtain(Object obj) {
		return ((Descriptors<Object>)get(obj)).descriptors(obj);
	}
}