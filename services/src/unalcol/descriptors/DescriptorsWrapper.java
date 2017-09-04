package unalcol.descriptors;

import unalcol.services.Service;
import unalcol.services.TaggedMicroService;
import unalcol.types.tag.Tags;

public class DescriptorsWrapper<T> extends Tags implements TaggedMicroService, Descriptors<T>{
	@Override
	public double[] descriptors() {
		try{ return (double[])Service.run(Descriptors.name, caller() ); }catch(Exception e){}
		return null;
	}
}