package unalcol.util;

public class I18NException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4106787401074640379L;

	public I18NException(String code, Object... args){
		super(String.format(I18N.get(code),args));
	}
}