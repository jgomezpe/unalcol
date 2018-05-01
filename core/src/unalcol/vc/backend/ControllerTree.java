package unalcol.vc.backend;

import unalcol.reflect.plugin.PlugIn;
import unalcol.reflect.plugin.PlugInTree;
import unalcol.vc.frontend.FrontEnd;

public class ControllerTree extends PlugInTree implements Controller{
	protected BackEnd backend=null;

	@Override
	public void setBackend( BackEnd backend ){
		if( this.backend != backend ){
			this.backend = backend; 
			for( PlugIn p:children() ){ ((Controller)p).setBackend(backend); }
			frontend().link(this); 
		}
	}

	@Override
	public BackEnd backend() { return backend; }

	@Override
	public FrontEnd frontend(){ return (FrontEnd)backend.get(); }	
}