package unalcol.vc;

public interface Controller extends Component{
	default FrontEnd front(){
		Side s = side();
		if( s==null ) return null; 
		Model m = s.model();
		if( m==null ) return null;
		return (FrontEnd)m.side(FrontEnd.FRONTEND);
	}
	
	default BackEnd back(){ return (BackEnd)side(); }
}