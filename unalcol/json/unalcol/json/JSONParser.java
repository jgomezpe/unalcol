package unalcol.json;

import unalcol.i18n.I18N;
import unalcol.collection.Vector;
import unalcol.constants.Json;
import unalcol.exception.ParamsException;
import unalcol.string.Parser;

public class JSONParser extends Parser{
	
	public JSONParser(){}
	
	public Object word() throws Exception{
		if( code.substring(pos, pos+4)=="true"){
			pos += 4;
			return true;
		}
		if( code.substring(pos, pos+5)=="false"){
			pos += 5;
			return false;
		}
		if( code.substring(pos, pos+4)=="null"){
			pos += 4;
			return null;
		}
		throw new ParamsException(Json.NOVALID, I18N.get(Json.WORD), code.substring(pos,pos+5));
	}
	
	public JSON object() throws Exception{
		if( spacedchar()!='{' ) throw new ParamsException(Json.NOVALID, I18N.get(Json.OBJECT), code.substring(pos,pos+1));
		pos++;
		JSON map = new JSON();
		while( spacedchar()!='}' ){
			String key = string(); 
			if(spacedchar()!=':') throw new ParamsException(Json.UNEXPECTED, c(), "", ":" );
			pos++;
			Object value = parse();
			map.set(key, value);
			if(spacedchar()==','){
				pos++;
				if( spacedchar()=='}' ) throw new ParamsException(Json.UNEXPECTED, '}', "", I18N.get(Json.KEY)+":"+I18N.get("VALUE"));
			}else if( c()!='}' )  throw new ParamsException(Json.UNEXPECTED, c(), I18N.get(Json.OPTION), ", }" );
		}
		pos++;
		return map;
	}
	
	public Object[] array() throws Exception{
		if( c()!='[' ) throw new ParamsException(Json.NOVALID, I18N.get(Json.ARRAY), code.substring(pos,pos+1));
		pos++;
		Vector<Object> v = new Vector<Object>();
		while( spacedchar()!=']' ){
			v.add(parse());
			if(spacedchar()==','){
				pos++;
				if( spacedchar()==']' ) throw new ParamsException(Json.UNEXPECTED, c(), "", I18N.get(Json.VALUE) );
			}else if( c()!=']' )  throw new ParamsException(Json.UNEXPECTED, c(), I18N.get(Json.OPTION), ", ]" );
		}
		pos++;
		return v.toArray();
	}
	
	protected Object parse() throws Exception{
		char c = spacedchar();
		if( Character.isDigit(c) ) return number(); 
		switch( c ){
			case '-': return number(); 
			case 't': case 'f':	case 'n': return word();  
			case '{': return object(); 
			case '[': return array(); 
			case '"': return string(); 
		}
		return null;
	}	
	
	public Object parse( String code ) throws Exception{
		return parse(code, 0);
	}

	public Object parse( String code, int start ) throws Exception{
		this.code = code;
		this.pos = start;
		Object obj = parse();
		return obj;
	}	
}