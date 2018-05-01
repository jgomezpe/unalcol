package unalcol.vc.backend;

import unalcol.vc.frontend.FrontEnd;

public class SimpleController implements Controller{
	protected String id;
	protected BackEnd backend=null;
	
	@Override
	public void setBackend(BackEnd backend){
		if( this.backend != backend ){
			this.backend = backend; 
			frontend().link(this); 
		}
	}

	@Override
	public BackEnd backend() { return backend; }

	@Override
	public FrontEnd frontend(){ return (FrontEnd)backend.get();	}

	@Override
	public String id(){ return id; }

	@Override
	public void setId(String id) { this.id = id; }

}