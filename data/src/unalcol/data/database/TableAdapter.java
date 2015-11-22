package unalcol.data.database;

import unalcol.types.collection.vector.Vector;

/**
 * <p>Title: TableAdapter</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Jonatan Gomez
 * @version 1.0
 */
public interface TableAdapter {
    public Record getFieldTypes();

    public Vector<String> getFieldTypeNames();
    public Vector<String> getFieldNames();

}
