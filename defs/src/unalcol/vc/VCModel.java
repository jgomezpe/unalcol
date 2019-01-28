package unalcol.vc;

public class VCModel extends KeyMapModel{
	public VCModel( BackEnd backend, FrontEnd frontend ){
		backend.setModel(this);
		frontend.setModel(this);
		sides.set(BackEnd.BACKEND, backend);
		sides.set(FrontEnd.FRONTEND, frontend);
	}
}