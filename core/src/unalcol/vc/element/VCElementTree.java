package unalcol.vc.element;

import unalcol.reflect.plugin.PlugIn;
import unalcol.reflect.plugin.PlugInTree;
import unalcol.vc.VCElement;
import unalcol.vc.VCManager;

public class VCElementTree extends PlugInTree implements VCElement{
	protected VCManager manager=null;
	
	@Override
	public void set(VCManager manager) {
		if( this.manager != manager ){
			for( PlugIn p:children() ){ ((VCElement)p).set(manager); }
			this.manager = manager;
			manager.register(this);
		}
	}

	@Override
	public VCManager manager(){ return manager; }

}
