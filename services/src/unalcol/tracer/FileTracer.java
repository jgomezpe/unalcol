package unalcol.tracer;

import java.io.FileWriter;
import java.io.IOException;

//
//Unalcol Service structure Pack 1.0 by Jonatan Gomez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/services/
//
/**
*
* ConsoleTracer
* <P> A Tracer based on files (stores information of traced objects in a given file).
*
* <P>
* <A HREF="https://github.com/jgomezpe/unalcol/blob/master/services/src/unalcol/tracer/FileTracer.java" target="_blank">
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
public class FileTracer extends OutputStreamTracer {
    /**
     * File that is associated to the tracer
     */
    protected FileWriter file;
    /**
     * File Name
     */
    protected String fileName;

    /**
     * Creates a file tracer
     * @param fileName File Name
     */
    public FileTracer(String fileName) {
        try {
            file = new FileWriter(fileName);
        } catch (IOException e) {
            // @TODO I have to check the tracer architecture as plugins
            //e.printStackTrace();
        }
    }

    /**
     * Creates a file tracer
     * @param fileName File Name
     * @param SEPARATOR Character used for separating traced values.
     */
    public FileTracer(String fileName, char SEPARATOR) {
    	this( fileName );
    	this.SEPARATOR = SEPARATOR;
    }

    /**
     * Creates a file tracer
     * @param fileName File Name
     * @param SEPARATOR Character used for separating traced values.
     * @param addNewline used for determining if a new line symbol is added after tracing an object or not
     */
    public FileTracer(String fileName, char SEPARATOR, boolean addNewline) {
    	this( fileName, SEPARATOR );
    	this.addNewLine = addNewline;
    }

    /**
     * Shows the traced information sent by the source into the file
     * @param str Traced information to be shown in the file
     */
    public void write(String str) {
        try {
            file.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the associated file
     */
    public void close() {
        try {
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cleans the traced information
     */
    public void clean() {
        close();
        try {
            file = new FileWriter(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}