package unalcol.util;

import unalcol.types.collection.keymap.HTKeyMap;
import unalcol.types.collection.keymap.ImmutableKeyMap;

public class I18N{
	public static final char MSG_SEPARATOR='='; 

	protected static HTKeyMap<String, ImmutableKeyMap<String,String>> languages = new HTKeyMap<String, ImmutableKeyMap<String,String>>();
	protected static ImmutableKeyMap<String,String> current=null;

	public static boolean use(String language){
		if(languages.get(language)==null) return false;
		current = languages.get(language);
		return true;
	}
	
	public static boolean remove(String language) { return languages.remove(language);	}
	
	public static boolean add(String language, ImmutableKeyMap<String,String> value){ return languages.add(language, value); }
	
	public static String get(String key) {	return current.get(key); }	

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

	public static ImmutableKeyMap<String, String> load(String config){
		HTKeyMap<String, String> table = new HTKeyMap<String,String>();
		String[] lines = config.split("\n");
		for( String line:lines ){
			String code = line.substring(0, line.indexOf(MSG_SEPARATOR));
			String message = line.substring(line.indexOf(MSG_SEPARATOR)+1);
			table.set(code,process(message));
		}
		return table;
	}
}