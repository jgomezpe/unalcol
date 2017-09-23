package unalcol.language;

public class LanguageException extends Exception{
	public static final int LEXER=0;
	public static final int PARSER=1;
	public static final int MEANER=2;
	
	protected String message;
	
	public LanguageException( String message ){
		this.message = message;
	}
	
	public String getMessage(){
		return message;
	}
	
	private static final long serialVersionUID = -3576803732938569084L;
}