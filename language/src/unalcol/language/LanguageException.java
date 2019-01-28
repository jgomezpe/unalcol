package unalcol.language;

import unalcol.i18n.I18NException;
import unalcol.types.collection.iterator.PositionTrack;

public class LanguageException extends I18NException{
	protected PositionTrack PositionTrack;
	public LanguageException( PositionTrack PositionTrack, String code, Object... args ){ 
		super(code, args);
		this.PositionTrack = PositionTrack;
	}
	
/*	public LanguageException( String code, Object... args ){ 
		this(new PositionTrack(0,0), code, args);
	}
*/	
	public PositionTrack position(){ return PositionTrack; }
	public void setPosition(PositionTrack PositionTrack){ this.PositionTrack = PositionTrack; }
	
	private static final long serialVersionUID = -3576803732938569084L;
}