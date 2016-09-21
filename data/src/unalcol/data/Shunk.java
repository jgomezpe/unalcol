/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.data;

import java.util.Iterator;
import unalcol.types.collection.Collection;
import unalcol.types.collection.vector.Vector;

/**
 *
 * @author jgomez
 */
public class Shunk<T> implements Iterator<Vector<T>>{
    protected Iterator<T> iter;

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    protected int n;
    
    public Shunk( Collection<T> set, int n ){
        this.iter = set.iterator();
        this.n = n;
    }
    
    public Shunk( Iterator<T> iter, int n ){
        this.iter = iter;
        this.n = n;
    }
    
    @Override
    public boolean hasNext(){
        return iter.hasNext();
    }
    
    @Override
    public Vector<T> next(){
        Vector<T> v = new Vector<T>();
        int i=0;
        while( i<n && iter.hasNext() ){
            v.add(iter.next());
            i++;
        }
        return v;
    }
}