package unalcol.io;

import java.io.IOException;

import unalcol.services.Service;
import unalcol.types.collection.UnalcolIterator;

public interface Readable {
	/**
	 * Reads an object from the given reader
	 * @param reader The input stream from which the object will be read
	 * @return An object, of the type the read service is attending, that is read from the input stream
	 * @throws IOException IOException
	 */
	default Object read(UnalcolIterator<?,Integer> reader) throws IOException{ return service().read(this, reader); }
	
	default Read service(){ return Readable.service(this); }
	
	public static Read service( Object caller ){
		try{ return (Read)Service.provider(Read.class, caller); }
		catch(NoSuchMethodException e){}
		return null;
	}
	
	public static Readable cast( Object obj ){
		if( obj instanceof Readable ) return (Readable)obj;
		Read r = Readable.service(obj);
		if( r==null ) return null;
		return new Readable(){ public Read service(){ return Readable.service(obj); } };
	}
}