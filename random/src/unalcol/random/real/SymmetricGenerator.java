/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.random.real;

import unalcol.random.RandomGenerator;
import unalcol.random.RandomGeneratorWrapper;
import unalcol.random.util.RandBool;
import unalcol.services.AbstractMicroService;
import unalcol.services.MicroService;

//
//Unified Random generation Pack 1.0 by Jonatan Gómez-Perdomo
//https://github.com/jgomezpe/unalcol/tree/master/random/
//
/**
 *
 * @author jgomez
 */
public class SymmetricGenerator extends MicroService<Double> implements RandDouble{
	public static final String one_side="one_side";
	public static final String side="side";
	
	public AbstractMicroService<?> wrap( String id ){
		if(id.equals(one_side)){
			RandomGeneratorWrapper<Double> os = new RandomGeneratorWrapper<Double>();
			os.setCaller(0.0);
			return os;
		}

		if(id.equals(side)){
			RandBool os = new RandBool();
			os.setCaller(true);
			return os;
		}
		
		return null;
	}
	
   /**
     * Returns a random double number
     * @return A random double number
     */
    @Override
    public Double next() {
		RandomGenerator<Double> g = (RandDouble)getMicroService(one_side);
		RandBool b = (RandBool)getMicroService(side);
        return b.next()?g.next():-g.next();
    }     
    
    /*@Override
    public DoubleGenerator new_instance(){
        return new SymmetricGenerator(g.new_instance(), b.new_instance());
    }*/        
}