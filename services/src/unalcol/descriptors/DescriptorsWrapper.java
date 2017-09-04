package unalcol.descriptors;

import unalcol.services.Service;
import unalcol.services.TaggedCallerNamePair;
import unalcol.types.tag.Tags;

public class DescriptorsWrapper<T> extends Tags implements TaggedCallerNamePair<T>, Descriptors<T>{
	@Override
	public double[] descriptors() {
		try{ return (double[])Service.run(Descriptors.name, caller() ); }catch(Exception e){}
		return null;
	}
}