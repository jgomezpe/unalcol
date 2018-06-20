package unalcol.vc;

public interface FrontEnd extends Side{
	public static final String FRONTEND = "unalcol.vc.FrontEnd";	
	public static final String VIEW = "view";	
	default String id(){ return FRONTEND; }
	default void setId(String id){}
	default BackEnd backend(){
		Model m = model();
		if( m==null ) return null;
		return (BackEnd)m.side(BackEnd.BACKEND);
	}
	default View view( String id ){ return (View)component(id); }
}