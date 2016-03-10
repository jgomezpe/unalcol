package evolution;
import unalcol.types.collection.bitarray.BitArray;

public class Glovito {
	
	protected int[][] states;
	protected int[][] out;
	
	public Glovito( BitArray a ){
		int n =  a.size() / 6;
		int m = 2;
		states = new int[n][m];
		out = new int [n][m];
		int k = 0;
		for( int i=0; i<n; i++){
			for( int j=0; j<m; j++){
				int h = a.get(k)?2:0;
				k++;
				int l = a.get(k)?1:0;
				k++;
				int o = a.get(k)?1:0;
				k++;
				states[i][j] = h + l;
				out[i][j] = o;
			}			
		}
		
	}
	
	public int[] simulate( int[] input ){
		int s = 0;
		int[] x = new int[input.length];
		for( int i=0; i<input.length; i++ ){
			x[i] = out[s][input[i]];
			s = states[s][input[i]];
		}
		return x;		
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for( int i=0; i<states.length; i++){
			for( int j=0; j<states[i].length; j++){
				sb.append(states[i][j]);
				sb.append(':');
				sb.append(out[i][j]);
				sb.append(' ');
			}
			sb.append('\n');
		}
		return sb.toString();
		
	}
}
