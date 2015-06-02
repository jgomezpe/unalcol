/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.population;

/**
 *
 * @author Jonatan
 */
public interface PopulationReplacement<T> {
    public PopulationSolution<T> apply( PopulationSolution<T> current, PopulationSolution<T> next );
}
