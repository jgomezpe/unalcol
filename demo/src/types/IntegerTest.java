package types;

import unalcol.io.reader.CharReader;
import unalcol.types.integer.IntegerPlainRead;

public class IntegerTest {
    public static void read(CharReader reader ){
    	System.out.println("************************");
        IntegerPlainRead service = new IntegerPlainRead();
        try{
           Integer i = service.read(reader.unalcol());
           System.out.println(i);
        }catch(Exception e ){
        	System.out.println(e.getMessage());
        }
    }
    
    public static void read(){
        read( new CharReader("-1234") ); // With a valid string
        read( new CharReader("    -1234") ); // With a valid string but extra spaces
        read( new CharReader("    -1234,") );  // With a valid string but extra spaces and a final comma (not used, left in the reader)
        read( new CharReader("    --1234,") ); // With an error (extra -)
    }    
    public static void main( String[] args ){
    	read(); // Testing the read method
    }

}
