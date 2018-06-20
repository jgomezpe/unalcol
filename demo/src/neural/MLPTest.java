package neural;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import unalcol.io.CharReader;
import unalcol.io.Position2D;
import unalcol.io.Readable;
import unalcol.services.Service;
import unalcol.types.collection.UnalcolIterator;
import unalcol.types.collection.vector.Vector;
import unalcol.types.collection.vector.VectorClone;
import unalcol.types.real.DoublePlainRead;
import unalcol.types.real.array.DoubleArrayPlainRead;
import unalcol.types.real.array.DoubleArrayPlainWrite;

public class MLPTest {
	
	public static void init_services(){
		Service.register(new DoublePlainRead(), Double.class);
		Service.register(new DoubleArrayPlainRead(108,','), double[].class);
		Service.register(new DoubleArrayPlainWrite(), double[].class);
		Service.register(new VectorClone<Object>(), Vector.class);
//        service.register(new ConsoleTracer(), Object.class);
	}

	
	@SuppressWarnings("rawtypes")
	public static Vector[] readFile( String fileName ){
		try{
			CharReader creader = new CharReader(new FileReader(fileName));
			UnalcolIterator<?, Integer> reader = creader.unalcol();
			Vector<double[]> inv  = new Vector<double[]>();
			Vector<double[]> outv  = new Vector<double[]>();
			
			while( reader.hasNext() ){
				double[] array = (double[])Readable.cast(double[].class).read(reader);
				//Split array				
				double[] input = Arrays.copyOf(array, 72);
				double[] output = Arrays.copyOfRange(array, 72, 108);
				inv.add(input);
				outv.add(output);
				int row = ((Position2D)reader.key()).row();
				System.out.println(row+":"+array.length);
				while( reader.hasNext() && row == ((Position2D)reader.key()).row() )  reader.next();
				//if( c!=-1 ) reader.back();
			}
			creader.close();      
			return new Vector[]{inv, outv}; 
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main( String[] args ){
    		init_services();
    		Vector[] data = readFile("72-36.csv");
    		Vector<double[]> in = data[0];
    		//Vector<double[]> out = data[1];
    		for( int i=0; i< in.size(); i++){
    			double[] x = in.get(i);
    			for( int k=0; k<x.length; k++){
    				System.out.print(x[k]+",");
    			}
    			System.out.println();
    		}
	}
}
