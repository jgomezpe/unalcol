package unalcol.java.process;
import java.io.*;
//
//Unalcol Service structure Pack 1.0 by Jonatan Gomez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/services/
//
/**
*
* ProcessInputStream  
* <p>A Class for processing input streams used by an External Process (command)..</p>
*
* <P>
* <A HREF="https://github.com/jgomezpe/unalcol/blob/master/services/src/unalcol/reflect/process/ProcessInputStream.java" target="_blank">
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
public class ProcessInputStream implements Runnable{
	/**
	 * OutputStream used by the External Process (command) that is going to be read
	 */
	protected InputStream is;

	/**
	 * Thread used for reading the OutputStream while the External Process is running
	 */
	protected Thread thread;

	/**
	 * External Process being executed
	 */
	protected ExternalProcess process;

	/**
	 * OutputStream associated to the OutputStream used by the External Process
	 */
	protected PrintStream out = null;

	/**
	 * Creates an object for reading the OutputStreams used by an External Process (command). 
	 * @param is OutputStream used by the External Process (command) that is going to be read
	 * @param process External Process being executed 
	 */
	public ProcessInputStream( InputStream is, ExternalProcess process ) {
		this.is = is;
		this.process = process;
	}

	/**
	 * Creates an object for reading the OutputStreams used by an External Process (command).
	 * @param is OutputStream used by the External Process (command) that is going to be read
	 * @param process External Process being executed
	 * @param out OutputStream associated to the OutputStream used by the External Process
	 */
	public ProcessInputStream( InputStream is, ExternalProcess process, PrintStream out ) {
		this.is = is;
		this.process = process;
		this.out = out;
	}

	/**
	 * Starts the OutputStream processing
	 */
	public void start () {
		thread = new Thread(this);
		thread.start ();
	}

	/**
	 * Process the OutputStream used by the External Process
	 */
	public void run () {
		try {
			if( out != null ) while(is.available() > 0 || process.is_running ) 	if( is.available() > 0 ) out.print((char)is.read());
			else while(is.available() > 0 || process.is_running ) if( is.available() > 0 ) is.read();	
			is.close();
		}catch (Exception ex) { ex.printStackTrace (); }
	}
}