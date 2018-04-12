package unalcol.gui.plugin;

import java.util.Vector;

import org.w3c.dom.Document;

import unalcol.gui.Controller;
import unalcol.gui.GUIComponent;
import unalcol.gui.View;
import unalcol.reflect.plugin.PlugInDescriptor;
import unalcol.reflect.plugin.PlugInLoader;
import unalcol.reflect.plugin.PlugInManager;
import unalcol.reflect.plugin.PlugInXMLElement;
import unalcol.reflect.plugin.XMLUtil;

public class PlugInsView implements View, Controller{
	public static final String ID="_xml";
	public static final String CONTROLLER="controller";
	public static final String GUI="gui";

	protected boolean canRegisterControllers = false;
	protected int registered = 0;
	protected Vector<Controller> toRegister = new Vector<Controller>();
	protected VCElementTree controller=null;
	protected VCElementTree gui=null;
	protected PlugInManager manager;
	
	public PlugInsView( String repository_url ){ 
		toRegister.add(this);
		this.manager = new PlugInManager(new String[]{CONTROLLER,GUI}, repository_url);
	}
	
	public void ready(){ canRegisterControllers=true; }
	
	public void load(String txt){
		Document doc = XMLUtil.load(txt);
		PlugInXMLElement e = new PlugInXMLElement(XMLUtil.element(doc.getElementsByTagName("vcl").item(0)));
		controller = new VCElementTree(CONTROLLER);
		gui = new VCElementTree(GUI);
		controller.init(e, manager);
		controller.set(this);
		gui.init(e, manager);
		gui.set(this);
	}		
	
	protected void register(){ 
		registered = toRegister.size();
		canRegisterControllers = true;
	}

	public boolean register(Controller controller) {
		toRegister.add(controller);
		if( canRegisterControllers ) this.register();
		return true;
	}


	@Override
	public GUIComponent component(String id) { return (gui!=null)?(GUIComponent)gui.get(id):null; }

	@Override
	public void init(PlugInDescriptor element, PlugInLoader manager) {}

	@Override
	public String[] id() { return new String[]{ID}; }

	@Override
	public void set(View view) {}

	@Override
	public View view(){ return this; }

	@Override
	public Controller controller(String id) { return (controller!=null)?(Controller)controller.get(id):null; }
}