package unalcol.types.object.dynamic;

import unalcol.types.collection.Collection;

//
//Unalcol definitions Pack 1.0 by Jonatan Gomez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/core/
//
/**
*
* Thing  
* <p>The unalcol abstract generic object.</p>
*
* <P>
* <A HREF="https://github.com/jgomezpe/unalcol/tree/master/core/src/unalcol/Thing.java" target="_blank">
* Source code </A> is available.
*
* <h3>License</h3>
*
* Copyright (c) 2017 by Jonatan Gomez-Perdomo. <br>
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
* (E-mail: <A HREF="mailto:jgomezpe@unal.edu.co">jgomezpe at unal.edu.co</A> )
* @version 1.0
*/
public interface DynObj{
	/**
	 * Determines if the object has the given attribute
	 * @param attribute Attribute to be considered
	 * @return <i>true</i> If the object has associated the given attribute, <i>false</i> otherwise. 
	 */
	public boolean valid( String attribute );

	/**
	 * Gets the value associated to the given attribute
	 * @param attribute Attribute to be retrieved
	 * @return Value associated to the given attribute 
	 */
	public Object get( String attribute );

	/**
	 * Sets the value associated to a given attribute
	 * @param attribute Attribute to be assigned
	 * @param value Value to be associated to the attribute
	 * @return <i>true</i> if the value was associated to the given attribute </i>false</i> otherwise
	 */
	public boolean set( String attribute, Object value );
	
	/**
	 * removes the given attribute from the object
	 * @param attribute Attribute to be removed
	 * @return <i>true</i> if the attribute was removed from the given attribute </i>false</i> otherwise
	 */
	public boolean remove( String attribute );
	
	/**
	 * Removes all attribute in the object
	 */
	public void clear();
	
	/**
	 * Gets a collection of the object attributes
	 * @return A collection of the object attributes
	 */
	public Collection<String> attributes();	
}