/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unalcol.types.real;
import java.io.IOException;

import unalcol.io.*;
import unalcol.services.MicroService;
import unalcol.types.collection.UnalcolIterator;
import unalcol.types.integer.IntegerPlainRead;

/**
 *
 * @author jgomez
 */
public class DoublePlainRead extends MicroService<Double>  implements Read<Double>{

	public static String number(UnalcolIterator<?, Integer> reader) throws IOException{
		StringBuilder sb = new StringBuilder();
		IntegerPlainRead.removeSpaces(reader);
		sb.append( IntegerPlainRead.number(reader) );
		if( !reader.hasNext() ) return sb.toString();
		int c = reader.next();
		if( c != '.' ){
			reader.back();
			return sb.toString();
		}
		sb.append(c);
		sb.append( IntegerPlainRead.number(reader) );
		if( !reader.hasNext() ) return sb.toString();
		c = reader.next();
		if( c!='e' && c!='E' ){
			reader.back();
			return sb.toString();
		}
		sb.append(c);
		sb.append( IntegerPlainRead.number(reader) );
		return sb.toString();
    }

	@Override
	public Double read(UnalcolIterator<?, Integer> reader) throws IOException{
		return Double.parseDouble(number(reader));
	}
}

