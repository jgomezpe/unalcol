package unalcol.vc;

import unalcol.collection.Collection;
import unalcol.object.Named;

public interface Side extends Named{
	default boolean hasChanged(){ return false; }
	default void synchronize(){}
	
	public Component component( String id );	
	public Collection<Component> components();
	
	public void setModel( Model model );
	public Model model();
}