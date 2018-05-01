package unalcol.vc.frontend;

import unalcol.vc.backend.BackEnd;

public class SimpleView implements View{
	protected String id;
	protected FrontEnd frontend=null;
	
	@Override
	public void setFrontend(FrontEnd frontend){
		if( this.frontend != frontend ){
			this.frontend = frontend; 
		}
	}

	@Override
	public void setBackend(BackEnd backend){}

	@Override
	public FrontEnd frontend() { return frontend; }

	@Override
	public BackEnd backend(){ return (BackEnd)frontend.get(); }

	@Override
	public String id(){ return id; }

	@Override
	public void setId(String id) { this.id = id; }

}
