package unalcol.gui.plugin;

import unalcol.gui.VCElement;
import unalcol.gui.View;
import unalcol.reflect.plugin.PlugIn;
import unalcol.reflect.plugin.PlugInTree;

public class VCElementTree extends PlugInTree implements VCElement{
	protected View view=null;

	public PlugIn instance(){ return new VCElementTree(); }
	
	public void set(View view){
		if( this.view != view ){ 
			for( Object c:children )((VCElement)c).set(view);
			this.view=view; 
		}
	}
	
	public View view() { return view; }
}