package unalcol.vc;

import org.w3c.dom.Document;

import unalcol.reflect.plugin.PlugInManager;
import unalcol.reflect.plugin.XMLUtil;

public class PlugInController extends SimpleController{
	public static final String ID="_xml";
	
	protected PlugInManager manager;
	
	public PlugInController( PlugInManager manager ){
		super(ID);
		this.manager = manager;
	}
	
	public void load(String txt){
		Document doc = XMLUtil.load(txt);
		init( XMLUtil.element(doc.getElementsByTagName("vcl").item(0)), manager );
	}		
}