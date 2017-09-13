package neural;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import unalcol.clone.DefaultClone;
import unalcol.io.Read;
import unalcol.io.ShortTermMemoryReader;
import unalcol.random.raw.JavaGenerator;
import unalcol.services.Service;
import unalcol.services.ServicePool;
import unalcol.types.collection.vector.Vector;
import unalcol.types.collection.vector.VectorClone;
import unalcol.types.real.DoublePlainRead;
import unalcol.types.real.array.DoubleArrayPlainRead;
import unalcol.types.real.array.DoubleArrayPlainWrite;

public class MLPTest {
	
	public static void init_services(){
		ServicePool service = new ServicePool();
		service.register(new JavaGenerator(), Object.class);         
		service.register(new DoublePlainRead(), Double.class);
		service.register(new DoubleArrayPlainRead(108,','), double[].class);
		service.register(new DoubleArrayPlainWrite(), double[].class);
		service.register(new DefaultClone(), Object.class);
		service.register(new VectorClone<Object>(), Vector.class);
//        service.register(new ConsoleTracer(), Object.class);
		Service.set(service);
	}

	
	@SuppressWarnings("rawtypes")
	public static Vector[] readFile( String fileName ){
		try{
			ShortTermMemoryReader reader = new ShortTermMemoryReader(new FileReader(fileName));
			
			Vector<double[]> inv  = new Vector<double[]>();
			Vector<double[]> outv  = new Vector<double[]>();
			
			int c = reader.read();
			reader.back();
			while( c != -1 ){
				double[] array = (double[])Read.from(double[].class, reader);
				//Split array				
				double[] input = Arrays.copyOf(array, 72);
				double[] output = Arrays.copyOfRange(array, 72, 108);
				inv.add(input);
				outv.add(output);
				int row = reader.getRow();
				System.out.println(row+":"+array.length);
				while( c != -1 && row == reader.getRow() )  c = reader.read();
				//if( c!=-1 ) reader.back();
			}
			reader.close();      
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
