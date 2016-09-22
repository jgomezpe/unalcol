package unalcol.data;

import unalcol.data.SampledArrayCollection;
import unalcol.random.util.*;
import unalcol.types.collection.array.ArrayCollection;

/**
 * <p>Title: PermutedArrayCollection</p>
 * <p>Description: A class representing a permutation process of a data source</p>
 * <p>Copyright:    Copyright (c) 2006</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez Reviewed by (Aurelio Benitez, Giovanni Cantor, Nestor Bohorquez)
 * @version 1.0
 *
 */
public class PermutedArrayCollection<T> extends SampledArrayCollection<T> {
  public static int[] getIndex( int m ){
      @SuppressWarnings("rawtypes")
      Shuffle shuffle = new Shuffle();
      return shuffle.apply(m);
  }

/**
 * Constructor: Creates a sampling object with marked data records
 * @param source Data source to be sampled
 */
  public PermutedArrayCollection(ArrayCollection<T> source) {
    super(source, getIndex(source.size()));
  }
}
