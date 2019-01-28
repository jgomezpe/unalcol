package unalcol.json;

import unalcol.i18n.I18N;
import unalcol.i18n.I18NException;
import unalcol.types.collection.vector.Vector;
import unalcol.util.ParseUtil;

public class JSONParser extends ParseUtil{
	public static final String NOVALID = "JSON_novalid";
	public static final String WORD = "JSON_word";
	public static final String OBJECT = "JSON_object";
	public static final String UNEXPECTED = "JSON_unexpected";
	public static final String KEY = "JSON_key";
	public static final String VALUE = "JSON_value";
	public static final String OPTION = "JSON_option";
	public static final String ARRAY = "JSON_array";
	
	public JSONParser(){}
	
	public Object word() throws Exception{
		if( code.substring(pos, pos+4)=="true") return true;
		if( code.substring(pos, pos+5)=="false") return true;
		if( code.substring(pos, pos+4)=="null") return null;
		throw new I18NException(NOVALID, I18N.get(WORD), code.substring(pos,pos+5));
	}
	
	public Object object() throws Exception{
		if( c()!='{' ) throw new I18NException(NOVALID, I18N.get(OBJECT), code.substring(pos,pos+1));
		pos++;
		JSON map = new JSON();
		remove_space_check_end();
		while( c()!='}' ){
			String key = string(); 
			remove_space_check_end();
			if(c()!=':') throw new I18NException(UNEXPECTED, c(), "", ":" );
			pos++;
			Object value = parse();
			map.set(key, value);
			remove_space_check_end();
			if(c()==','){
				pos++;
				remove_space_check_end();
				if( c()=='}' ) throw new I18NException(UNEXPECTED, "{", "", I18N.get(KEY)+":"+I18N.get("VALUE"));
			}else if( c()!='}' )  throw new I18NException(UNEXPECTED, c(), I18N.get(OPTION), ", }" );
		}
		pos++;
		return map;
	}
	
	public Object array() throws Exception{
		if( c()!='[' ) throw new I18NException(NOVALID, I18N.get(ARRAY), code.substring(pos,pos+1));
		pos++;
		Vector<Object> v = new Vector<Object>();
		remove_space_check_end();
		while( c()!=']' ){
			v.add(parse());
			remove_space_check_end();
			if(c()==','){
				pos++;
				remove_space_check_end();
				if( c()==']' ) throw new I18NException(UNEXPECTED, c(), "", I18N.get(VALUE) );
			}else if( c()!=']' )  throw new I18NException(UNEXPECTED, c(), I18N.get(OPTION), ", ]" );
		}
		pos++;
		return v;
	}
	
	protected Object parse() throws Exception{
		remove_space();
		if( !end() ){
			char c = code.charAt(pos); 
			if( Character.isDigit(c) ) return number(); 
			switch( c ){
				case '-': return number(); 
				case 't': case 'f':	case 'n': return word();  
				case '{': return object(); 
				case '[': return array(); 
				case '"': return string(); 
			}
			return null;
		}else throw new Exception("end of file");
	}	
	
	public Object parse( String code ) throws Exception{
		return parse(code, 0);
	}

	public Object parse( String code, int start ) throws Exception{
		this.code = code;
		this.pos = start;
		Object obj = parse();
		//remove_space();
		//if( !end() ) throw new Exception("Extra characters are not allowed.." + code.substring(pos) );
		return obj;
	}	
}
