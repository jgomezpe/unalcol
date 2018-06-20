/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unalcol.types.real;
import java.io.IOException;

import unalcol.types.collection.UnalcolIterator;
import unalcol.types.integer.IntegerPlainRead;

/**
 *
 * @author jgomez
 */
public class DoublePlainRead implements DoubleRead{

	public static String number(UnalcolIterator<?, Integer> reader) throws IOException{
		StringBuilder sb = new StringBuilder();
		IntegerPlainRead.removeSpaces(reader);
		sb.append( IntegerPlainRead.number(reader) );
		if( !reader.hasNext() ) return sb.toString();
		int c = reader.next();
		if( c == '.' ){
			sb.append((char)c);
			sb.append( IntegerPlainRead.number(reader) );
			if( !reader.hasNext() ) return sb.toString();
			c = reader.next();
		}
		if( c!='e' && c!='E' ){
			reader.back();
			return sb.toString();
		}
		sb.append((char)c);
		sb.append( IntegerPlainRead.number(reader) );
		System.out.println("DoublePlainRead "+sb.toString());
		return sb.toString();
    }

	public Double read(UnalcolIterator<?, Integer> reader) throws IOException{ return Double.parseDouble(number(reader)); }
	
	@Override
	public String toString(){ return "DoublePlainRead"; }	
}