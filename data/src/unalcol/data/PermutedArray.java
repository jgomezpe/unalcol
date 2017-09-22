package unalcol.data;

import unalcol.data.SampledArray;
import unalcol.random.util.*;
import unalcol.types.collection.array.ImmutableArray;

/**
 * <p>Title: PermutedArrayCollection</p>
 * <p>Description: A class representing a permutation process of a data source</p>
 * <p>Copyright:    Copyright (c) 2006</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez Reviewed by (Aurelio Benitez, Giovanni Cantor, Nestor Bohorquez)
 * @version 1.0
 *
 */
public class PermutedArray<T> extends SampledArray<T> {
  public static int[] getIndex( int m ){
      @SuppressWarnings("rawtypes")
      Shuffle shuffle = new Shuffle();
      return shuffle.apply(m);
  }

/**
 * Constructor: Creates a sampling object with marked data records
 * @param source Data source to be sampled
 */
  public PermutedArray(ImmutableArray<T> source) {
    super(source, getIndex(source.size()));
  }
}
