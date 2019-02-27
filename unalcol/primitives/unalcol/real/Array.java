package unalcol.real;
import unalcol.collection.array.Fibo;

/**
 * <p>Set of constants and methods operating on an array of the primitive double data type</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 * 
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 *
 */
public class Array extends Fibo{
	protected double[] buffer;
	
	public Array( double[] buffer ){ this(buffer, buffer.length); }

	public Array( double[] buffer, int s ){
		this.buffer = buffer;
		size = s;
		find_fib(buffer.length);
	}

	public Array(){ this( new double[DEFAULT_C], 0 ); }

	/**
	 * Inserts a data element in the structure
	 * @param data Data element to be inserted
	 * @return <i>true</i> if the element could be added, <i>false</i> otherwise
	 */
	public boolean add(double data){
		if( buffer.length == size ) grow();
		buffer[size]=data;
		size++;
		return true;
	}

	protected void leftShift( int index ) throws IndexOutOfBoundsException{
		size--;
		if( index < size ) System.arraycopy(buffer, index+1, buffer, index, buffer.length-index-1);
		if( size < a ) shrink();
	}

	protected void rightShift( int index ) throws IndexOutOfBoundsException{
		if( buffer.length == size ) grow();
		System.arraycopy(buffer, index, buffer, index+1, buffer.length-index-1);
		size++;
	}

	public boolean set( int index, double data ) throws IndexOutOfBoundsException{
		if( 0 <= index && index < size ){
			buffer[index]=data;
			return true;
		}else{ throw new ArrayIndexOutOfBoundsException( index ); }
	}

	public boolean add( int index, double data ) throws IndexOutOfBoundsException{
		rightShift(index);
		buffer[index]=data;
		return true;
	}

	public boolean remove( int index ) throws IndexOutOfBoundsException{
		leftShift(index);
		return true;
	}

	/**
	 * Sets the size of the array
	 * @param n The new size of the array
	 */
	public void resize(){
		double[] new_buffer = new double[c];
		System.arraycopy(buffer, 0, new_buffer, 0, Math.min(size,c));
		buffer = new_buffer;		
	}  

	public double get( int index ) throws IndexOutOfBoundsException{
		if( index >= size ) throw new IndexOutOfBoundsException();
		return buffer[index];
	}
	
	public double[] toArray(){
		double[] new_buffer = new double[size];
		System.arraycopy(buffer, 0, new_buffer, 0, size);
		return new_buffer;		
	}
	
    /**
     * Creates a double array of size <i>n</i> with the same value in each compoment
     * @param n Size of the array to be created
     * @param value Value that will be copied in each position of the array
     * @return double[]
     */
    public static double[] create(int n, double value) {
    double[] x = new double[n];
        for (int i = 0; i < n; i++) {
            x[i] = value;
        }
        return x;
    }

    /**
     * Reverses the given array
     * @param a Double array to be reversed
     */
    public static void invert(double[] a) {
        int n = a.length;
        int j = n - 1;
        for (int i = 0; i < j; i++) {
            double tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
            j--;
        }
    }

    /**
     * Normalizes the array to the interval [0,1] using the sum of the values in the array as the maximum value
     * (precondition: Values in the array should be non negatives and at least one value should be different of zero
     * @param x Array to be normalized
     */
    public static void normalize( double[] x ){
    int n = x.length;
        double sum = 0.0;
        for( int i=0; i<n; i++ ){
            sum +=  x[i];
        }
        if( !Util.isZero(sum) ){
            for (int i = 0; i < n; i++) {
                x[i] /= sum;
            }
        }
    }
    

    /**
     * Sorts (low to high) an array of doubles using bubble sort
     * @param a Double array to be sorted
     */
    public static void bubble(double[] a) {
        int n = a.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[j] < a[i]) {
                    double x = a[i];
                    a[i] = a[j];
                    a[j] = x;
                }
            }
        }
    }

    /**
     * Sorts (low to high) an array of doubles using merge sort
     * @param a Double array to be sorted
     */
    public static void merge(double[] a) {
        int n = a.length;
        if (n >= 4) {
            int nizq = n / 2;
            int nder = n - nizq;
            double[] aIzq = new double[nizq];
            double[] aDer = new double[nder];
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


    /**
     * Determines if the sorted array contains the given double
     * @param sorted Array of ints (should be sorted)
     * @param x Element to be located
     * @return <i>true</i> if the double belongs to the sorted array, <i>false</i> otherwise
     */
    public static boolean contains(double[] sorted, double x) {
        return ( find(sorted, x) != -1 );
    }

    /**
     * Search for the position of the given int. The vector should be sorted
     * @param sorted Array of ints (should be sorted)
     * @param x Element to be located
     * @return The position of the given int, -1 if the given int is not in the array
     */
    public static int find( double[] sorted, double x ){
        if( sorted.length > 0 && sorted[0] < sorted[sorted.length-1] ){
            return findLow2High(sorted, x);
        }else{
            return findHigh2Low(sorted, x);
        }
    }


    /**
     * Search for the position of the given double. The vector should be sorted (low to high)
     * @param sorted Array of doubles (should be sorted)
     * @param x Element to be located
     * @return The position of the given double, -1 if the given double is not in the array
     */
    public static int findLow2High( double[] sorted, double x ){
        int pos = findRightLow2High( sorted, x );
        if( pos > 0 && x == sorted[pos-1] ){
                pos--;
        }else{
            pos = -1;
        }
        return pos;
    }

    /**
     * Search for the position of the first element in the array that is bigger
     * than the given element. The array should be sorted (low to high)
     * @param sorted Array of doubles (should be sorted)
     * @param x Element to be located
     * @return Position of the first double that is bigger than the given double
     */
    public static int findRightLow2High(double[] sorted, double x) {
      int n = sorted.length;
      if( n > 0 ){
          int a = 0;
          int b = n - 1;
          if( x < sorted[a] ){ return 0; }
          if( x >= sorted[b] ){ return n; }

          while (a + 1 < b) {
              int m = (a + b) / 2;
              if( x < sorted[m] ){ b = m; }
              else{ a = m; }
          }
          return b;
      }else{ return 0; }
    }

    /**
     * Search for the position of the given double. The vector should be sorted (high to low)
     * @param sorted Array of doubles (should be sorted)
     * @param x Element to be located
     * @return The position of the given double, -1 if the given double is not in the array
     */
    public static int findHigh2Low( double[] sorted, double x ){
        int pos = findRightHigh2Low( sorted, x );
        if( pos > 0 && x == sorted[pos-1] ){
                pos--;
        }else{
            pos = -1;
        }
        return pos;
    }

    /**
     * Search for the position of the first element in the array that is smaller
     * than the given element. The array should be sorted (high to low)
     * @param sorted Array of doubles (should be sorted)
     * @param x Element to be located
     * @return Position of the first double that is smaller than the given double
     */
    public static int findRightHigh2Low(double[] sorted, double x) {
      int n = sorted.length;
      if( n > 0 ){
          int a = 0;
          int b = n - 1;
          if( x > sorted[a] ){ return 0; }
          if( x <= sorted[b] ){ return n; }

          while (a + 1 < b) {
              int m = (a + b) / 2;
              if( x > sorted[m] ){ b = m; }
              else{ a = m; }
          }
          return b;
      }else{ return 0; }
    }

    /**
     * Search for the position of the last element in the array that is smaller
     * than the element given. The array should be sorted (low to high)
     * @param sorted Array of doubles (should be sorted)
     * @param x Element to be located
     * @return Position of the object that is smaller than the given element
     */
    public int findLeftLow2High(double[] sorted, double x) {
      int n = sorted.length;
      if( n > 0 ){
        int a = 0;
        int b = n - 1;
        if( x <= sorted[a] ){ return -1; }
        if( x > sorted[b] ){ return n-1; }

        while (a + 1 < b) {
            int m = (a + b) / 2;
            if( x <= sorted[m] ){ b = m; }
            else{ a = m; }
        }
        return a;
      }else{ return 0; }
    }

    /**
     * Search for the position of the last element in the array that is bigger
     * than the element given. The array should be sorted (high to low)
     * @param sorted Array of doubles (should be sorted)
     * @param x Element to be located
     * @return Position of the double that is smaller than the given double
     */
    public int findLeftHigh2Low(double[] sorted, double x) {
      int n = sorted.length;
      if( n > 0 ){
        int a = 0;
        int b = n - 1;
        if( x >= sorted[a] ){ return -1; }
        if( x < sorted[b] ){ return n-1; }

        while (a + 1 < b) {
            int m = (a + b) / 2;
            if( x >= sorted[m] ){ b = m; }
            else{ a = m; }
        }
        return a;
      }else{ return 0; }
    }
    
    /**
     * Obtains statistical information of the given array considering the array values as samples drawn from a population
     * @param x double[] Samples drawn from a population
     * @return Statistics information of the given data samples
     */
    public static Statistics statistics( double[] x ){
    return (new Statistics(x));
    }

    /**
     * Obtains statistical information (including median) of the given array considering the array values as samples drawn from a population
     * @param x double[] Samples drawn from a population
     * @return Statistics information of the given data samples
     */
    public static StatisticsWithMedian statistics_with_median( double[] x ){
    return (new StatisticsWithMedian(x));
    }
    
    /**
     * Casts an array of Doubles to an array of doubles
     * @param x Array of Doubles
     * @return Array of doubles
     */
    public static double[] cast( Double[] x ){
    	double[] y = new double[x.length];
    	for( int i=0; i<y.length; i++ )
    		y[i] = x[i];
    	return y;
    }

    /**
     * Casts an array of Doubles to an array of doubles
     * @param x Array of Doubles
     * @return Array of doubles
     */
    public static Double[] cast( double[] x ){
    	Double[] y = new Double[x.length];
    	for( int i=0; i<y.length; i++ )
    		y[i] = x[i];
    	return y;
    }
    
}