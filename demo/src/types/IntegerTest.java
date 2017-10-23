package types;

import unalcol.io.CharReader;
import unalcol.types.integer.IntegerPlainRead;

public class IntegerTest {
    public static void read(){
        CharReader reader = new CharReader("    --1234,");
        IntegerPlainRead service = new IntegerPlainRead();
        try{
           Integer i = service.read(reader.unalcol());
           System.out.println(i);
        }catch(Exception e ){
            e.printStackTrace();
        }
    }
    
    public static void main( String[] args ){
    	read(); // Testing the read method
    }

}
