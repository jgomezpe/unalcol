package math;

import unalcol.math.statistics.Histogram;
import unalcol.random.integer.IntUniform;
import unalcol.random.raw.JavaGenerator;
import unalcol.services.Service;
import unalcol.services.ServicePool;
import unalcol.types.integer.IntegerOrder;
import unalcol.types.integer.IntegerPlainRead;
import unalcol.types.integer.array.IntArrayPlainRead;
import unalcol.types.integer.array.IntArrayPlainWrite;
import unalcol.types.real.DoublePlainRead;

public class HistogramDemo {
	public static void init_services(){
		ServicePool service = new ServicePool();
        service.register(new JavaGenerator(), Object.class);         
    	service.register(new IntegerOrder(), Integer.class);
    	service.register(new DoublePlainRead(), Double.class);
    	service.register(new IntegerPlainRead(), Integer.class);
    	service.register(new IntArrayPlainRead(), int[].class);
        service.register(new IntArrayPlainWrite(), int[].class);
//        service.register(new ConsoleTracer(), Object.class);
        Service.set(service);
	}
	
	public static void main(String[] args){
		init_services();
		IntUniform g = new IntUniform(20);
		Histogram<Integer> h = new Histogram<Integer>( new IntegerOrder());
		for( int i=0; i<100; i++ ){
			int x = g.next();
			System.out.print( x + ",");
			h.inc(x);
		}
		System.out.println();
		System.out.println(h);
	}
}