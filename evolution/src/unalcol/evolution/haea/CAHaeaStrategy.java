package unalcol.evolution.haea;
import unalcol.optimization.solution.Solution;
import unalcol.optimization.selection.Selection;
import unalcol.evolution.*;
import unalcol.ca.*;
import unalcol.types.collection.vector.*;

/**
 * <p>Title: CAHAEAStrategy</p>
 * <p>Description: The Cellular Automata based Hybrid Adaptive Evolutionary Algorithm Offspring Generation strategy as
 * proposed by Cantor and Gomez in , Proceedings of WCCI 2010.</p>
 * <p>Copyright:    Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public class CAHaeaStrategy<G, P> extends HaeaStrategy<G, P> {
    /**
     * CambrianExtiction population resizing mechanism.
     */
    protected CambrianExtinctionCA ca = null;

    /**
     * Constructor: Creates a CAHaea offspring generation strategy
     * @param operators Genetic operators used to evolve the solution
     * @param grow Growing function
     * @param selection Extra parent selection mechanism
     */
    public CAHaeaStrategy(HaeaOperators<G> operators, GrowingFunction<G, P> grow,
            Selection<P> selection) {
        super( operators, grow, selection );
    }

    /**
     * Gets a subpopulation thta can be used for selecting a second parent
     * @param id First parent
     * @param population Full Population
     * @return A subpopulation thta can be used for selecting a second parent
     */
    public Vector<Solution<P>> select( int id, Vector<Solution<P>> population ){
        Vector<Solution<P>> pop = new Vector();
        int[][] neighboor = ca.neighborhood(id);
        int i=0;
        while( neighboor[i][0] >= 0 ){
            pop.add(population.get(ca.id(neighboor[i][0], neighboor[i][1])));
            i++;
        }
        return pop;
    }

    /**
     * Determines if the individual can be selected as firts parent
     * @param id Individuals's id
     * @return <i>true</i> if the individual can be selected as first parent, <i>false</i> otherwise
     */
    public boolean available(int id){
        return ca.state(id);
    }


    /**
     * Generates a population of offspring individuals following cahaea rules.
     * @param population The population to be transformed
     * @param replace Replacement mechanism
     * @param f Function to be optimized
     */
    @Override
    public Vector<Solution<P>> apply(Vector<Solution<P>> population) {
        if( ca == null ){
            int rows = (int) Math.sqrt( population.size() );
            int columns = population.size() / rows;
            columns = (rows*columns < population.size())?columns+1:columns;
            ca = new CambrianExtinctionCA(rows, columns, 0.33);
        }
        ca.simulate();
        return super.apply(population);
    }

}