package types;

import java.io.StringReader;

import unalcol.io.ShortTermMemoryReader;
import unalcol.types.real.DoublePlainRead;

public class DoubleTest {

    public static void read(){
        // Reflection
        StringReader r = new StringReader("    -123.44555e-123");
        ShortTermMemoryReader reader = new ShortTermMemoryReader(r);
        DoublePlainRead service = new DoublePlainRead();
        try{
           Double x = service.read(reader);
           System.out.println(x);
        }catch(Exception e ){
            e.printStackTrace();
        }
    }

    public static void main(String[] args ){
    	read(); // Testing read methods
    }
    
}
