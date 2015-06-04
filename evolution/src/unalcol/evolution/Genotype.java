package unalcol.evolution;
import unalcol.types.collection.vector.*;
import unalcol.service.Service;

/**
 * <p>Title: Genotype</p>
 *
 * <p>Description: Abstract class representing the genotype used by the
 * evolutionary algorithm</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Jonatan Gomez
 * @version 1.0
 */
public abstract class Genotype<T> extends Service{
    /**
     * Gets a new genotype
     * @returnNew genotype
     */
    public abstract T get();

    /**
     * Size (length) of the given genome
     * @param genotype Genome to be analyzed in terms of its size
     * @return Size of the given genome
     */
    public abstract int size( T genotype );

    /**
     * Generates <i>n</i> genomes
     * @param n Number of genomes to be generated
     * @return A Vector with the <i>n</i> generated genomes
     */
    public Vector<T> build( int n ){
        Vector<T> v = new Vector<T>();
        for( int i=0; i<n; i++ ){
            v.add( get() );
        }
        return v;
    }

  public void setSize( int length ){
  }
}