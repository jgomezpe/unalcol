package unalcol.evolution;
import unalcol.optimization.solution.Solution;
import unalcol.optimization.*;
import unalcol.clone.*;

/**
 * <p>Title: Individual</p>
 * <p>Description: A candidate solution obtained by an evolutionary algorithm.
 * It is compossed by two main elements: Genotype and phenotype</p>
 * <p>Copyright:    Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class Individual<G,P> extends Solution<P> implements Cloneable{
  /**
   * Chromosome of the individual
   */
  protected G genome = null;

  /**
   * Thing represented by the individual
   */
  protected P thing = null;

  /**
   * Growing function used by the individual (used for mutable individuals)
   */
  protected GrowingFunction<G,P> grow = null;

  /**
   * Constructor: Creates an individual
   * @param genome The genome of the individual
   * @param thing The thing represented by the individual
   */
  public Individual(G genome, P thing) {
      this.genome = genome;
      this.thing = thing;
  }

  /**
   * Constructor: Creates an individual
   * @param genome The genome of the individual
   * @param grow Growing function used by the individual (used for mutable individuals)
   */
  public Individual(G genome, GrowingFunction<G,P> grow) {
      this.genome = genome;
      this.grow = grow;
  }

  /**
   * Determines if the genome can represent different phenotypes if the problem allows
   * mutable individuals
   * @return <i>true</i> if the genome represents different phenotypes (mutable individuals).
   * <i>false</i> otherwise.
   */
  public boolean mutable(){ return (grow != null); }

  /**
   * Constructor: Creates a clone from the given Individual
   * @param source Individual to be cloned
   */
  public Individual(Individual<G,P> source) {
      try{
          this.value = source.value;
          genome = (G) Clone.get(source.genome);
          if( source.mutable() ){
              grow = source.grow;
          }else{
              thing = (P) Clone.get(source.thing);
          }
      }catch( Exception e ){
          e.printStackTrace();
      }
  }

  /**
   * Creates a clone
   * @return a clone of the individual
   */
  public Object clone() {
    return new Individual(this);
  }

  /**
   * Gets the thing represented by the individual
   * @return Thing represented by the individual
   */
  public P get(){
      if( mutable() ){
          return grow.get(genome);
      }else{
          return thing;
      }
  }

  /**
   * Returns the genome of the individual
   * @return Individual's genome
   */
  public G genome() { return genome; }

  /**
   * Return an individual converted in a string
   * @return A String
   */
  public String toString() {
    if (thing != null) {
        if( thing instanceof double[] ){
            double[] x = (double[])thing;
            StringBuffer sb = new StringBuffer();
            sb.append("[");
            for( int i=0; i<x.length-1; i++){
                sb.append(x[i]+",");
            }
            if( x.length > 0 ){ sb.append(x[x.length-1]); }
            sb.append("]");
            return sb.toString();
        }else{
            return thing.toString();
        }
    } else { return genome.toString(); }
  }


  /**
   * Determines if the genotype represents a valid individual
   * @return true if the genotype represents a valid individual, false in other case
   */
  public boolean isFeasible(){ return true; }
}