package unalcol.evolution;
import unalcol.optimization.generation.*;

/**
 * <p>Title: OffspringGeneration</p>
 *
 * <p>Description: Offspring generation strategy (evolutionay strategy)</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Jonatan Gomez
 * @version 1.0
 */
public abstract class OffspringGeneration<G, P> implements PopulationGeneration<P>{
    protected GrowingFunction<G,P> grow;

    public OffspringGeneration( GrowingFunction<G,P> grow ){
        //System.out.println("Offspring");        
        this.grow = grow;
    }

    public GrowingFunction<G,P> grow(){
        return this.grow;
    }

    public void setGrow( GrowingFunction<G,P> grow ){
        this.grow = grow;
    }

    public Object owner(){
        return Object.class;
    }
}
