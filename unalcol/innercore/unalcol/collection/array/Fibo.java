package unalcol.collection.array;

public abstract class Fibo {
	protected int size;
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
		size = 0;
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

	/**
	 * Sets the size of the array
	 * @param n The new size of the array
	 */
	public void resize( int n ){
		int x = c;
		find_fib(n);
		if( x!=c ) resize();
		this.size = n; 
	}

	public abstract void resize();
	
	public int size(){ return size; }
	
}
