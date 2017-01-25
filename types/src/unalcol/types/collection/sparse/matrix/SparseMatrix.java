package unalcol.types.collection.sparse.matrix;

import java.util.Iterator;

import unalcol.sort.Search;
import unalcol.types.collection.Collection;
import unalcol.types.collection.sparse.vector.SparseVector;

/**
 * <p>Title: Mesparc</p>
 * <p>Description: Sparse matrix.</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class SparseMatrix<T> implements Collection<T>{
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

	public boolean isEmpty(){
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
				SparseMatrix<T> x;
				try{
					x = dimension_data[k].get(pos[k]);
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
	
	public boolean stored( int[] index ){
		boolean flag = false;
		try{  
			SparseMatrix<T> x = dimension_data[0].get( index[0] );
			int m = n-1;
			int[] sub_pos = new int[m];
			int j = 0;
			for( int i=0; i<n; i++ ){
				sub_pos[j] = index[i];
				j++;
			}
			flag = x.stored(sub_pos);
		}catch( ArrayIndexOutOfBoundsException ex ){}
		return flag;
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
					if( x.isEmpty() ){
						dimension_data[k].del(pos[k]);
					}
				}catch( ArrayIndexOutOfBoundsException ex ){
					flag = false;
				}
			}
		}else{
			flag = (data!=null );
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
	
	@SuppressWarnings("unchecked")
	public Iterator<int[]> indices(int[] order){
		return new SparseMatrixIndicesIterator((SparseMatrix<Object>)this, order);
	}

	public Iterator<SparseMatrixElement<T>> elements(int[] order){
		return new SparseMatrixElementsIterator<T>(this, order);
	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		int[] order = new int[this.n];
		for( int i=0; i<n; i++ ) order[i] = i;
		return iterator(order);
	}

	public Iterator<T> iterator( int[] order ) {
		// TODO Auto-generated method stub
		return new SparseMatrixIterator<T>(this, order);
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

}