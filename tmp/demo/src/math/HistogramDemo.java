package math;

import unalcol.math.statistics.Histogram;
import unalcol.random.integer.IntUniform;
import unalcol.services.Service;
import unalcol.types.integer.IntegerOrder;
import unalcol.types.integer.IntegerPlainRead;
import unalcol.types.integer.array.IntArrayPlainRead;
import unalcol.types.integer.array.IntArrayPlainWrite;
import unalcol.types.real.DoublePlainRead;

public class HistogramDemo {
	public static void init_services(){
    	Service.register(new IntegerOrder(), Integer.class);
    	Service.register(new DoublePlainRead(), Double.class);
    	Service.register(new IntegerPlainRead(), Integer.class);
    	Service.register(new IntArrayPlainRead(), int[].class);
        Service.register(new IntArrayPlainWrite(), int[].class);
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