package unalcol.plugin;

import unalcol.collection.Array;
import unalcol.object.Thing;

public class DefaultPlugInTree extends Thing implements PlugInTree{
	protected Array<Object> children;
	
	public DefaultPlugInTree(){ this("noname"); }
	public DefaultPlugInTree( String id ){ super(id); }
	@Override
	public void setChildren(Array<Object> children){ this.children = children; }
}