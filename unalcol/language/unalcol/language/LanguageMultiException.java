package unalcol.language;

import unalcol.collection.Vector;

public class LanguageMultiException extends LanguageException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1151358486430386182L;

	protected Vector<LanguageException> multi = new Vector<LanguageException>();
	
	public LanguageMultiException(LanguageException e) {
		super(e.position(), e.code(), e.args());
		multi.add(e);
	}

	public void add(LanguageException e){ multi.add(e); }
	
	public LanguageException get(int index){ try{ return multi.get(index); }catch(Exception e){ return null;  } }
	
	@Override
	public String getMessage(){
		StringBuilder sb = new StringBuilder();
		for( LanguageException e:multi){
			sb.append(e.getMessage());
			sb.append('\n');
		}
		return sb.toString();
	}
}