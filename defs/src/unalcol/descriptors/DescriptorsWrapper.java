package unalcol.descriptors;

import unalcol.services.Service;
import unalcol.services.MicroService;

public class DescriptorsWrapper<T>  extends MicroService<T> implements  Descriptors<T>{
	@Override
	public double[] descriptors() {
		try{ return (double[])Service.run(Descriptors.name, caller() ); }catch(Exception e){}
		return null;
	}
}