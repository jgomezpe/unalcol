package unalcol.gui.js.mode.server;

import unalcol.gui.Controller;
import unalcol.gui.View;
import unalcol.reflect.plugin.PlugInDescriptor;
import unalcol.reflect.plugin.PlugInLoader;

public class PullServerController implements Controller{
	public static final String SERVER = "unalcol_servlet";
	
	protected ServerView view; 
	
	public PullServerController(){ super(); }

	public String pull(){ return view.queue(); }

	@Override
	public String[] id(){ return new String[]{SERVER}; }

	@Override
	public void init(PlugInDescriptor element, PlugInLoader builder){}

	@Override
	public void set(View view) { this.view = (ServerView)view; }

	@Override
	public View view(){ return view; }
}