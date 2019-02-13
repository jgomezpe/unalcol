package unalcol.constants;

import unalcol.i18n.I18N;

public class Json {
	public static final String NOVALID = "JSON_novalid";
	public static final String WORD = "JSON_word";
	public static final String OBJECT = "JSON_object";
	public static final String UNEXPECTED = "JSON_unexpected";
	public static final String KEY = "JSON_key";
	public static final String VALUE = "JSON_value";
	public static final String OPTION = "JSON_option";
	public static final String ARRAY = "JSON_array";
	
	public static void init(){
		InnerCore.init();
		I18N.set(NOVALID,"%s definition no valid. %s ");
		I18N.set(WORD,"Reserved word");
		I18N.set(OBJECT,"Object");
		I18N.set(OPTION," one of the following symbols");
		I18N.set(UNEXPECTED,"Unexpected symbol %c. Expecting %s %s");
		I18N.set(ARRAY,"Array");
	}
}