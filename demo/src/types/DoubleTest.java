package types;

import unalcol.io.reader.CharReader;
import unalcol.types.real.DoublePlainRead;

public class DoubleTest {

    public static void read(){
        // Reflection
        CharReader reader = new CharReader("    -123.44555e-123");
        DoublePlainRead service = new DoublePlainRead();
        try{
           Double x = service.read(reader.unalcol());
           System.out.println(x);
        }catch(Exception e ){
            e.printStackTrace();
        }
    }

    public static void main(String[] args ){
    	read(); // Testing read methods
    }
    
}
