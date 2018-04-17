package unalcol.reflect.plugin;

import unalcol.reflect.xml.XMLElement;
import unalcol.reflect.xml.XMLUtil;
import unalcol.vc.controller.ControllerInstance;
import unalcol.vc.plugin.PlugInsVCManager;
import unalcol.vc.view.ViewInstance;

public class PlugInManager{
	public static void testLoader(){
		PlugInLoader loader = new PlugInLoader();
		try{
			loader.addRepository("http://localhost/unalcol/demo/");
			for( String s : loader.modules() ){
				System.out.println("****************************");
				System.out.println(s);
				for( String t:loader.module(s)){
					System.out.println(s+"--->"+t);
					Object obj = loader.load(t, s);
					System.out.println(obj);
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

	public static void testManager(){
		PlugInsVCManager manager = new PlugInsVCManager("http://localhost/unalcol/demo/", new ControllerInstance(), new ViewInstance());
		XMLElement doc = XMLUtil.document(xml());
		manager.init(doc);
		for( String c:manager.controllers()){
			System.out.println("Controller "+c+"-->"+manager.controller(c));
		}
		for( String v:manager.views()){
			System.out.println("View "+v+"-->"+manager.view(v));
		}
	}
	
	public static void main(String[] args){
		//testLoader();
		testManager();
	}
}
