package unalcol.types.collection.vector;
import unalcol.clone.Clone;

/**
 * <p>CloneService of Java Vectors.</p>
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class ImmutableVectorCloneService<T> extends Clone<ImmutableVector<T>>{
    public ImmutableVectorCloneService() {
    }

    public static Object[] toArray(ImmutableVector<?> obj){
        int size = obj.size();
        Object[] cl = new Object[size];
        for(int i=0; i<size; i++ ){
            cl[i] = Clone.get(obj.get(i));
        }
        return cl;
    }

    /**
     * Clones a Java Vector
     * @param obj The Java Vector to be cloned
     * @return A clone of the Java Vector
     */
    @SuppressWarnings("unchecked")
	@Override
    public ImmutableVector<T> clone(ImmutableVector<T> obj){    
        return new ImmutableVector<T>( (T[])toArray(obj) );
    }    
}