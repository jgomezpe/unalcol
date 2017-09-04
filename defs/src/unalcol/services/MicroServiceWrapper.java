package unalcol.services;

public interface MicroServiceWrapper<T> extends TaggedCallerNamePair<T>{
	public default Object run( Object... args ) throws Exception{ return Service.run(name(), caller(), args); }
}