package unalcol.integer;

import unalcol.random.raw.Generator;
import unalcol.search.space.Space;

public class HyperCube implements Space<int[]>{
	protected int[] min;
	protected int[] max;
	protected int[] length;
	
	public HyperCube( int n, int min, int max ) {
		this(Array.create(n ,min), Array.create(n,max));
	}
	
	public HyperCube( int[] min, int[] max ) {
        this.min = min;
        this.max = max;
        this.length = new int[min.length];
        for( int i=0; i<length.length; i++ ){
        	length[i] = max[i] - min[i] + 1;
        }
	}

	@Override
	public boolean feasible(int[] x) {
        int i=0;
        while(i<x.length && min[i] <= x[i] && x[i] <= max[i]){ i++; }
        return (i==x.length); 
	}

	@Override
	public double feasibility(int[] x) {
        double d = 0.0;
        for( int i=0; i<x.length; i++ ){
            if( x[i] < min[i] ){
                d += x[i] - min[i];
            }else{
                if( x[i] > max[i] ){
                    d += max[i] - x[i];
                }
            }
        }
        return d==0.0?1.0:d; 
	}

	@Override
	public int[] repair(int[] x) {
        x = x.clone();
        for( int i=0; i<x.length; i++ ){
            if( x[i] < min[i] ){
                x[i] = min[i];
            }else{
                if( x[i] > max[i] ){
                     x[i] = max[i];
                }
            }
        }
        return x;        
	}

	@Override
	public int[] pick() {
		Generator g = Generator.cast(this);
		int[] x = new int[min.length];
		for( int i=0; i<x.length; i++){
			x[i] = min[i] + g.integer(length[i]);
		}
		return x;
	}
}