package core;

import unalcol.i18n.I18N;

public class I18NTest {
	public static void main(String[] args){
		I18N.setLanguage("spanish");
		I18N.use("basic");
		System.out.println(I18N.get("code"));
		System.out.println(I18N.get("arity"));
		System.out.println(I18N.get("priority"));
		System.out.println(I18N.get("rotate"));
	}
}
