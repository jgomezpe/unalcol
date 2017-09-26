package types;

import unalcol.io.CharReader;
import unalcol.io.RowColumnReaderException;
import unalcol.io.ShortTermMemoryReader;
import unalcol.types.integer.IntegerPlainRead;

public class IntegerTest {
    public static void read(){
        ShortTermMemoryReader reader = new CharReader("    --1234,");
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
