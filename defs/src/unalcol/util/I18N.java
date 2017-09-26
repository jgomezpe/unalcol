package unalcol.util;

import unalcol.types.collection.keymap.HTKeyMap;
import unalcol.types.collection.keymap.ImmutableKeyMap;

public class I18N{
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
}