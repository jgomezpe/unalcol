package unalcol.vc.frontend;

import unalcol.reflect.plugin.PlugIn;
import unalcol.reflect.plugin.PlugInTree;
import unalcol.vc.backend.BackEnd;

public class ViewTree extends PlugInTree implements View{
	protected FrontEnd frontend=null;

	@Override
	public void setFrontend(FrontEnd frontend){
		if( this.frontend != frontend ){
			this.frontend = frontend; 
			for( PlugIn p:children() ){ ((View)p).setFrontend(frontend); }
		}
	}

	@Override
	public FrontEnd frontend() { return frontend; }

	@Override
	public void setBackend(BackEnd backend) {}

	@Override
	public BackEnd backend(){ return (BackEnd)frontend.get(); }		
}