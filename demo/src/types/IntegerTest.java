package types;

import java.io.StringReader;

import unalcol.io.RowColumnReaderException;
import unalcol.io.ShortTermMemoryReader;
import unalcol.types.integer.IntegerPlainRead;

public class IntegerTest {
    public static void read(){
        StringReader r = new StringReader("    --1234,");
        ShortTermMemoryReader reader = new ShortTermMemoryReader(r);
        IntegerPlainRead service = new IntegerPlainRead();
        try{
           Integer i = service.read(reader);
           System.out.println(i);
        }catch(Exception e ){
            System.out.println( ((RowColumnReaderException)e).getColumn() );
            e.printStackTrace();
        }
    }
    
    public static void main( String[] args ){
    	read(); // Testing the read method
    }

}
