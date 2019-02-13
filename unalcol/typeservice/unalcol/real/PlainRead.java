/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unalcol.real;
import unalcol.exception.ParamsException;

import unalcol.iterator.Backable;

/**
 *
 * @author jgomez
 */
public class PlainRead implements Read{

	public static String number(Backable<Integer> reader) throws ParamsException{
		StringBuilder sb = new StringBuilder();
		unalcol.integer.PlainRead.removeSpaces(reader);
		sb.append( unalcol.integer.PlainRead.number(reader) );
		if( !reader.hasNext() ) return sb.toString();
		int c = reader.next();
		if( c == '.' ){
			sb.append((char)c);
			sb.append( unalcol.integer.PlainRead.number(reader) );
			if( !reader.hasNext() ) return sb.toString();
			c = reader.next();
		}
		if( c!='e' && c!='E' ){
			reader.back();
			return sb.toString();
		}
		sb.append((char)c);
		sb.append( unalcol.integer.PlainRead.number(reader) );
		System.out.println("DoublePlainRead "+sb.toString());
		return sb.toString();
    }

	public Double read(Backable<Integer> reader) throws ParamsException{ return Double.parseDouble(number(reader)); }
	
	@Override
	public String toString(){ return "DoublePlainRead"; }	
}