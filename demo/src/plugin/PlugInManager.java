package plugin;

import unalcol.xml.XMLElement;
import unalcol.xml.XMLElementSet;
import unalcol.xml.XMLFilter;
import unalcol.xml.XMLUtil;
import unalcol.reflect.Loader;
import unalcol.vc.BackEnd;
import unalcol.vc.FrontEnd;
import unalcol.vc.Side;
import unalcol.vc.plugin.PlugInVCModel;

public class PlugInManager{
	public static void testLoader(){
		Loader loader = new Loader("http://localhost/unalcol/demo/");
		try{
			XMLFilter filter = loader.filter(XMLElement.TAG);
			for( String s : filter.modules() ){
				System.out.println("****************************");
				System.out.println("Some reflected classes provide the service of "+s);
				XMLElementSet set = filter.module(s);
				for( String t:set.keys()){					
					System.out.println("+++++++++++++++++++++++++++++++");
					System.out.println("The instance "+t+ " provides the service of "+s);
					Object obj = loader.load(set.get(t));
					System.out.println("This is the toString for the instance "+t+" of "+ obj.getClass() + " that provides the service of "+s+ ": "+obj);
				}
			}
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	public static String xml(){
		return "<vcl>\n" +
				"  <editor id='A'/>\n" +
				"  <toolbar id='B'/>\n" +
				"  <button id='C'/>\n" +
				"  <panel id='D'/>\n" +
				"</vcl>";
	}

	public static void testVCModel(){
		System.out.println("***********Testing manager****************");
		String url = "http://localhost/unalcol/demo/";
		Loader loader = new Loader(url);
		//String url2 = "http://localhost/demo/xml/";
		PlugInVCModel model = new PlugInVCModel(loader);
		Side backend = model.side(BackEnd.BACKEND);
		Side frontend = model.side(FrontEnd.FRONTEND);
		XMLElement doc = XMLUtil.document(xml());
		model.init(doc);
		for( Object obj : doc.children() ){
			XMLElement e = (XMLElement)obj;
			String id = null; // (String)e.get(NamedObject.ID);
			System.out.println("++++++++++++++++++++++++++++++++++++++++");
			System.out.println(e.getTag()+"-->"+id);
			System.out.println("Controller "+id+"-->"+backend.component(id));
			System.out.println("View "+id+"-->"+frontend.component(id));
		}
	}

	public static void main(String[] args){
		testLoader();
	//	testVCModel();
	}
}
