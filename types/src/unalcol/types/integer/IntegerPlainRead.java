/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unalcol.types.integer;
import unalcol.types.collection.UnalcolIterator;

import java.io.*;

/**
 *
 * @author jgomez
 */
public class IntegerPlainRead implements IntegerRead{
	
	public static void readDigitStar( UnalcolIterator<?,Integer> reader, StringBuilder sb ){
		boolean flag = true;
		while( reader.hasNext() && flag ){
			int c = reader.next();
			flag = Character.isDigit(c);
			if( flag ) sb.append((char)c);
		}
		if( !flag ) reader.back();
	}

	public static void removeSpaces( UnalcolIterator<?,Integer> reader ){
		boolean flag = true;
		while( reader.hasNext() && flag ){ flag = Character.isSpaceChar(reader.next()); }
		if( !flag ) reader.back();
	}

	public static String number( UnalcolIterator<?,Integer> reader ) throws IOException{
		if( reader.hasNext() ){
			StringBuilder sb = new StringBuilder();
			int c = reader.next();
			if( c=='-' || c=='+' ){
				sb.append((char)c);
				if( !reader.hasNext() ) throw new IOException("Unexpected eof");
				c = reader.next();
			}	
			if( Character.isDigit(c) ){ 
				sb.append((char)c);
				readDigitStar(reader, sb);
				return sb.toString();
			}
			throw new IOException("Unexpected symbol " + (char)c);
		}		
		throw new IOException("Unexpected eof");
	}
	
	@Override
	public Integer read(UnalcolIterator<?,Integer> reader) throws IOException{
		removeSpaces(reader);
		return Integer.parseInt(number(reader));
    }
	
	@Override
	public String toString(){ return "IntegerPlainRead"; }		
}
