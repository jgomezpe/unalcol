package unalcol.reflect.plugin;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import unalcol.reflect.plugin.PlugInManager;

public abstract class PlugInBuilder{
	public static final String tag="tag";
	public static final String jar="jar";
	
	protected String repository_url;
	
	protected PlugInManager manager;
	protected HashMap<String, String> tags = new HashMap<String,String>();
	protected HashMap<String, Class<?>> primitives = new HashMap<String, Class<?>>();
	protected HashMap<String, String> zeroes = new HashMap<String, String>();
	
	public PlugInBuilder(String plugin_path, String repository_url){
		this.repository_url = repository_url;
		manager = new PlugInManager(plugin_path);
		Set<String> plugins = manager.plugins();
		for( String s:plugins ) tags.put(manager.info(s, tag), s);
		this.init_primitives();
		this.init_containers();
	}
	
	public void addContainer( String tag ){ zeroes.put(tag, tag); }
	
	protected abstract void init_primitives();
	protected abstract void init_containers();
	protected abstract String main();
	
	public void install( String url ) throws IOException{ install( new URL(url) ); }
	
	public void install( URL url ) throws IOException{
		manager.install(url);
		Set<String> plugins = manager.plugins();
		for( String s:plugins ) tags.put(manager.info(s, tag), s);
	}
	
	protected PlugIn download( Element element ){
		try{
			String t = element.getTagName();
			PlugInManifest manifest = new PlugInManifest(repository_url);
			Set<String> plugins = manifest.plugins();
			for( String pl : plugins ){
				String jarFileURL = repository_url+manifest.info(pl,jar);
				PlugInManifest jarManifest = new PlugInManifest(jarFileURL);
				Set<String> plugins2 = jarManifest.plugins();
				for( String pl2 : plugins2 ){
					String iTag = jarManifest.info(pl2, tag);
					if(iTag!=null){
						install(jarFileURL);
						return reflected(reflected_class(t), element);
					}
				}
			}
		}catch (IOException e) { e.printStackTrace(); }
		return null;
	}
	
	public PlugIn[] list(Element[] list){
		int n = list.length;
		PlugIn[] item = new PlugIn[n];
		for(int i=0; i<n; i++) item[i] = build(list[i]);
		return item;
	}
	
	protected PlugIn container(Element element){
		int m=0;
		NodeList list = element.getChildNodes();
		while(m<list.getLength() && PlugIn.element(list.item(m))==null) m++;
		if(m<list.getLength()) return build(PlugIn.element(list.item(m)));
		return null; 
	}
	
	protected Element element( Node node ){
		if( node.getNodeType()==Node.ELEMENT_NODE ) return ((Element)node);
		return null;
	}
	
	protected PlugIn primitive(Class<?> primitive, Element element){
		try{
			PlugIn comp = (PlugIn)primitive.newInstance();
			comp.init(element, this);
			return comp;
		}catch (InstantiationException | IllegalAccessException e){ e.printStackTrace(); }
		return null;
	}

	protected String reflected_class(String t){
		String plugin = tags.get(t);
		if(plugin==null || plugin.length()==0 )	return null;
		return plugin;
	}
	
	protected PlugIn reflected(String plugin, Element element){
		try {
			PlugIn comp = (PlugIn)manager.newInstance(plugin);
			comp.init(element,this);
			return comp;
		}catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {}
		return null;
	}

	public PlugIn build(Element element){
		String t = element.getTagName();
		if( zeroes.get(t) != null ) return container(element);
		Class<?> primitive = primitives.get(t);
		if( primitive != null ) return primitive(primitive,element);
		String plugin = reflected_class(t);
		if( plugin!=null ) return reflected(plugin, element);
		return download(element);
	}
	
	public PlugIn build(InputStream is){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try{
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(is);
			NodeList e = doc.getElementsByTagName(main());
			return build(PlugIn.element(e.item(0)));
		}catch(ParserConfigurationException | SAXException | IOException e){ e.printStackTrace(); }
		return null;
	}

	public PlugIn build(String xml){
		try{ return build( new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8.name())) ); }
		catch(UnsupportedEncodingException e){ e.printStackTrace(); }
		return null;
	}
}