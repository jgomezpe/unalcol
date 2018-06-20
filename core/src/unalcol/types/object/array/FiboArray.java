package unalcol.types.object.array;

public abstract class FiboArray {
	protected int a, b, c;

	protected static final int DEFAULT_C = 144;
	protected static final int DEFAULT_B = 89;
	protected static final int DEFAULT_A = 55;

	protected final void find_fib( int s ){
		a = DEFAULT_A;
		b = DEFAULT_B;
		c = DEFAULT_C;
		while(s>c){
			a=b;
			b=c;
			c=a+b;
		}
	}    

	public void clear(){
		a = DEFAULT_A;
		b = DEFAULT_B;
		c = DEFAULT_C;
		resize();
	}

	protected void grow(){
		// It requires than a > buffer.length/2
		a = b;
		b = c;
		c = a+b;
		resize();        
	}

	protected void shrink(){
		// It maintains a > buffer.length/2
		if( a >= DEFAULT_B ){
			c = b;
			b = a;
			a = c-b;
		}    
		if(size()!=c) resize();
	}

	public abstract int size();
	
	public abstract void resize();
	
	public void resize( int n ){
		int x = c;
		find_fib(n);
		if( x!=c ) resize();
	}
}
