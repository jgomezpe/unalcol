package xml;

import java.io.IOException;

import unalcol.plugin.PlugIn;
import unalcol.xml.XMLDocument;
import unalcol.xml.XMLElement;
import unalcol.xml.XMLManifest;

public class XMLTest{
	public static void from_url(){
		String url = "http://localhost/unalcol/demo/";
		String file = PlugIn.PLUGIN;
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("Loading xml file -->" + url+file);
		System.out.println("it will add the .xml to the file name");
		try {
			XMLDocument doc = new XMLDocument(url, file);
			for( Object e:doc.children() ){
				if( e instanceof XMLElement ){
					XMLElement x = (XMLElement)e;
					System.out.println("***************************");
					System.out.println(x.getTag());
					for( String a:x.attributes())
						System.out.println(a+"-->"+x.get(a));
				}	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public static XMLDocument from_file_inside_jar(){
		String url = "http://localhost/unalcol/demo/pluginA.jar";
		String file = PlugIn.PLUGIN;
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("Loading xml file -->" + url+'/'+file);
		System.out.println("it will add the .xml to the file name");
		try {
			XMLDocument doc = new XMLDocument(url, file);
			for( Object e:doc.children() ){
				if( e instanceof XMLElement ){
					XMLElement x = (XMLElement)e;
					System.out.println("***************************");
					System.out.println(x.getTag());
					for( String a:x.attributes())
						System.out.println(a+"-->"+x.get(a));
				}	
			}
			return doc;
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return null;
	}
	
	public static void manifest( XMLDocument doc ){
		System.out.println("++++++++++++++++++++MANIFEST+++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("----------------->XMLElements with tag controller");
		XMLManifest m = new XMLManifest("controller", doc);
		for( XMLElement x:m ){
			System.out.println("***************************");
			System.out.println(x.getTag());
			for( String a:x.attributes())
				System.out.println(a+"-->"+x.get(a));
		}
		System.out.println("----------------->XMLElements with tag view");
		m = new XMLManifest("view", doc);
		for( XMLElement x:m ){
			System.out.println("***************************");
			System.out.println(x.getTag());
			for( String a:x.attributes())
				System.out.println(a+"-->"+x.get(a));
		}
	}
	
	public static void main(String[] args){
		from_url();
		XMLDocument doc = from_file_inside_jar();
		if( doc != null ) manifest(doc);
	}
}
