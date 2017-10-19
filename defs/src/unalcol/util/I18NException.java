package unalcol.util;

public class I18NException extends Exception{
	protected String code;
	protected Object[] args;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4106787401074640379L;

	public I18NException(String code, Object... args){
		this.code = code;
		this.args = args;
	}
	
	public String code(){ return code; }
	
	public Object[] args(){ return args; }
	
	@Override
	public String getMessage(){ 
		String formated = I18N.get(code);
		if( formated!=null ) return String.format(formated,args); 
		else return "Invalid code "+code; 
	} 
}