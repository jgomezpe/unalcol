package unalcol.data;
import unalcol.data.SampledArrayCollection;
import unalcol.random.util.Partition;
import unalcol.types.collection.array.ArrayCollection;
import unalcol.types.integer.array.IntArray;


/**
 * <p>Title: PartitionedArrayCollection</p>
 * <p>Description: A class representing a partition process from a data source.
 * Useful for folding cross-validation analysis</p>
 * <p>Copyright:    Copyright (c) 2006</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez Reviewed by (Aurelio Benitez, Giovanni Cantor, Nestor Bohorquez)
 * @version 1.0
 *
 */
public class PartitionedArrayCollection<T> extends SampledArrayCollection<T> {

/**
 * The partition applied to the data set
 */
  protected Partition partition = null;

  public static int[] getIndex( Partition partition, int group, boolean useGroup ){
      int[] index;
      if (useGroup) {
        index = partition.getGroup(group);
      } else {
        index = partition.skipGroup(group);
      }
      IntArray.merge(index);
      return index;
  }

/**
 * Constructor: Creates a sampling object with marked data records
 * @param source Data source to be analized
 * @param partition Partition applied to the data set. I don't check if the partition
 * has the same size of the data set. I suppose is ok.
 * @param group The key group in the partition: For using it (testing) or discarding it (training)
 * @param useGroup If the key group is used (the iteration is on the group only)
 * or discarded (the iteration is done over the data set without including the group)
 */
  public PartitionedArrayCollection(ArrayCollection<T> source, Partition partition,
                              int group, boolean useGroup) {
    super(source, getIndex(partition, group, useGroup));
    this.partition = partition;
  }

  /**
   * Initializes the partition Source
   * @param group The key group in the partition: For using it (testing) or discarding it (training)
   * @param useGroup If the key group is used (the iteration is on the group only)
   * or discarded (the iteration is done over the data set without including the group)
   */
  public void init(int group, boolean useGroup) {
      index = getIndex(partition, group, useGroup);
  }
}
