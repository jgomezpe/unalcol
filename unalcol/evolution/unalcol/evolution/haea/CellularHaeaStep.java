package unalcol.evolution.haea;
import unalcol.ca.*;
import unalcol.search.selection.Selection;
import unalcol.search.space.Space;
import unalcol.collection.Vector;
import unalcol.object.Tagged;

/**
 * <p>Title: CAHAEAStrategy</p>
 * <p>Description: The Cellular Automata based Hybrid Adaptive Evolutionary Algorithm Offspring Generation strategy as
 * proposed by Cantor and Gomez in , Proceedings of WCCI 2010.</p>
 * <p>Copyright:    Copyright (c) 2010</p>
 * @author Jonatan Gomez
 * @version 1.0
 *
 */
public class CellularHaeaStep<T> extends HaeaStep<T> {
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
    public CellularHaeaStep(int mu,  Selection<T> selection, HaeaReplacement<T> replacement) {
        super( mu, selection, replacement );
    }

    public CellularHaeaStep(int mu, Selection<T> selection, HaeaOperators<T> operators) {
    	super( mu, selection, operators);
    }
    
    /**
     * Gets a subpopulation that can be used for selecting a second parent
     * @param id First parent
     * @param population Full Population
     * @return A subpopulation that can be used for selecting a second parent
     */
    public Vector<Integer> select( int id, Tagged<T>[] population ){
        Vector<Integer> pop = new Vector<Integer>();
        int[][] neighboor = ca.neighborhood(id);
        int i=0;
        while( neighboor[i][0] >= 0 ){
            pop.add(ca.id(neighboor[i][0], neighboor[i][1])%mu);
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
	public Tagged<T>[] apply( Tagged<T>[] population, Space<T> space ){
        ca.simulate();
        return super.apply(population, space);
    }

	@Override
	public Tagged<T>[] init(Space<T> space) {
		// initializing the cellular automaton
        int rows = (int) Math.sqrt( mu );
        int columns = mu / rows;
        columns = (rows*columns < mu)?columns+1:columns;
        ca = new CambrianExtinctionCA(rows, columns, 0.33);
		return super.init(space);
	}
    
}