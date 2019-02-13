package unalcol.real.array;
import java.util.Iterator;

import unalcol.collection.array.Immutable;
import unalcol.math.algebra.InvertableScale;
import unalcol.real.LinealScale01;


public class LinealScale implements InvertableScale<double[]> {
    protected double originalMin;
    protected LinealScale01[] scale;

    public LinealScale( Immutable<double[]> a ){
    	try{
    		double[] min = a.get(0).clone();
    		double[] max = min.clone();
    		double[] tmp;
    		Iterator<double[]> iter = a.iterator();
    		iter.next();
    		while( iter.hasNext()){
    			tmp = iter.next();
    			for( int j=0; j<min.length; j++ ){
    				if( tmp[j] < min[j] ){
    					min[j] = tmp[j];
    				}else{
    					if( tmp[j] > max[j] ){
    						max[j] = tmp[j];
    					}
    				}
    			}
    		}	
            scale = new LinealScale01[min.length];
            for( int i=0; i<scale.length; i++ ){
                scale[i] = new LinealScale01(min[i], max[i]);
            }
      }catch(Exception e){}      
    }
    
    public LinealScale( double[] min, double[] max ){
        scale = new LinealScale01[min.length];
        for( int i=0; i<scale.length; i++ ){
            scale[i] = new LinealScale01(min[i], max[i]);
        }
    }


    public LinealScale( double[] originalMin, double[] originalMax,
                             double[] targetMin, double[] targetMax ){
        scale = new LinealScale01[originalMin.length];
        for( int i=0; i<scale.length; i++ ){
            scale[i] = new unalcol.real.LinealScale(originalMin[i], originalMax[i], targetMin[i], targetMax[i]);
        }
    }

    @Override
    public double[] fastApply( double[] x ){
        for( int i=0; i<x.length; i++ ){
            x[i] = scale[i].apply(x[i]);
        }
        return x;
    }

    @Override
    public double[] fastInverse( double[] x ){
        for( int i=0; i<x.length; i++ ){
            x[i] = scale[i].inverse(x[i]);
        }
        return x;
    }
}