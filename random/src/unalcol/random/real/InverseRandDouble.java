package unalcol.random.real;

import unalcol.random.InverseGenerator;
import unalcol.types.tag.Tags;

//
//Unified Random generation Pack 1.0 by Jonatan Gómez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/random/
//
/**
 * <p>Creates a double number generator from a uniform number generator (using the inverse notion)</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public abstract class InverseRandDouble extends Tags implements InverseGenerator<Double>, RandDouble {
     
    public InverseRandDouble(){}    
}