package unalcol.java;
import java.io.*;

//
//Unalcol Service structure Pack 1.0 by Jonatan Gomez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/services/
//
/**
*
* JavaOS
* <P>Collection of values which are determined according to the Operative System
* where the Java Machine is running.</p>
*
* <P>
* <A HREF="https://github.com/jgomezpe/unalcol/blob/master/services/src/unalcol/reflect/util/JavaOS.java" target="_blank">
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
public class JavaOS {
	/**
	 * Character used for directories hierarchy
	 */
	protected static char FILE_SEPARATOR = System.getProperty("file.separator").charAt(0);

	/**
	 * Character used for defining class paths for the java compiler and java virtual machine.
	 */
	protected static char PATH_SEPARATOR = System.getProperty("path.separator").charAt(0);

	/**
	 * Character used for enclosing class paths, class sources, etc for the java compiler and java virtual machine.
	 */
	protected static char CLOSING_CHARACTER = (System.getProperty("os.name").indexOf("Windows") != -1)?'"':' ';

	/**
	 * Creates an string with the appropriated operating system file separator
	 * @param path String standar java path name
	 * @return Path with the appropriated file separator character (according to the operating system)
	 */
	public static String systemPath(String path) { return path.replace('/', FILE_SEPARATOR).replace('\\', FILE_SEPARATOR); }

	/**
	 * Creates the absolute version of a path (including last / symbol)
	 * @param path String to be converted to absolute path
	 * @return String with the absolute path
	 */
	public static String absolutePath( String path ){ return (new File(systemPath(path)).getAbsolutePath()) + "/"; }

	/**
	 * Character used for directories hierarchy.
	 * @return Character used for directories hierarchy.
	 */
	public static char fileSeparator(){ return FILE_SEPARATOR; }

	/**
	 * Character used for defining class paths for the java compiler and java virtual machine.
	 * @return Character used for defining class paths for the java compiler and java virtual machine.
	 */
	public static char pathSeparator(){ return PATH_SEPARATOR; }

	/**
	 * Character used for enclosing class paths, class sources, etc for the java compiler and java virtual machine.
	 * @return Character used for enclosing class paths, class sources, etc for the java compiler and java virtual machine.
	 */
	public static char closingCharacter(){ return CLOSING_CHARACTER; }
    
	/**
	 * Determines the Path for the given unalcol class
	 * @param cl Class to be analyzed according to its path
	 * @return The Path for the given unalcol class.
	 */
	public static String applicationPath( Class<?> cl ){
		String applicationDir = cl.getResource(cl.getSimpleName()+".class").getPath();
		applicationDir = applicationDir.substring(0, applicationDir.lastIndexOf("/unalcol"));
		if( applicationDir.endsWith(".jar!")) applicationDir = applicationDir.substring(0, applicationDir.lastIndexOf("/"));	
		if( applicationDir.startsWith("file:")) applicationDir = applicationDir.substring(5);
		return applicationDir;
	}
}