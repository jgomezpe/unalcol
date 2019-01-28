package unalcol.vc;

public interface View extends Component{
	default BackEnd back(){
		Side s = side();
		if( s==null ) return null; 
		Model m = s.model();
		if( m==null ) return null;
		return (BackEnd)m.side(BackEnd.BACKEND);
	}

	default FrontEnd front(){ return (FrontEnd)side(); }
}