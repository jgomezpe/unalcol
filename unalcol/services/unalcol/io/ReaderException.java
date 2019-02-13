/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unalcol.io;

import unalcol.constants.InnerCore;
import unalcol.exception.ParamsException;
import unalcol.i18n.I18N;
import unalcol.iterator.Position2DTrack;
import unalcol.iterator.PositionTrack;

//
//Unalcol Service structure Pack 1.0 by Jonatan Gomez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/services/
//
/**
*
* RowColumnReaderException 
* <p>An exception for readers that use two dimensions (row, columns) cursor location information.</p>
*
* <P>
* <A HREF="https://github.com/jgomezpe/unalcol/blob/master/services/src/unalcol/io/RowColumnReaderException.java" target="_blank">
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
public class ReaderException extends ParamsException{
	/**
	 * Serial information
	 */
	private static final long serialVersionUID = -4896269613684585270L;

	protected PositionTrack pos=null;
	/**
	 * Creates an Exception with the associated message for the row and column cursor position
	 * @param row Row information of the cursor when the exception was thrown.
	 * @param column Column information of the cursor when the exception was thrown.
	 * @param message Message associated to the exception.
	 */
	public ReaderException( PositionTrack pos, String code, Object... args ){ 
		super(code, args);
		this.pos = pos;
		Object[] nargs;
		if( pos instanceof Position2DTrack ){
			nargs = new Object[args.length+2];
			nargs[args.length] = ((Position2DTrack)pos).row();
			nargs[args.length+1] = ((Position2DTrack)pos).column();
		}else{
			nargs = new Object[args.length+1];
			nargs[args.length] = pos.offset();
		}	
		System.arraycopy(args, 0, nargs, 0, args.length);
		this.args = nargs;
	}
	
	public ReaderException( String code, Object... args ){ super(code, args); }
	
	protected String format(){
		String msg = super.format();
		if( msg != null && pos!=null ){
			String at;
			if( pos instanceof Position2DTrack ) at = I18N.get(InnerCore.POS2D);
			else at = I18N.get(InnerCore.POS);
			if( at!=null ) msg += at;
			return msg;
		}
		return msg; 
	}
	

	/**
	 * Creates an Exception with the associated message for the row and column cursor position
	 * @param row Row information of the cursor when the exception was thrown.
	 * @param column Column information of the cursor when the exception was thrown.
	 * @param message Message associated to the exception.
	 */
	public ReaderException( String code ){ super(code); }

	/**
	 * Returns the row information of the cursor when the exception was thrown.
	 * @return the row information of the cursor when the exception was thrown.
	 */
	public int getRow(){ return (Integer)args[0]; }

	/**
	 * Returns the column information of the cursor when the exception was thrown.
	 * @return the column information of the cursor when the exception was thrown.
	 */    
	public int getColumn(){ return (Integer)args[1]; }
}