package unalcol.search.multilevel;

import unalcol.types.collection.vector.Vector;

public class CodeDecodeMap<G,P>{
	/**
	   * Generates a thing from the given genome
	   * @param genome Genome of the thing to be expressed
	   * @return A thing expressed from the genome
	   */
	  @SuppressWarnings("unchecked")
	public P decode(G genome) { return (P)genome; }

	  /**
	   * Generates a genome from the given thing
	   * @param thing A thing expressed from the genome
	   * @return Genome of the thing
	   */
	  @SuppressWarnings("unchecked")
	public G code(P thing) { return (G)thing; }

	  /**
	   * Generates a population of individuals from the given collection of genomes
	   * @param genomes Genomes used for building the population of individuals
	   * @return Population of individuals built upon the given genomes
	   */
	  public Vector<P> decode( Vector<G> genomes ){
	      G genome;
	      Vector<P> v = new Vector<P>();
	      int n = genomes.size();
	      for( int i=0; i<n; i++ ){
	          genome = genomes.get(i);
	          v.add( decode(genome) );
	      }
	      return v;
	  }

	  /**
	   * Obtains the first <i>n</i> candidate solutions genomes of the population
	   * @param n Number of candidate solutions genomes to obtain
	   * @param population Population used for obtaining the first <i>n</i> genomes
	   * @return The first <i>n</i> candidate solutions genomes of the population
	   */
	  public Vector<G> code( int n, Vector<P> population ){
	      Vector<G> genomes = new Vector<G>();
	      for( int i=0; i<n; i++ ){
	          genomes.add( code(population.get(i)) );
	      }
	      return genomes;
	  }

	  /**
	   * Obtains the first <i>n</i> candidate solutions genomes of the population
	   * @param n Number of candidate solutions genomes to obtain
	   * @param population Population used for obtaining the first <i>n</i> genomes
	   * @return The first <i>n</i> candidate solutions genomes of the population
	   */
	  public Vector<G> code( Vector<P> population ){
	      return code( population.size(), population );
	  }
}
