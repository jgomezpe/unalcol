package unalcol.services;

public interface ServiceWrapper extends TaggedMicroService{
	public default Object run( Object... args ) throws Exception{ return Service.run(name(), caller(), args); }
}