package unalcol.vc;

import unalcol.types.collection.Collection;
import unalcol.types.object.Thing;

public interface Side extends Thing{
	default boolean hasChanged(){ return false; }
	default void synchronize(){}
	
	public Component component( String id );	
	public Collection<Component> components();
	
	public void setModel( Model model );
	public Model model();
}