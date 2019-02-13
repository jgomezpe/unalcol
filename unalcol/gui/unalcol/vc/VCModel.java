package unalcol.vc;

public class VCModel extends KeyMapModel{
	public VCModel(){}
	
	public VCModel( BackEnd backend, FrontEnd frontend ){ init(backend, frontend); }
	
	public void init(BackEnd backend, FrontEnd frontend ){	
		if( backend!=null ){
			backend.setModel(this);
			sides.set(BackEnd.BACKEND, backend);
		}
		if( frontend != null ){
			frontend.setModel(this);
			sides.set(FrontEnd.FRONTEND, frontend);
		}
	}
}