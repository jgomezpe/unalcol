package math;

import java.io.StringReader;

import unalcol.io.ShortTermMemoryReader;
import unalcol.types.real.array.sparse.SparseRealVector;
import unalcol.types.real.array.sparse.SparseRealVectorPlainReadService;

public class SparseRealVectorTest {
    public static void persistency(){
        StringReader r = new StringReader("  3, 5.0, 1, -1234.4555e-123, 6, 345.6789, 9, 23.456    ");
        ShortTermMemoryReader reader = new ShortTermMemoryReader(r);
        SparseRealVector x;
        SparseRealVectorPlainReadService service = new SparseRealVectorPlainReadService(10, ',');
        try{
           x = (SparseRealVector)service.read(reader);
           for( int i=0; i<x.dim(); i++ ){
               System.out.println(x.get(i));
           }
        }catch(Exception e ){
            e.printStackTrace();
        }
    }
    
    public static void main( String[] args ){
    	persistency();
    }

}
