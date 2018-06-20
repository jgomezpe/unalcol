/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.random.real;

import unalcol.random.util.RandBool;

//
//Unified Random generation Pack 1.0 by Jonatan Gómez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/random/
//
/**
 *
 * @author jgomez
 */
public class SymmetricGenerator implements RandDouble {
	protected RandBool side;
	protected RandDouble one_side;
	
	public SymmetricGenerator(){ this( new RandBool(), new StandardUniformGenerator()); }
	
	public SymmetricGenerator( RandDouble one_side ){ this( new RandBool(), one_side ); }
	
	public SymmetricGenerator( RandBool side, RandDouble one_side ){
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