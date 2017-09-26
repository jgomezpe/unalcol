package unalcol.language;

import unalcol.util.I18NException;

public class LanguageException extends I18NException{
	public LanguageException( String code, Object... args ){ super(code, args); }
	
	private static final long serialVersionUID = -3576803732938569084L;
}