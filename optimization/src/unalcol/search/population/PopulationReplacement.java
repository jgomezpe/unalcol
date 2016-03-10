/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.population;

import unalcol.search.solution.SolutionManager;

/**
 *
 * @author Jonatan
 */
public interface PopulationReplacement<T> extends SolutionManager<T> {
    public Population<T> apply( Population<T> current, Population<T> next );
}