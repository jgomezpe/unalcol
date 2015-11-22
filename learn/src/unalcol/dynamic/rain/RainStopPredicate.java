/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.dynamic.rain;
import unalcol.dynamic.DynamicSystem;
import unalcol.math.logic.Predicate;
/**
 *
 * @author jgomez
 */
public class RainStopPredicate extends Predicate<DynamicSystem>{
    int evaluations = 0;
    
    public RainStopPredicate(){}
    
    public RainStopPredicate( int evaluations ){
        this.evaluations = evaluations;
    }
    
    @Override    
    public boolean evaluate(DynamicSystem system) {
        RainSystem rain = (RainSystem)system;
        System.out.println(rain.point_fusions + "#" + rain.structure_fusions);
        evaluations++;
        return evaluations <= 30;
    }
    
    @Override
    public RainStopPredicate clone(){
        return new RainStopPredicate(evaluations);
    }    
}
