package unalcol.i18n;

import unalcol.collection.KeyMap;
import unalcol.collection.keymap.HashMap;

public class I18N{
	public static final String MSG = "msg";
	public static final String i18n = "i18n";
	public static final String USES = "uses";
	public static final String PACK = "pack";
	
	protected KeyMap<String, Language> languages;
	protected Language defaultLang=new Language();
	protected Language lang=defaultLang;
	
	protected static I18N i18nObj =new I18N();
	
	public I18N(){
		languages = new HashMap<String,Language>();
		lang=defaultLang;
		languages.set(defaultLang.name(),defaultLang);
	}

	public static boolean use( String language ){
		try{
			Language l = i18nObj.languages.get(language);
			if( l==i18nObj.lang || l==null ) return false;
			i18nObj.lang = l;
			return true;
		}catch(Exception e){}
		return false;
	}
	
	public static String get(String key){
		String msg = i18nObj.lang.get(key);
		if( msg==null && i18nObj.lang != i18nObj.defaultLang ) msg = i18nObj.defaultLang.get(key);
		return msg;
	}
	
	public static void add( Language language ){ i18nObj.addLanguage(language); }

	public static void use( Language language ){
		i18nObj.setLanguage(language); 
	}
	
	public static void set( String code, String msg ){ i18nObj.defaultLang.set(code, msg); }
	
	public void addLanguage( Language language ){ this.languages.set(language.name(), language); }
	
	public void setLanguage( Language language ){
		addLanguage(language);
		use(language.name());
	}
	
	public Language getLanguage( String language ){
		try{
			return languages.get(language);
		}catch(Exception e){}
		return null;
	}
}