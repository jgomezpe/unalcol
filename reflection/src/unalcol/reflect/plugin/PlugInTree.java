package unalcol.reflect.plugin;

public class PlugInTree implements PlugIn{
	protected Object[] children = new Object[0];
	protected String[] id=new String[0];
	
	public PlugIn instance(){ return new PlugInTree(); }
	
	@Override
	public void init(PlugInDescriptor element, PlugInLoader builder){
		this.id = new String[]{element.getAttribute(PlugIn.id)};
		int n = element.children();
		children = new Object[n];
		for(int i=0; i<n; i++){
			try{
				children[i] = builder.load(element.child(i));
			}catch(Exception ex){
				PlugIn c = instance();
				c.init(element.child(i), builder);
				children[i] = c;
			}
		}
	}
	
	public int children(){ return children.length; }
	
	public Object child( int k ){ return children[k]; }

	@Override
	public String[] id(){ return id; }
	
	public Object get( String id ){
		if( is(id) ) return this;
		for( Object obj:children ) 
			if( obj instanceof PlugIn ){
				Object x = ((PlugIn)obj).get(id);
				if( x!=null ) return x;
			}
		return null; 
	}
}