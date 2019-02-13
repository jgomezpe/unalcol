/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.real;

//
//Unified Random generation Pack 1.0 by Jonatan Gómez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/random/
//
/**
 *
 * @author jgomez
 */
public class Symmetric implements Rand {
	protected unalcol.bit.Rand side;
	protected Rand one_side;
	
	public Symmetric(){ this( new unalcol.bit.Rand(), new StdUniform()); }
	
	public Symmetric( Rand one_side ){ this( new unalcol.bit.Rand(), one_side ); }
	
	public Symmetric( unalcol.bit.Rand side, Rand one_side ){
		this.side = side;
		this.one_side = one_side;
	}
	
   /**
     * Returns a random double number
     * @return A random double number
     */
    @Override
    public Double next(){ return side.next()?one_side.next():-one_side.next(); }  
}