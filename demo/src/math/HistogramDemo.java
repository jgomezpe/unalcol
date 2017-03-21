package math;

import unalcol.math.statistics.Histogram;
import unalcol.types.integer.IntegerOrder;

public class HistogramDemo {
	public static void main(String[] args){
		Histogram<Integer> h = new Histogram<Integer>( new IntegerOrder());
		for( int i=0; i<100; i++ ){
			int x = (int)(Math.random()*20);
			System.out.print( x + ",");
			h.inc(x);
		}
		System.out.println();
		System.out.println(h);
	}
}