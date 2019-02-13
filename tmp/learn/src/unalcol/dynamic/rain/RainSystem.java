/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.dynamic.rain;

import java.util.Iterator;
import unalcol.dynamic.DynamicSystem;
import unalcol.clone.Clone;
import unalcol.random.util.Shuffle;
import unalcol.types.collection.array.ArrayCollection;
import unalcol.types.collection.misc.DisjointSets;
import unalcol.types.collection.vector.Vector;

/**
 *
 * @author jgomez
 */
public class RainSystem<T> extends DynamicSystem {
  /**
   * An optimal disjoin union-find set structure to mantain the clusters
   * found by the gravitational algorithm
   */
  protected DisjointSets sets;
  /**
   * The particles in the dynamical system (not the original points)
   */
  protected Vector<T> points;
  protected PickOne<T> pick;
  protected RainMove<T> move;
  
  public RainSystem( ArrayCollection<T> set, RainMove<T> move, PickOne<T> pick ){
      points = new Vector();
      Iterator<T> iter = set.iterator();
      while( iter.hasNext() ){
          points.add((T)Clone.get(iter.next()));
      }
      sets = new DisjointSets(points.size());
      this.move = move;
      this.pick = pick;
  }
  
  protected int point_fusions = 0;
  protected int structure_fusions = 0;
  public void step(){
      int[] a;
      int j;
      int n = points.size();
      Shuffle s = new Shuffle();
      point_fusions = 0;
      structure_fusions = 0;
      a = s.apply(n);
      for( int i=0; i<n; i++ ){
        j = pick.apply(i, points);
        if( move.apply(points, a[i], a[j]) ){
            point_fusions++;
            System.out.println(sets.find(a[i]) + "$" + sets.find(a[j]));
            if(sets.find(a[i]) != sets.find(a[j])){
                structure_fusions++;
                sets.union(a[i], a[j]);
            }
        }
      }
  }
  
  public int getPointFusion(){
      return point_fusions;
  }
  
  public int structureFusions(){
      return structure_fusions;
  }
  
  public int[] structures(){
      return sets.get();
  }
}
