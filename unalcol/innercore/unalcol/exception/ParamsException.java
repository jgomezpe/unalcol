package unalcol.exception;

import unalcol.i18n.I18N;

public class ParamsException extends java.lang.Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6986218612974644636L;
		protected String code;
		protected Object[] args;

		public ParamsException(String code, Object... args){
			this.code = code;
			this.args = args;
		}
		
		public String code(){ return code; }

		protected String format(){
			String msg = I18N.get(code);
			if( msg != null ) return msg;
			if( code.indexOf("%d")>0 || code.indexOf("%c")>0 || code.indexOf("%s")>0 ) return code;
			return null; 
		}
		
		public Object[] args(){ return args; }
		
		@Override
		public String getMessage(){ 
			String message = format();
			if( message!=null ) return String.format(message,args); 
			StringBuilder sb = new StringBuilder();
			sb.append(code);
			for( int i=0; i<args.length; i++ ){
				sb.append(' ');
				sb.append(args[i]);
			}
			return sb.toString(); 
		} 		
		
		public static ParamsException wrap( String code, Exception e ){ return new ParamsException(code, e.getMessage()); }
}