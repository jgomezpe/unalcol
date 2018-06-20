package unalcol.i18n;

import unalcol.xml.XMLDocument;
import unalcol.xml.XMLManifest;
import unalcol.types.collection.keymap.HTKeyMap;

public class I18N{
	public static final String MSG = "msg";
	public static final String i18n = "i18n";
	protected static HTKeyMap<String, XMLManifest> languages = new HTKeyMap<String, XMLManifest>();
	protected static XMLManifest current=null;

	public static boolean use(String language){
		if( languages.valid(language) ){
			current = languages.get(language);
			return true;
		}
		return false;
	}
	
	public static boolean remove(String language) { return languages.remove(language);	}
	
	public static boolean add(String language, XMLManifest value){ return languages.set(language, value); }
	
	public static boolean add(String language, String xmlStr){ return languages.set(language, new XMLManifest(i18n, new XMLDocument(xmlStr))); }
	
	public static String get(String key){
		if( current!= null && current.valid(key) ) return process((String)current.get(key).get(MSG));
		return key;
	}	

	public static String process( String message ){
		StringBuilder sb = new StringBuilder();
		int i=0; 
		while( i<message.length() ){
			char c = message.charAt(i); 
			if(c=='\\'){
				i++;
				if(i<message.length()){
					c = message.charAt(i);
					switch( c ){
						case '\\': sb.append('\\'); break;
						case 'n':  sb.append('\n'); break;
						case 't':  sb.append('\t'); break;
						default: sb.append('\\'); sb.append(c); 
					}
					i++;
				}else{
					sb.append(c);
				}
			}else{
				sb.append(c);
				i++;
			}
		}
		return sb.toString();
	}

	public static XMLManifest load(String xmlStr){ return new XMLManifest(i18n, new XMLDocument(xmlStr)); }
}