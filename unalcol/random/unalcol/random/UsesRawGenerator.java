package unalcol.random;

import unalcol.random.raw.Generator;

public class UsesRawGenerator {
	protected unalcol.random.raw.Generator g=null;
	
	public UsesRawGenerator(){}
	
	public UsesRawGenerator( unalcol.random.raw.Generator g ){ this.g= g; }
	
	public unalcol.random.raw.Generator raw(){ return (g==null)?Generator.cast(this):g; }
	
	public void setRaw( unalcol.random.raw.Generator g ){ this.g = g; }
}