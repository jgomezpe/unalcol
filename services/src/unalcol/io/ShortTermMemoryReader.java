package unalcol.io;

import java.io.*;

//
//Unalcol Service structure Pack 1.0 by Jonatan Gomez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/services/
//
/**
*
* ShortTermMemoryReader  
* <p>A Reader that is able to maintain the latest read characters (according to its memory size).</p>
*
* <P>
* <A HREF="https://github.com/jgomezpe/unalcol/blob/master/services/src/unalcol/io/ShortTermMemoryReader.java" target="_blank">
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
public class ShortTermMemoryReader extends Reader {
    /**
     * Default number of characters that is able to maintain the reader (last read characters)
     */
    public static int MEMORY_SIZE = 100000;

    /**
     * Last read characters
     */
    protected int[] memory = null;
    /**
     * Number of characters that is able to maintain the reader (last read characters)
     */
    protected int n;

    protected int start = 0;
    protected int end = 0;
    /**
     * Current cursor position
     */
    protected int pos = 0;

    /**
     * Row of the last read characters
     */
    protected int[] row = null;
    /**
     * Column of the last read characters
     */
    protected int[] column = null;
    /**
     * Linefeed character
     */
    protected int LINEFEED = (int) '\n';
    /**
     * Carriage return character
     */
    protected int CARRIAGERETURN = (int) '\r';

    /**
     * Underline Reader
     */
    protected Reader reader;

    /**
     * Initializes the inner state of the UnalcolReader
     * @param n Maximum number of characters stored by the Reader
     */
    private void init(int n) {
        this.n = n;
        memory = new int[n];
        row = new int[n];
        column = new int[n];
    }

    /**
     * Creates an exception according to the message and the current reader cursor position.
     * @param message Message of the Exception that is generated.
     * @return An Exception with extra information about the current cursor position (2D info).
     */
    public RowColumnReaderException getException( String message ){
        return new RowColumnReaderException(row[pos], column[pos], message);
    }

    /**
     * Creates a short term memory reader that maintains at most the last <i>MEMORY_SIZE</i> read symbols
     * @param MEMORY_SIZE Memory size (maintains at most the last <i>MEMORY_SIZE</i> read symbols)
     * @param reader The underline reader
     */
    public ShortTermMemoryReader(int MEMORY_SIZE, Reader reader) {
        init(MEMORY_SIZE + 1);
        this.reader = reader;
    }

    /**
     * Creates a short term memory reader that maintains at most the last <i>MEMORY_SIZE</i> read symbols
     * @param MEMORY_SIZE Memory size (maintains at most the last <i>MEMORY_SIZE</i> read symbols)
     * @param reader The underline InputStream
     */
    public ShortTermMemoryReader(int MEMORY_SIZE, InputStream reader) {
        init(MEMORY_SIZE + 1);
        this.reader = new InputStreamReader(reader);
    }

    /**
     * Creates a short term memory reader that maintains at most the last <i>MEMORY_SIZE</i> (default) read symbols
     * @param reader The underline Reader
     */
    public ShortTermMemoryReader(Reader reader) {
        this(MEMORY_SIZE, reader);
    }

    /**
     * Creates a short term memory reader that maintains at most the last <i>MEMORY_SIZE</i> (default) read symbols
     * @param reader The underline InputStream.
     */
    public ShortTermMemoryReader(InputStream reader) {
        this(MEMORY_SIZE, reader);
    }

    /**
     * Creates a short term memory reader using a String as InputStream
     * @param reader The underline InputStream
     */
    public ShortTermMemoryReader(String reader) {
        this(reader.length(), new StringReader(reader));
    }

    /**
     * Obtains a new symbol from the underline reader.
     * @return The next available symbol
     * @throws IOException If there was an exception reading a symbol
     */
    protected int get() throws IOException {
        return reader.read();
    }

    /**
     * Determines the maximum number of symbols that can "virtually" returned to the reader. (Using the memory)
     * @return Maximum number of symbols that can "virtually" returned to the reader. (Using the memory)
     */
    public int maxBack() {
        if (pos >= start)return pos - start;
        return n + pos - start;
    }

    /**
     * Returns the last k read character to the stream, if possible
     * @param k Number of characters to be returned to the stream
     * @return true if it was possible to return the last k read character, false otherwise
     */
    public boolean back(int k) {
        boolean flag = (k <= maxBack());
        if (flag) {
            pos -= k;
            if (pos < 0) {
                pos += n;
            }
        }
        return flag;
    }

    /**
     * Returns the last read character to the stream, if possible
     * @return true if it was possible to return the last read character, false otherwise
     */
    public boolean back() {
        boolean flag = (pos != start);
        if (flag) {
            pos--;
            pos = (pos < 0) ? n - 1 : pos;
        }
        return flag;
    }

    /**
     * Reads a character
     * @return The character that has been read
     * @throws IOException An exception if it was not possible to read a character.
     */
    @Override
    public int read() throws IOException {
        try{
            int c;
            if (pos == end) {
                c = get();
                if (c != -1) {
                    end = (end + 1 < n) ? end + 1 : 0;
                    if (c == CARRIAGERETURN ||
                        (c == LINEFEED && memory[pos] != CARRIAGERETURN)) {
                        row[end] = row[pos] + 1;
                        column[end] = 0;
                    } else {
                        row[end] = row[pos];
                        if (c != LINEFEED) {
                            column[end] = column[pos] + 1;
                        }
                    }
                    pos = end;
                    memory[pos] = c;
                    if (end == start) {
                        start = (start + 1 < n) ? start + 1 : 0;
                    }
                }
            } else {
                pos = (pos + 1 < n) ? pos + 1 : 0;
                c = memory[pos];
            }
            return c;
        }catch( IOException e ){
            throw getException(e.getMessage());
        }
    }


    /**
     * Read characters into a portion of an array. This method will block until
     * some input is available, an I/O error occurs, or the end of the stream is reached.
     * @param cbuf Destination buffer
     * @param off Offset at which to start storing characters
     * @param len Maximum number of characters to read
     * @return The number of characters read, or -1 if the end of the stream has been reached
     * @throws IOException - If an I/O error occurs
     */
    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        try{
            int k = 0;
            if (off >= 0 && off < cbuf.length) {
                int c = read();
                while (c != -1 && k < len && off < cbuf.length) {
                    cbuf[off] = (char) c;
                    k++;
                    off++;
                }
            }
            return k;
        }catch( IOException e ){
            throw getException(e.getMessage());
        }
    }

    /**
     * Closes the underline reader
     * @throws IOException An exception if it was not possible to close the reader.
     */
    @Override
    public void close() throws IOException {
        try{
            reader.close();
        }catch( IOException e ){
            throw getException(e.getMessage());
        }
    }

    /**
     * Marks the actual position as a mark for reseting the reader. The readAheadLimit is
     * not used since it is determined dynamically. if the number of characters read,
     * after calling the mark method, is greater than the size of the buffer
     * then the mark is moved to such maximum number of characters. In this way at least the last n characters
     * are always maintained by the reader.
     * @param readAheadLimit Non used.
     */
    @Override
    public void mark(int readAheadLimit) {
        mark();
    }

    /**
     * Marks the actual position as a mark for reseting the reader
     */
    public void mark() {
        start = pos;
    }

    /**
     * Returns if the reader supports marks.
     * @return true
     */
    @Override
    public boolean markSupported() {
        return true;
    }

    /**
     * Resets the reader to the previous mark. Marks are dynamically adjusted in such a way that
     * if the number of characters read, after calling the mark method, is greater than the size of the buffer
     * then the mark is moved to such maximum number of characters. In this way at least the last n characters
     * are always maintained by the reader.
     */
    @Override
    public void reset() {
        back(maxBack());
    }

    /**
     * Skips n characters
     * @param n Characters to be skipped
     * @return Number of characters actually skipped
     * @throws IOException An exception if it was not possible to skip <i>n</i> characters.
     */
    @Override
    public long skip(long n) throws IOException {
        try{
            long k = 0;
            while (k < n && read() != -1) {
                k++;
            }
            return k;
        }catch( IOException e ){
            throw getException(e.getMessage());
        }

    }

    /**
     * Gets the actual reading row
     * @return Reading row
     */
    public int getRow() {
        return row[pos];
    }

    /**
     * Gets the actual reading column
     * @return Reading column
     */
    public int getColumn() {
        return column[pos];
    }
}
