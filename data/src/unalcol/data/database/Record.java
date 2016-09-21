package unalcol.data.database;
import unalcol.types.collection.vector.Vector;

/**
 * <p>Title: Record</p>
 *
 * <p>Description: A Data record</p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class Record {
    @SuppressWarnings("rawtypes")
	protected Vector field;
    public Record( @SuppressWarnings("rawtypes") Vector field ) {
        this.field = field;
    }

    public Object field( int i ){
        return field.get(i);
    }

    protected String fieldType( int i ){
        if( field.get(i) != null ){
            return field.get(i).getClass().getCanonicalName();
        }
        return null;
    }

    protected Vector<String> type(){
        Vector<String> types = new Vector<String>();
        for( int i=0; i<field.size(); i++ ){
            types.add( fieldType(i) );
        }
        return types;
    }

    public int size(){ return field.size(); }

}
