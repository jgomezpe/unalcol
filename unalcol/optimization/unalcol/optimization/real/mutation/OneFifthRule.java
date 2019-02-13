/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.optimization.real.mutation;

import unalcol.search.local.AdaptSearchOperatorParameters;

/**
 *
 * @author jgomez
 */
public class OneFifthRule implements AdaptSearchOperatorParameters<Double>{
    protected int G;
    protected double alpha;
    protected int Gcount = 0;
    protected int Gstar = 0;

    public OneFifthRule( int G, double alpha ){
        this.G = G;
        this.alpha = alpha;
    }
    
    
    
    
	@Override
	public Double apply(Double sigma, double current, double next) {
		double productivity = next - current;
        if( productivity > 0.0 )  Gstar++;
        Gcount++;
        if(Gcount==G){
            //mut.
            double p = (double)Gstar/(double)G;
            if( p > 0.2 ){
                sigma /= alpha;   
            }else{
                if( p < 0.2){
                    sigma *= alpha; 
                }
            } 
            Gcount = 0;
            Gstar = 0;
        }
        return sigma;        
    }
}