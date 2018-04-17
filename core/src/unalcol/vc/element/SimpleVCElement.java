package unalcol.vc.element;

import unalcol.reflect.plugin.PlugInTree;
import unalcol.vc.VCElement;
import unalcol.vc.VCManager;

public class SimpleVCElement extends PlugInTree implements VCElement{
	
	protected String id;
	protected VCManager manager=null;
	
	@Override
	public void set(VCManager manager){
		if( this.manager != manager ){
			this.manager = manager; 
			manager.register(this);
		}
	}

	@Override
	public VCManager manager() { return manager; }

	@Override
	public String id(){ return id; }
}