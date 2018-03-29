package unalcol.vc;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import unalcol.reflect.plugin.PlugInLoader;
import unalcol.reflect.plugin.XMLUtil;
import unalcol.types.collection.vector.Vector;

public class SimpleController implements Controller{
	protected Vector<Controller> children = new Vector<Controller>();
	protected String[] ids;
	protected View view;
	
	public SimpleController( String id ) { this( new String[]{id} ); }
	public SimpleController( String[] ids ) { this.ids = ids; }

	@Override
	public String[] id(){ return ids; }

	@Override
	public void set(View view){
		if( this.view == null ) for( Controller c:children ) c.set(view);
		this.view=view; 
		if( view != null ) view.register(this);
	}
	
	@Override
	public void init(Element node, PlugInLoader manager ){
		children.clear();
		NodeList list = node.getChildNodes();
		int n = list.getLength();
		for(int i=0; i<n; i++){
			Element e = XMLUtil.element(list.item(i)); 
			if(e!=null){
				Controller c;
				try{
					c = (Controller)manager.load(e);
				}catch(Exception ex){
					c = new SimpleController("unalcox_"+e.getTagName());
					c.init(e, manager);
				}
				c.set(view);
				children.add(c);
			}
		}			
	}
	@Override
	public View view() { return view; }
}