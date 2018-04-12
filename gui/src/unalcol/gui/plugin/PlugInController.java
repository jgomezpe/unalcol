package unalcol.gui.plugin;

import unalcol.gui.Controller;
import unalcol.gui.SimpleVCElement;
import unalcol.gui.View;
import unalcol.reflect.plugin.PlugInDescriptor;
import unalcol.reflect.plugin.PlugInException;
import unalcol.reflect.plugin.PlugInLoader;

public class PlugInController extends SimpleVCElement implements Controller{
	protected Controller cId;
	protected String[] id;

	public PlugInController( String[] id ){ this.id = id; }

	@Override
	public String[] id(){ return id; }

	@Override
	public void init(PlugInDescriptor element, PlugInLoader builder) {
		try{ cId = (Controller)builder.load(element.getAttribute("id"), PlugInsView.CONTROLLER); } catch (PlugInException e){ e.printStackTrace(); }
	}
	
	@Override
	public Object get( String id ){ if( is(id) ) return this; else if( cId!=null ) return cId.get(id); return null; }
	
	public void set( View view ){
		if( this.view != view ){ view.register(cId); }
		super.set(view);
	}	
}