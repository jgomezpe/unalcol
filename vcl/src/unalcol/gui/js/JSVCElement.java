package unalcol.gui.js;

import unalcol.gui.VCElement;
import unalcol.gui.View;
import unalcol.reflect.plugin.PlugIn;
import unalcol.reflect.plugin.PlugInDescriptor;
import unalcol.reflect.plugin.PlugInLoader;

public class JSVCElement  implements VCElement, PlugIn {
	public static final String ID = "id";
	public static final String NONAME = "noname";
	
	protected JSView view=null;
	protected String id=NONAME;
	
	public JSVCElement(){}
	public JSVCElement(String id){ this.id = id; }
	
	@Override
	public void set(View view) { this.view = (JSView)view; }

	@Override
	public View view() { return view; }
	
	public String[] id(){ return new String[]{id}; }
	
	public void execute( String js_command ){ view.execute(js_command); }

	@Override
	public void init(PlugInDescriptor element, PlugInLoader builder){ id = element.getAttribute(ID); }
}