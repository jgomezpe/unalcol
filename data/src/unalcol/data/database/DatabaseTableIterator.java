/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unalcol.data.database;
import unalcol.types.collection.vector.*;
import java.sql.*;
import java.util.Iterator;

/**
 *
 * @author jgomez
 */
public class DatabaseTableIterator implements Iterator<Record>{

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    protected boolean hNext;
    protected boolean nextCalled;
    protected ResultSet rs;
    protected int n;

    public DatabaseTableIterator( ResultSet rs, int n ){
        this.rs = rs;
        this.n = n;
        nextCalled = true;
    }

    public boolean hasNext(){
        try{
            if( nextCalled ){
                hNext = rs.next();
                nextCalled = false;
            }
            return hNext;
        }catch(Exception e){
            return false;
        }
    }

    @SuppressWarnings("unchecked")
	public Record next(){
        try{
            nextCalled = true;
            @SuppressWarnings("rawtypes")
			Vector v = new Vector();
            for( int i=1; i<=n; i++ ){
                v.add( rs.getObject(i) );
            }
            return new Record(v);
        }catch(Exception e){
            return null;
        }
    }
}
