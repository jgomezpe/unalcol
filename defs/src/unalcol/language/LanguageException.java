package unalcol.language;

import unalcol.io.Position;
import unalcol.util.I18NException;

public class LanguageException extends I18NException{
	protected Position position;
	public LanguageException( Position position, String code, Object... args ){ 
		super(code, args);
		this.position = position;
	}
	
	public Position position(){ return position; }
	
	private static final long serialVersionUID = -3576803732938569084L;
}