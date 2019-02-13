package unalcol.collection.misc;


/**
 * <p>Title: DisjointSets</p>
 * <p>Description: This class represents a disjoint sets find-union optimal structure
 * as explained by Cormen et all in Introduction to algorithms. Mac Graw Hill, 1990.</p>
 * <p>Copyright: Copyright (c) 2002</p>
 * <p>Company: Universidad Nacional de Colombia - The University of Memphis</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public class DisjointSets {
  /**
   * The canonical object that represents the set which the element belongs to
   * p_i = x means that object i belongs to the set represented by object x
   */
  public int[] p = null;
  /**
   * The rank of the elements
   */
  public int[] rank = null;

  /**
   * Constructor: Creates a disjoint set structure of n objects. Each element
   * defines a set. p_x = x
   * @param n Number of elements
   */
  public DisjointSets( int n ) {
    p = new int[n];
    rank = new int[n];
    init();
  }

  /**
   * Initialize the disjoint find union structure. p_x = x, rank_x = 0
   */
  public void init(){
    for( int i=0; i<p.length; i++ ){
      p[i] = i;
      rank[i] = 0;
    }
  }

  /**
   * Returns the canonical object that represents the set that the object x belongs to
   * @param x Element used to determine the canonical object
   * @return the canonical object that represents the set that the object x belongs to
   */
  public int find( int x ){
    if( p[x] != x ){
      p[x] = find( p[x] );
    }
    return p[x];
  }

  /**
   * Performs the union of the set that contains element x with the set that contains
   * the element y
   * @param x The object that defines the first set to be joint
   * @param y The object that defines the second set to be joint
   * @return The canonical object of the union set.
   */
  public int union( int x, int y ){
    return link( find(x), find(y) );
  }

  /**
   * An intermediate function for ddoing the union of two sets
   * @param x The canonical object of the first set to be joint
   * @param y The canonical object of the second set to be joint
   * @return The canonical object of the union set.
   */
  protected int link( int x, int y ){
    if( x != y ){
      if( rank[x] > rank[y] ){
        int t = x;
        x = y;
        y = t;
      }else{
        if( rank[x] == rank[y] ){  rank[y]++;  }
      }
      p[x] = y;
    }
    return y;
  }

  /**
   * Returns the canonical elements for each element in the full set
   * @return The canonical elements for each element in the full set
   */
  public int[] get(){
    for( int i=0; i<p.length; i++ ){  find( p[i] ); }
    return p;
  }

  /**
   * Converts the disjoin find-union structure to a string
   * return A string with the disjoin find-union structure
   */
  public String toString(){
    StringBuffer sb = new StringBuffer();
    get();
    for( int i=0; i<p.length; i++ ){
      sb.append(p[i]);
      sb.append("\n");
    }
    return sb.toString();
  }
}