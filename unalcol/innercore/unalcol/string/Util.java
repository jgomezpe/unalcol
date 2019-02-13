package unalcol.string;

import unalcol.constants.InnerCore;
import unalcol.exception.ParamsException;

public class Util{
	
	protected static Parser parser = new Parser();
	
	public static String store( String str ){
		StringBuilder sb = new StringBuilder();
		sb.append('"');
		for( int i=0; i<str.length(); i++ ){
			char c = str.charAt(i);
			switch( c ){
				case '/': sb.append("\\/"); break;	
				case '\\': sb.append("\\\\"); break;
				case '\b': sb.append("\\b"); break;
				case '\f': sb.append("\\f"); break;
				case '\n': sb.append("\\n"); break;
				case '\r': sb.append("\\r"); break;
				case '\t': sb.append("\\t"); break;
				case '\'': sb.append("\'"); break;
				case '"': sb.append("\\\""); break;
				default:
					if( c < 32 || c > 255 ){
						sb.append("\\u");
						sb.append(Integer.toHexString((int)c));
					}else sb.append(c);
				break;
			}
		}
		sb.append('"');
		return sb.toString();	
	}
	
	public static char escape_char( char c ) throws Exception{
		switch( c ){
			case '\'': case '\\': case '/': return c;
			case 'b': return '\b';
			case 'f': return '\f';
			case 'n': return '\n';
			case 'r': return '\r';
			case 't': return '\t';
			default: throw new ParamsException(InnerCore.ESC_CHAR, c); 
		}
	}
	
	public static Object number( String txt, int pos ) throws Exception{ return parser.number(txt, pos); }
	
	public static char  character( String txt, int pos ) throws Exception{ return parser.character(txt,pos); }

	public static String string( String txt, int pos ) throws Exception{ return parser.string(txt, pos); }
	
	public static int parserConsumed(){ return parser.consumed(); }
}