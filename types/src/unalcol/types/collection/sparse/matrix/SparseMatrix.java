package unalcol.types.collection.sparse.matrix;

import unalcol.sort.Search;
import unalcol.types.collection.sparse.vector.SparseVector;
import unalcol.types.collection.vector.Vector;

/**
 * <p>Title: Mesparc</p>
 * <p>Description: Sparse matrix.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class SparseMatrix<T> {
	protected Search<SparseMatrix<T>> search = new Search<SparseMatrix<T>>();
	/**
	 * Dimension of the matrix
	 */
	protected int n;

	/**
	 * Array of list of non-zero elements of each dimension
	 */
	protected SparseVector<SparseMatrix<T>>[] dimension_data = null;
	protected T data;

	/**
	 * Class constructor
	 * @param m Number of rows
	 * @param n Number of columns
	 */
	@SuppressWarnings("unchecked")
	public SparseMatrix(int n){ 
		this.n = n;
		dimension_data = (SparseVector<SparseMatrix<T>>[])new SparseVector[n];
		for( int k=0; k<n; k++ ){
			dimension_data[k] = new SparseVector<SparseMatrix<T>>();
		}
	}
	
	public SparseMatrix( T data ){
	    this.data = data;
	    this.n = 0;
	}

	public void clear(){
	    if( n > 0 ){
		for( int k=0; k<n; k++ ){
		    SparseVector<SparseMatrix<T>> dim = dimension_data[k];
		    while( dim.size() > 0 ){
			dim.get(0).clear();
			dim.del(0);
		    }
		}
	    }else{
		data = null;
	    }
	}

public boolean empty(){
    boolean flag = true;
    if( n>0 ){
        for( int k=0; k<n && flag; k++ ){
            flag = (dimension_data[k].size()==0);
        }
    }else{
        flag = ( data == null );
    }
    return flag;
}

	public void set( int[] pos, T data ){
	    if( n > 0 ){
		int m = n-1;
		int[] sub_pos = new int[m];
		for( int k=0; k<n; k++ ){
		    int index = dimension_data[k].findIndex(pos[k]);
		    SparseMatrix<T> x;
		    try{
			x = dimension_data[k].get(index);
		    }catch( ArrayIndexOutOfBoundsException ex ){
			x = new SparseMatrix<T>( m );
			dimension_data[k].set(pos[k], x);
		    }
		    int j = 0;
		    for( int i=0; i<n; i++ ){
			if( i!=k ){
			    sub_pos[j] = pos[i];
			    j++;
			}
		    }
		    x.set(sub_pos, data );
		}
	    }else{
		this.data = data;
	    }
}

/**
 * Delete the element of this sparse matrix
 * @param row Number of row
 * @param column Number of column
 */
        public boolean del( int[] pos ){
          boolean flag=true;
          if( n > 0 ){
              int m = n-1;
              int[] sub_pos = new int[m];
              for( int k=0; k<n && flag; k++ ){
                try{  
                    SparseMatrix<T> x = dimension_data[k].get(pos[k] );
                      int j = 0;
                      for( int i=0; i<n; i++ ){
                          if( i!=k ){
                              sub_pos[j] = pos[i];
                              j++;
                          }
                      }
                      flag = x.del(sub_pos);
                      if( x.empty() ){
                          dimension_data[k].del(pos[k]);
                      }
                  }catch( ArrayIndexOutOfBoundsException ex ){
                      flag = false;
                  }
              }
          }else{
              flag = (data==null );
              data = null;
          }
          return flag;
        }

        /**
	 * Returns the  value in the position (row, column)
	 * @param row Number of row
	 * @param column Number of column
	 */
        protected T get( int[] pos, int m ) throws ArrayIndexOutOfBoundsException{
            T xdata = null;
            if( n>0 ){
        		SparseMatrix<T> sub_matrix = dimension_data[0].get( pos[m] );
        		if( sub_matrix != null )	xdata = sub_matrix.get( pos, m+1 );
        	    }else{
                xdata = data;
            }
            return xdata;
	}

        /**
         * Returns the  value in the position (row, column)
         * @param row Number of row
         * @param column Number of column
         */
        public T get(int[] pos) throws ArrayIndexOutOfBoundsException{
            return get(pos, 0);
        }

        public int[] low(){
            int[] low = new int[n];
            for( int k=0; k<n; k++ ){
        		low[k] = dimension_data[k].low();
            }
            return low;
        }

        public int[] high(){
            int[] low = new int[n];
            for( int k=0; k<n; k++ ){
        		low[k] = dimension_data[k].high();
            }
            return low;
        }

        /*
public boolean move( int k, int current, int future ){
    boolean flag = true;
    if( n>0 ){
        for( int i=0; i<n && flag; i++ ){
            if( i<k ){
              for( int j=0; j<dimension_data[i].size(); j++ ){
                  dimension_data[i].get(j).move(k-1,current,future);
              }
            }else{
                if( i==k ){
                    SparseMatrix<T> x = new SparseMatrix<T>(n - 1, current);
                    int current_pos = search.find( dimension_data[k], x, order );
                    x = new SparseMatrix<T>(n - 1, future);
                    int future_pos = search.find( dimension_data[k], x, order );
                    flag = ( current_pos >= 0 && future_pos == -1 );
                    if( flag ){
                        x = dimension_data[k].get(current_pos);
                        dimension_data[k].remove(current_pos);
                        x.index = future;
                        future_pos = search.findRight( dimension_data[k], x, order );
                        dimension_data[k].add( future_pos, x );
                    }
                }else{
                    for( int j=0; j<dimension_data[i].size(); j++ ){
                        dimension_data[i].get(j).move(k,current,future);
                    }
                }
            }
        }
    }
    return flag;
} */

/**
 * Returns an Enumeration object for traversing the matrix sequentially
 * @return an Enumeration object for traversing the matrix sequentially.
 */
/*public Enumeration<SparseMatrixElement<T>>
        elements(int[] begin, int[] end, int[] order){
    if( begin != null && end != null && order != null &&
        begin.length == end.length && end.length == order.length &&
        n == order.length ){
        return new SparseMatrixEnumeration<T>(order, begin, end, this);
    }
    return null;
}*/

/**
 * Returns an Enumeration object for traversing the matrix sequentially
 * @return an Enumeration object for traversing the matrix sequentially.
 */
/*        public Enumeration<SparseMatrixElement<T>>
                elements(int[] begin, int[] end){
          int[] order = new int[begin.length];
          for( int i=0; i<order.length; i++ ){
            order[i] = i;
          }
          return new SparseMatrixEnumeration<T>(order, begin, end, this);
        }
*/
	/**
	 * Returns an Enumeration object for traversing the matrix sequentially
	 * @return an Enumeration object for traversing the matrix sequentially.
	 */
/*	public Enumeration<SparseMatrixElement<T>> elements() {
            int[] begin = new int[n];
            int[] end = new int[n];
            int[] order = new int[n];
            for( int k=0; k<n; k++ ){
                begin[k] = 0;
                end[k] = -1;
                order[k] = k;
            }
    	    return elements(begin, end, order);
	}
*/
     public static void main( String[] args ){
         Vector<int[]> poss = new Vector<int[]>();
         int n = 3;
         SparseMatrix<Integer> matrix = new SparseMatrix<Integer>(n);
         for( int i=0; i<1000; i++ ){
             int[] pos = new int[n];
             for( int k=0; k<n; k++ ){
                 pos[k] = (int)(Math.random() * 100);
//                 System.out.print( " " + pos[k] );
 }
 poss.add(pos);
 Integer h = i;
//             System.out.println( " " + h );
 try{
     if( matrix.get( pos ) != null ){
	 System.out.println( "Already there..." );
     }
 }catch( ArrayIndexOutOfBoundsException  ex ){
     System.out.println("It is not there...");
 }
 matrix.set( pos, h );
//             System.out.println( "Recover" + matrix.get( pos ) );
//             System.out.println( "******************" );
 }
//         System.out.println( matrix.numberElements() );
//         for( int i=0; i<poss.size(); i++ ){
//             int[] pos = poss.get(i);
//             System.out.println( pos[0] + " " + pos[1] + " " + pos[2] + " " + matrix.get(pos) );
//         }
 System.out.println("+++++++++++++++++++++++++++++");
 int counter = 0;
/* Enumeration<SparseMatrixElement<Integer>> iter = matrix.elements();
 while( iter.hasMoreElements() ){
     System.out.println( iter.nextElement().toString() );
     counter++;
 }
 System.out.println( counter );
 System.out.println("+++++++++++++++++++++++++++++");

 int[] low = matrix.low();
 int[] high = matrix.high();
 for( int i=0; i<low.length; i++ ){
     System.out.println( "[" + low[i] + "," + high[i] + "]" );
 }

/* matrix.move( 0, 50, 200 );
 matrix.move( 1, 20, 300 );

 System.out.println("+++++++++++++++++++++++++++++");
 low = matrix.low_limit();
 high = matrix.high_limit();
 for( int i=0; i<low.length; i++ ){
     System.out.println( "[" + low[i] + "," + high[i] + "]" );
 }
* /

 counter = 0;
 iter = matrix.elements();
 while( iter.hasMoreElements() ){
      System.out.println( iter.nextElement().toString() );
      counter++;
 }
 System.out.println( counter );


 System.out.println("+++++++++++++++++++++++++++++");
 for( int i=0; i<poss.size(); i++ ){
     int[] pos = poss.get(i);
     if( !matrix.del(pos) ){
         System.out.println( "Already erased..." );
 }
//             System.out.println( pos[0] + " " + pos[1] + " " + pos[2] + " " + matrix.get(pos) );
 }
 System.out.println( "empty:" + matrix.empty() );
         counter = 0;
         iter = matrix.elements();
         while( iter.hasMoreElements() ){
              System.out.println( iter.nextElement().toString() );
              counter++;
         }
         System.out.println( counter );
*/
     }
}