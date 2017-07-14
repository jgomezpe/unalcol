package unalcol.gui.paint;

public class Color implements Comparable<Object>{
	protected int r;
	protected int g;
	protected int b;
	protected int a;
	public Color(int R, int G, int B, int A){
		this.r = R;
		this.g = G;
		this.b = B;
		this.a = A;
	}
	
	public int red(){ return r; }
	public int green(){ return g; }
	public int blue(){ return b; }
	public int alpha(){ return a; }
	
	public int[] values(){ return new int[]{r,g,b,a}; }

	public int compare(int[] one, int[] two) {
		if( one.length!=two.length ) return one.length-two.length;
		int k=0;
		while( k<one.length && one[k]==two[k] ) k++;
		if(k==one.length) return 0;
		else return one[k]-two[k];
	}
	
	@Override
	public int compareTo(Object other){
		if( !(other instanceof Color) ) return Integer.MAX_VALUE;
		return compare(values(), ((Color)other).values());
	}	
}