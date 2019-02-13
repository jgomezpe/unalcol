package core;

import unalcol.i18n.I18N;
import unalcol.json.JSONParser;
import unalcol.types.collection.keymap.LowLevelKeyMap;

public class JSONParserTest {
	public static String[] jsons = new String[]{
			"{\"code\",123.89e-23, \"array_\\uAA00\":[]}",
			"{\"code\":123.89e-23:x, \"array_\\uAA00\":[]}",
			"{code:123.89e-23, \"array_\\uAA00\":[]}",
			"{\"code\":123.89e--23, \"array_\\uAA00\":[]}",
			"{\"code\":123.89e-23, \"array_\\uAA00\":[]}"
	};
	
	public static void main( String[] args ){
		I18N.setLanguage("spanish");
		I18N.use("core");
		for( int i=0; i<jsons.length; i++ ){
			System.out.println("***********************"+i+"*******************");
			String code = jsons[i];
			JSONParser json = new JSONParser();
			try{ 
				Object obj = json.parse(code);
				if( obj instanceof LowLevelKeyMap ){
					@SuppressWarnings("unchecked")
					LowLevelKeyMap<String,Object> map = (LowLevelKeyMap<String,Object>)obj;
					for( String k : map.keys() ){ System.out.println(k + ":" + map.get(k));}
				}
				System.out.println(obj);
			}catch( Exception e ){
				System.out.println("Error..."+e.getMessage()); 
			}
		}	
	}
}