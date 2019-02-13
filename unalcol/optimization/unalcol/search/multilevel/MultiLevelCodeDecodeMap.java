package unalcol.search.multilevel;

public class MultiLevelCodeDecodeMap<G,I,P> extends CodeDecodeMap<G, P> {
	
	protected CodeDecodeMap<G,I> lowLevel;
	protected CodeDecodeMap<I,P> highLevel;
	
	public MultiLevelCodeDecodeMap( CodeDecodeMap<G, I> lowLevel, CodeDecodeMap<I, P> highLevel) {
		this.lowLevel = lowLevel;
		this.highLevel = highLevel;
	}
	
	/**
	   * Generates a thing from the given genome
	   * @param genome Genome of the thing to be expressed
	   * @return A thing expressed from the genome
	   */
	public P decode(G genome) { return highLevel.decode(lowLevel.decode(genome)); }

	  /**
	   * Generates a genome from the given thing
	   * @param thing A thing expressed from the genome
	   * @return Genome of the thing
	   */
	public G code(P thing) { return lowLevel.code(highLevel.code(thing)); }
}
