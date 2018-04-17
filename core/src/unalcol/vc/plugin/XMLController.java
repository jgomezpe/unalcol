package unalcol.vc.plugin;

import unalcol.reflect.xml.XMLUtil;
import unalcol.vc.controller.SimpleController;

public class XMLController extends SimpleController{
	public static final String ID = "xml"; 
	public XMLController(){ id = XMLController.ID; }
	public void load(String txt){ ((PlugInsVCManager)manager).init(XMLUtil.document(txt)); }	
}