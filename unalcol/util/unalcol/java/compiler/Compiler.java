package unalcol.java.compiler;

import unalcol.java.JavaOS;
import unalcol.java.process.*;

import java.io.*;
import java.util.*;

//
//Unalcol Service structure Pack 1.0 by Jonatan Gomez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/services/
//
/**
*
* Compiler  
* <p>A basic Java Compiler.</p>
*
* <P>
* <A HREF="https://github.com/jgomezpe/unalcol/blob/master/services/src/unalcol/reflect/compiler/Compiler.java" target="_blank">
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
public class Compiler {
	/**
	 * Class Paths used for compiling a Java Source File
	 */
	protected Vector<String> classPath = new Vector<>();
	/**
	 * Source Paths used for compiling a Java Source File
	 */
	protected Vector<String> sourcePath = new Vector<>();
	/**
	 * Destination path (compiling classes output directory)
	 */
	protected String destination;

	/**
	 * Compilation errors
	 */
	protected ByteArrayOutputStream err = new ByteArrayOutputStream();

	/**
	 * Compilation output
	 */
	protected ByteArrayOutputStream out = new ByteArrayOutputStream();

	/**
	 * Creates a Java compiler
	 */
	public Compiler() {}

	/**
	 * Adds a source path to the sources path collection
	 * @param path New source path
	 */
	public void addSourcePath(String path) { sourcePath.add(JavaOS.systemPath(path)); }

	/**
	 * Adds a class path to the classes path collection
	 * @param path New class path
	 */
	public void addClassPath(String path) { classPath.add(path); }

	/**
	 * Removes a path from a path collection
	 * @param paths Collection of paths
	 * @param path Path to be removed
	 */
	protected void removePath(Vector<String> paths, String path) {
		path = JavaOS.systemPath(path);
		for (int i = paths.size() - 1; i >= 0; i--) if (paths.get(i).compareTo(path) == 0) paths.remove(i); 
	}

	/**
	 * Removes a source path from the source path collection
	 * @param path Source path to be removed
	 */
	public void removeSourcePath(String path) { removePath(sourcePath, path); }

	/**
	 * Removes a class path from the class path collection
	 * @param path Class path to be removed
	 */
	public void removeClassPath(String path) { removePath(classPath, path); }

	/**
	 * Sets the destination path (directory for putting the class files)
	 * @param outputPath Destination path
	 */
	public void setOutputPath(String outputPath) { this.destination = JavaOS.systemPath(outputPath); }

	/**
	 * Creates the appropriated java compiling code for the given paths (source or classes)
	 * @param paths Collection of paths (source or classes)
	 * @return The appropriated java compiling code for the given paths (source or classes)
	 */
	protected String commandStr(Vector<String> paths) {
		StringBuilder sb = new StringBuilder();
		if (paths.size() > 0) {
			sb.append(JavaOS.closingCharacter());
			sb.append(paths.get(0));
			for (int i = 1; i < paths.size(); i++) {
				sb.append(JavaOS.pathSeparator());
				sb.append(paths.get(i));
			}
			sb.append(JavaOS.closingCharacter());
		}
		return sb.toString();
	}

	/**
	 * Spawns a process to compile the java source code file specified in the 'javaFile' parameter.
	 * @param javaFile Java file to be compiled
	 * @return <i>true</i> if the compilation succeeds, <i>false</i> otherwise.
	 */
	public boolean run(String javaFile){
		err.reset();
		out.reset(); 
		int nParameters = 2;
		// building the compiling command
		nParameters += (classPath.size()>0)?2:0;
		nParameters += (sourcePath.size()>0)?2:0;
		nParameters += (destination!=null)?2:0;

		String[] command = new String[nParameters];
		command[0] = "javac";
		int pos = 1;

		if( classPath.size() > 0 ){
			command[pos] = "-classpath";
			command[pos+1] = commandStr(classPath);
			pos += 2;
		}

		if( sourcePath.size() > 0 ){
			command[pos] = "-sourcepath";
			command[pos+1] = commandStr(sourcePath);
			pos += 2;
		}

		if (destination != null) {
			command[pos] = " -d ";
			command[pos+1] = this.destination;
			pos += 2;
		}

		command[pos] = javaFile;

		StringBuilder sb = new StringBuilder();
		for( int i=0; i<command.length; i++ ){
			sb.append( command[i]);
			sb.append(" ");
		}		
		return ExternalProcess.run(sb.toString(), new PrintStream(out), new PrintStream(err));
	}

	/**
	 * Resets the destination, class and source paths of the compiler
	 */
	public void clear(){
		classPath.clear();
		sourcePath.clear();
		destination = null;
		err.reset();
		out.reset();
	}

	/**
	 * Obtains the error message generated by the last compilation process
	 * @return String containing the error message produced by the last compilation process
	 */
	public String error(){ return err.toString(); }

	/**
	 * Obtains the message generated by the last compilation process
	 * @return String containing the message produced by the last compilation process
	 */
	public String out(){ return out.toString(); }
}