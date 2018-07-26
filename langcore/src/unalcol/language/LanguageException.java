package unalcol.language;

import unalcol.i18n.I18NException;
import unalcol.io.Position;

public class LanguageException extends I18NException{
	protected Position position;
	public LanguageException( Position position, String code, Object... args ){ 
		super(code, args);
		this.position = position;
	}
	
/*	public LanguageException( String code, Object... args ){ 
		this(new Position(0,0), code, args);
	}
*/	
	public Position position(){ return position; }
	public void setPosition(Position position){ this.position = position; }
	
	private static final long serialVersionUID = -3576803732938569084L;
}