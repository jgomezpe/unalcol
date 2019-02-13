package unalcol.language.generalized;

public interface GeneralizedEncoder<T> {
    /**
     * Gets the codification of the given symbol
     * @param symbol Symbol to encode
     * @return Codification of the given symbol
     */
	public int encode(T symbol);
    /**
     * Gets the symbol encoded with number <i>code</i>.
     * @param code Code of the symbol
     * @return T Symbol encoded with number <i>code</i>.
     */
	public T decode( int code );
}
