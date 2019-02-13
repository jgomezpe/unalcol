package unalcol.collection.vector;

import unalcol.collection.Vector;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class ScalarProduct<T> implements unalcol.math.algebra.ScalarProduct<Vector<T>> {
    protected unalcol.math.algebra.ScalarProduct<T> lowLevelScalarProduct = null;

    public ScalarProduct( unalcol.math.algebra.ScalarProduct<T> lowLevelScalarProduct ){
        this.lowLevelScalarProduct = lowLevelScalarProduct;
    }

    /**
     * Adds the object one and the object two
     * @param one The first Object
     * @param two The second Object
     */
    public Vector<T> fastMultiply( Vector<T> one, double x ){
    	try{
    		int n = one.size();
    		T r;
    		for( int i=0; i<n; i++ ){
    			r = one.get(i);
    			one.set(i,lowLevelScalarProduct.fastMultiply(r,x));
    		}
    	}catch(Exception e){}
        return one;
    }

    /**
     * Substract the object two from the object oClone.name,ne
     * @param one The first Object
     * @param two The second Object
     */
    public Vector<T> fastDivide(Vector<T> one, double x){
        try{
        	int n = one.size();
            T r = null;
            for( int i=0; i<n; i++ ){
              r = one.get(i);
              one.set(i,lowLevelScalarProduct.fastDivide(r,x));
            }
        }catch(Exception e){}
        return one;
    }
}