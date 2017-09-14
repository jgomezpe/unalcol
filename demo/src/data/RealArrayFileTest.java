package data;

import unalcol.clone.DefaultClone;
import unalcol.data.plaintext.RealVectorFile;
import unalcol.io.Write;
import unalcol.random.raw.JavaGenerator;
import unalcol.services.Service;
import unalcol.services.ServicePool;
import unalcol.tracer.ConsoleTracer;
import unalcol.tracer.Tracer;
import unalcol.types.collection.vector.Vector;
import unalcol.types.real.array.DoubleArrayPlainRead;
import unalcol.types.real.array.DoubleArrayPlainWrite;

public class RealArrayFileTest {
	public static void init_services(){
		// Tracking the goal evaluations
		ServicePool service = new ServicePool();
		service.register(new JavaGenerator(), Object.class);      
		service.register(new DefaultClone(), Object.class);
		Tracer<Object> t = new ConsoleTracer<Object>();
		t.start();
		service.register(new DoubleArrayPlainWrite(',',false), double[].class);
		service.register(new DoubleArrayPlainRead(','), double[].class);
		Service.set(service);
	}

	public static void main(String[] args){
		init_services();
		try{
			String fileName = "/home/jgomez/Repository/data/chameleon/t7.10k.dat";
			Vector<double[]> v = RealVectorFile.load(fileName, ' ');
			for( int i=0; i<v.size(); i++){
				System.out.println(Write.toString(v.get(i)));
			}
			System.out.println(v.size());
		}catch(Exception e){ e.printStackTrace(); }
	}
}