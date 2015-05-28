package unalcol.types.collection.vector;
import unalcol.clone.Clone;
import unalcol.math.algebra.ScalarProduct;

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
public class VectorScalarProduct<T> implements ScalarProduct<Vector<T>> {
    protected ScalarProduct<T> lowLevelScalarProduct = null;

    public VectorScalarProduct( ScalarProduct<T> lowLevelScalarProduct ){
        this.lowLevelScalarProduct = lowLevelScalarProduct;
    }

    /**
     * Adds the object one and the object two
     * @param one The first Object
     * @param two The second Object
     */
    public Vector<T> fastMultiply( Vector<T> one, double x ){
        int n = one.size();
        T r;
        for( int i=0; i<n; i++ ){
          r = one.get(i);
          one.set(i,lowLevelScalarProduct.fastMultiply(r,x));
        }
        return one;
    }

    /**
     * Substract the object two from the object one
     * @param one The first Object
     * @param two The second Object
     */
    public Vector<T> fastDivide(Vector<T> one, double x){
        int n = one.size();
        T r = null;
        for( int i=0; i<n; i++ ){
          r = one.get(i);
          one.set(i,lowLevelScalarProduct.fastDivide(r,x));
        }
        return one;
    }

    @SuppressWarnings("unchecked")
	@Override
    public Vector<T> divide(Vector<T> one, double x) {
        return fastDivide((Vector<T>)Clone.create(one), x);
    }

    @SuppressWarnings("unchecked")
	@Override
    public Vector<T> multiply(Vector<T> one, double x) {
        return fastMultiply((Vector<T>)Clone.create(one), x);
    }    
}
