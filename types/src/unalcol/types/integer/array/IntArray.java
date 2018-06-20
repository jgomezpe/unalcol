package unalcol.types.integer.array;

import unalcol.random.integer.IntUniform;
import unalcol.types.collection.array.ImmutableArray;
import unalcol.types.object.array.FiboArray;

public class IntArray extends FiboArray{
	protected int[] buffer;
	protected int size;
	
	public IntArray( int[] buffer ){ this(buffer, buffer.length); }

	public IntArray( int[] buffer, int s ){
		this.buffer = buffer;
		size = s;
		find_fib(buffer.length);
	}

	public IntArray(){ this( new int[DEFAULT_C], 0 ); }

	@Override
	public void clear(){
		super.clear();
		size = 0;
	}

	/**
	 * Inserts a data element in the structure
	 * @param data Data element to be inserted
	 * @return <i>true</i> if the element could be added, <i>false</i> otherwise
	 */
	public boolean add(int data){
		if( buffer.length == size ) grow();
		buffer[size]=data;
		size++;
		return true;
	}

	protected void leftShift( int index ) throws IndexOutOfBoundsException{
		size--;
		if( index < size ) System.arraycopy(buffer, index+1, buffer, index, size-index);
		if( size < a ) shrink();
	}

	protected void rightShift( int index ) throws IndexOutOfBoundsException{
		if( buffer.length == size ) grow();
		System.arraycopy(buffer, index, buffer, index+1, buffer.length-index-1);
		size++;
	}

	public boolean set( int index, int data ) throws IndexOutOfBoundsException{
		if( 0 <= index && index < size ){
			buffer[index]=data;
			return true;
		}else{ throw new ArrayIndexOutOfBoundsException( index ); }
	}

	public boolean add( int index, int data ) throws IndexOutOfBoundsException{
		rightShift(index);
		buffer[index]=data;
		return true;
	}

	public boolean remove( int index ) throws IndexOutOfBoundsException{
		leftShift(index);
		return true;
	}

	public int size(){ return size; }

	/**
	 * Sets the size of the array
	 * @param n The new size of the array
	 */
	public void resize(){
		int[] new_buffer = new int[c];
		System.arraycopy(buffer, 0, new_buffer, 0, Math.min(size,c));
		buffer = new_buffer;		
	}  

	public int get( int index ) throws IndexOutOfBoundsException{
		if( index >= size ) throw new IndexOutOfBoundsException();
		return buffer[index];
	}
	
	public int[] toArray(){
		int[] new_buffer = new int[size];
		System.arraycopy(buffer, 0, new_buffer, 0, size);
		return new_buffer;		
	}
	
    /**
     * Transforms a Vector into int array
     * @param v vector of integers to be converted
     * @return integer array
     */
    public static int[] get(ImmutableArray<Integer> v) {
        int n = v.size();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = v.get(i);
        }
        return a;
    }

    /**
     * Reverses the given array
     * @param a Integer array to be reversed
     */
    public static void invert(int[] a) {
        int n = a.length;
        int j = n - 1;
        for (int i = 0; i < j; i++) {
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
            j--;
        }
    }
    
    public static int max(int[] a){
        int m = a[0];
        for( int i=1; i<a.length; i++ ){
            if( m < a[i] ){
                m = a[i];
            }
        }
        return m;
    }
    
    public static int min(int[] a){
        int m = a[0];
        for( int i=1; i<a.length; i++ ){
            if( m > a[i] ){
                m = a[i];
            }
        }
        return m;
    }   
    
    /**
     * Sorts (low to high) an array of integers using bubble sort
     * @param a Integer array to be sorted
     */
    public static void bubble(int[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[i]) {
                    int x = a[i];
                    a[i] = a[j];
                    a[j] = x;
                }
            }
        }
    }

    /**
     * Sorts (low to high) an array of integers using merge sort
     * @param a Integer array to be sorted
     */
    public static void merge(int[] a) {
        int n = a.length;
        if (n >= 4) {
            int nizq = n / 2;
            int nder = n - nizq;
            int[] aIzq = new int[nizq];
            int[] aDer = new int[nder];
            for (int i = 0; i < nizq; i++) {
                aIzq[i] = a[i];
            }
            for (int i = 0; i < nder; i++) {
                aDer[i] = a[nizq + i];
            }
            merge(aIzq);
            merge(aDer);
            int k = 0;
            int izq = 0;
            int der = 0;
            while (izq < nizq && der < nder) {
                if (aIzq[izq] < aDer[der]) {
                    a[k] = aIzq[izq];
                    izq++;
                } else {
                    a[k] = aDer[der];
                    der++;
                }
                k++;
            } while (izq < nizq) {
                a[k] = aIzq[izq];
                izq++;
                k++;
            } while (der < nder) {
                a[k] = aDer[der];
                der++;
                k++;
            }
        } else {
            bubble(a);
        }
    }    

    public static int[] create( int n, int value ){
    	int[] x = new int[n];
    	for( int i=0; i<n; i++ ) x[i] = value;
    	return x;
    }
    
    public static int[] random( int n, int max ){
    	IntUniform g = new IntUniform(max);
    	return g.generate(n);
    }    	
}