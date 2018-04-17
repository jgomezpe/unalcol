package unalcol.vc.plugin;

import java.util.Vector;

import unalcol.reflect.plugin.PlugIn;
import unalcol.reflect.plugin.PlugInInstance;
import unalcol.reflect.plugin.PlugInLoader;
import unalcol.reflect.plugin.PlugInSet;
import unalcol.reflect.xml.XMLElement;
import unalcol.types.collection.Collection;
import unalcol.vc.Controller;
import unalcol.vc.VCElement;
import unalcol.vc.VCManager;
import unalcol.vc.View;

public class PlugInsVCManager implements VCManager{
	public static final String ID="_xml";
	public static final String CONTROLLER="controller";
	public static final String GUI="gui";

	protected boolean canRegisterControllers = false;
	protected int registered = 0;
	protected Vector<VCElement> toRegister = new Vector<VCElement>();
	protected PlugInSet<Controller> controller=null;
	protected PlugInSet<View> view=null;
	protected PlugInInstance<Controller> defaultController;
	protected PlugInInstance<View> defaultView;
	protected PlugInLoader loader;
	protected XMLController xml = new XMLController();
	
	public PlugInsVCManager( String url, PlugInInstance<Controller> defaultController, PlugInInstance<View> defaultView ){
		this( new String[]{url}, defaultController, defaultView ); 
	}
	
	public PlugInsVCManager( String[] urls, PlugInInstance<Controller> defaultController, PlugInInstance<View> defaultView ){ 
		toRegister.add(xml);
		loader = new PlugInLoader();
		try{ for(String url:urls) loader.addRepository(url); }catch(Exception e){}
		this.defaultController = defaultController;
		this.defaultView = defaultView;
	}
	
	public void init(XMLElement e){
		controller = new PlugInSet<Controller>(loader, Controller.TAG, PlugIn.ID, defaultController);
		view = new PlugInSet<View>(loader, View.TAG, XMLElement.TAG, defaultView);
		Controller c = controller.get(e);
		c.set(this);
		View v = view.get(e);
		v.set(this);
	}
	
	public void ready(){ canRegisterControllers=true; }
	
	protected void register(){ 
		registered = toRegister.size();
		canRegisterControllers = true;
	}

	public boolean register(VCElement vc) {
		toRegister.add(vc);
		if( canRegisterControllers ) this.register();
		return true;
	}

	public Collection<String> views(){ return view.keys(); }
	public Collection<String> controllers(){ return controller.keys(); }

	@Override
	public View view(String id) { return (View)view.get(id); }

	@Override
	public Controller controller(String id) {  return (Controller)controller.get(id); }
}