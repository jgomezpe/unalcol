package unalcol.vc;

public interface BackEnd extends Side {
	public static final String BACKEND = "unalcol.vc.BackEnd";
	public static final String CONTROLLER = "controller";
	default String id(){ return BACKEND; }
	default void setId(String id){}
	default FrontEnd frontend(){
		Model m = model();
		if( m==null ) return null;
		return (FrontEnd)m.side(FrontEnd.FRONTEND);
	}
	default Controller controller( String id ){ return (Controller)component(id); }
}