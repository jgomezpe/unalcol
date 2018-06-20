package unalcol.i18n;

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
		String message = I18N.get(code);
		if( message!=code ) return String.format(message,args); 
		StringBuilder sb = new StringBuilder();
		sb.append(code);
		for( int i=0; i<args.length; i++ ){
			sb.append(' ');
			sb.append(args[i]);
		}
		return sb.toString(); 
	} 
}