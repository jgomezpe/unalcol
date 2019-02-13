/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unalcol.integer;
import unalcol.constants.InnerCore;
import unalcol.exception.ParamsException;
import unalcol.io.ReaderException;
import unalcol.iterator.Backable;
import unalcol.iterator.Trackable;

/**
 *
 * @author jgomez
 */
public class PlainRead implements Read{
	
	public static void readDigitStar( Backable<Integer> reader, StringBuilder sb ){
		boolean flag = true;
		while( reader.hasNext() && flag ){
			int c = reader.next();
			flag = Character.isDigit(c);
			if( flag ) sb.append((char)c);
		}
		if( !flag ) reader.back();
	}

	public static void removeSpaces( Backable<Integer> reader ){
		boolean flag = true;
		while( reader.hasNext() && flag ){ flag = Character.isSpaceChar(reader.next()); }
		if( !flag ) reader.back();
	}

	@SuppressWarnings("unchecked")
	public static String number( Backable<Integer> reader ) throws ParamsException{
		if( reader.hasNext() ){
			StringBuilder sb = new StringBuilder();
			int c = reader.next();
			if( c=='-' || c=='+' ){
				sb.append((char)c);
				if( !reader.hasNext() ) throw new ParamsException(InnerCore.EOI);
				c = reader.next();
			}	
			if( Character.isDigit(c) ){ 
				sb.append((char)c);
				readDigitStar(reader, sb);
				return sb.toString();
			}
			if( reader instanceof Trackable ){
				throw new ReaderException(((Trackable<Integer>)reader).pos(), InnerCore.UNEXPCHAR, (char)c);
			}else throw new ParamsException(InnerCore.UNEXPCHAR, (char)c);
		}		
		throw new ParamsException(InnerCore.EOI);
	}
	
	@Override
	public Integer read(Backable<Integer> reader) throws ParamsException{
		removeSpaces(reader);
		return Integer.parseInt(number(reader));
    }
	
	@Override
	public String toString(){ return "IntegerPlainRead"; }		
}
