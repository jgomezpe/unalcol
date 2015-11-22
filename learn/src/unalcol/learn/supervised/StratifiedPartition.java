package unalcol.learn.supervised;

import unalcol.random.util.*;

/**
 * <p>Title: AnomalyPartition</p>
 * <p>Description: A partition for anomaly classification. This object generate
 * a random partition on the samples that belongs to the negative class and
 * return only negative samples for training (skipGroup) and negative and all
 * positive samples for testing (getGroup)</p>
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: Universidad Nacional de Colombia</p>
 * @author Jonatan Gomez Reviewed by (Aurelio Benitez, Giovanni Cantor, Nestor Bohorquez)
 * @version 1.0
 */

public class StratifiedPartition extends Partition{

  /**
   * Creates an anomaly partition for a data source with two classes
   * @param source The data source to be anomaly partitioned
   * @param _m Number of groups that will define the anomaly partition
   */
  public StratifiedPartition( int[][] groups, int m, boolean shuffling ){
      Partition[] pgroups = new Partition[groups.length];
      for( int i=0; i<pgroups.length; i++ ){
          pgroups[i] = new Partition(groups[i], m);
      }
      n = 0;
      int ng;
      int[] groupsSize = new int[m];
      for( int i=0; i<m; i++){
          for( int k=0; k<pgroups.length; k++){
              ng = pgroups[k].groupSize(i);
              groupsSize[i] += ng;
              n += ng;
          }        
      }
      int[] group;
      index = new int[n];
      int pos=0;
      for( int i=0; i<m; i++){
          for( int k=0; k<pgroups.length; k++){
              group = pgroups[k].getGroup(i);
              ng = group.length;
              System.arraycopy(group, 0, index, pos, ng);
              pos += ng;
          }        
      }
      
      start = new int[m + 1];
      start[0] = 0;
      for (int i = 0; i < m; i++) {
          start[i + 1] = start[i] + groupsSize[i];
      }      
  }
}
