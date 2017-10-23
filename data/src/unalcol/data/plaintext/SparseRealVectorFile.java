/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.data.plaintext;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import unalcol.io.CharReader;
import unalcol.types.collection.vector.Vector;
import unalcol.types.real.array.sparse.SparseRealVector;
import unalcol.types.real.array.sparse.SparseRealVectorPlainReadService;

/**
 *
 * @author jgomez
 */
public class SparseRealVectorFile {
    public static Vector<SparseRealVector> load(String fileName, char separator) throws IOException{
        BufferedReader file = new BufferedReader(new FileReader(fileName));
        String line = file.readLine();
        String[] dimensions = line.split(""+separator);
        int n = Integer.parseInt(dimensions[0]);
        int d = Integer.parseInt(dimensions[1]);
        SparseRealVectorPlainReadService read = new SparseRealVectorPlainReadService(d, separator);
        Vector<SparseRealVector> data_points = new Vector<SparseRealVector>();
        //System.out.println(" " + n + "," + d );
        CharReader reader;
        for( int i=0; i<n; i++ ){
            reader = new CharReader( file.readLine() );
            data_points.add(read.read(reader.unalcol()));
        }
        file.close();
        return data_points;
    }
    
    public static void main(String[] args){
    	// @TODO Organize this code
/*        try{
            SparseRealVectorSimpleWriteService key = new SparseRealVectorSimpleWriteService(',', false);
            provider.register(key);
            provider.setDefault_service(WriteService.class,SparseRealVector.class,key);
            String fileName = "/home/jgomez/Repository/data/misc/datasets/tr11.mat";
            Vector<SparseRealVector> v = load(fileName, ' ');
            for( int i=0; i<v.size(); i++){
                System.out.println(Persistency.toString(v.get(i)));
            }
            System.out.println(v.size());
        }catch(Exception e){
            e.printStackTrace();
        }
*/        
    }
    
}
