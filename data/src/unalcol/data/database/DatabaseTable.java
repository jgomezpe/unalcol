package unalcol.data.database;

import java.sql.*;
import java.util.Iterator;

import unalcol.types.collection.vector.Vector;
import unalcol.io.Write;
import unalcol.types.collection.Collection;

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
public class DatabaseTable implements Collection<Record>, TableAdapter{
    protected Record record_type = null;
    protected Vector<String> record_name = null;
    protected Connection connection = null;
    protected String url;
    protected String user;
    protected String password;
    protected String statement;
    protected boolean isEmpty;

    public DatabaseTable( String url, String user, String password, String statement ){
        this.url = url;
        this.user = user;
        this.password = password;
        this.statement = statement;
    }
    
    protected Iterator<Record> connect() throws Exception{
        if( connection == null ){
            connection = DriverManager.getConnection(url,user, password);
        }
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery (statement);
        if( record_type == null ){
            ResultSetMetaData metadata = rs.getMetaData();
            Vector<Object> rt = new Vector<Object>();
            record_name = new Vector<String>();
            int n = metadata.getColumnCount();
            for( int i=1; i<n; i++ ){
                // @TODO Get The JAVA Class for the given column (no the type index)
                Class<?> field_class = ClassLoader.getSystemClassLoader().loadClass(metadata.
                        getColumnClassName(i));
                rt.add( field_class );
                record_name.add(metadata.getColumnName(i));
            }
            record_type = new Record(rt);
        }
        return new DatabaseTableIterator(rs, record_type.size());
    }
    
    @Override
    public boolean isEmpty(){
        try{
            return !connect().hasNext();
        }catch( Exception e ){
            e.printStackTrace();
        }
        return true;
    }
    
    @Override
    public Iterator<Record> iterator(){
        try{
            return connect();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Record getFieldTypes(){
        if( record_type == null ){
            try{
                connect();
            }catch(Exception e){
                e.printStackTrace();
            }
        }    
        return record_type; 
    }

    public Vector<String> getFieldTypeNames(){ 
        if( record_type == null ){
            getFieldTypes();
        }

        return record_type.type(); 
    }
    
    public Vector<String> getFieldNames(){
        if( record_type == null ){
            getFieldTypes();
        }
        return record_name; 
    }


    public static void main( String[] args ){
        try{

            Class.forName("com.mysql.jdbc.Driver");
            DatabaseTable table = new DatabaseTable("jdbc:mysql://localhost/mysql",
                    "root", "e4e5f4d5nse", "select * from user");
            Record r = table.getFieldTypes();
            System.out.println( r.field.size() );
            for( int i=0; i<r.field.size(); i++){
                System.out.print(r.fieldType(i) + ":" + r.field(i)+",");
            }
            System.out.println();
            System.out.println( table.getFieldNames() );
            Iterator<Record> iter = table.iterator();
            while( iter.hasNext() ){
                r = iter.next();
                for( int i=0; i<r.field.size(); i++ ){
                    if( r.field(i) != null ){
//                        System.out.print(" " +
//                                         r.field(i).getClass().getName());
                        System.out.print(" " + Write.toString(r.field(i)));
                    }else{
                        System.out.print( " null" );
                    }
                }
                System.out.println();
                System.out.println( r.field.toString() );
            }

            /* FULL DATABASE INFORMATION */
            /* Connection conexion = DriverManager.getConnection ("jdbc:mysql://localhost/mysql","root", "e4e5f4d5");
            DatabaseMetaData dbmeta = conexion.getMetaData();
            ResultSet drs = dbmeta.getTables(null, null, null, null);
            ResultSetMetaData dbmetadata = drs.getMetaData();
            int n = dbmetadata.getColumnCount();
            for( int i=1; i<n; i++ ){
                System.out.print( dbmetadata.getColumnName(i) + ":" + dbmetadata.getColumnTypeName(i) + " ");
            }
            System.out.println();
            while( drs.next() ){
                for( int i=1; i<=n; i++ ){
                    System.out.print(drs.getObject(i) + " ");
                }
                System.out.println();
            }
            conexion.close();
            */
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}
