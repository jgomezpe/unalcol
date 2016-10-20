package unalcol.types.collection.sparse.vector;

import unalcol.clone.Clone;

/**
 * <p>Title: SparseValue</p>
 * <p>Description: Stores a position of a vector where this position</p>
 * have a value different of the default value.
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class SparseElement<T>{
  /**
   * The position of a vector
   */
  protected int index;
  
  protected T obj;

  /**
   * Constructor: Creates a SparseValue with an index
   * @param index The object stored by the index
   */
  public SparseElement (int index, T obj) {
    this.index = index;
    this.obj = obj;
  }

  /**
   * To clone a SparseValue
   * @return The new SparseValue
   */
  @SuppressWarnings("unchecked")
@Override
  public SparseElement<T> clone() {
    return new SparseElement<T>(index, (T)Clone.get(obj));
  }

  /**
   * Converts the index to a string
   * @return A string with the index
   */
  public String toString() {
    return "" + index;
  }

  /**
   * Returns the index
   * @return The index
   */
  public int index() {
    return index;
  }
  
  public T value(){
      return obj;
  }
  
  public void setIndex( int index ){
      this.index = index;
  }
  
  public void setValue( T obj ){
      this.obj = obj;
  }
  
}
