package unalcol.types.object.basic;

import unalcol.types.object.Thing;

public class BasicThing implements Thing{
	public String id;
	
	public BasicThing( String id ){ this.id = id; }
	
	@Override
	public String id(){ return id; }

	@Override
	public void setId(String id){ this.id = id; }
}