/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.evolution.util;

import unalcol.algorithm.iterative.ForLoopCondition;
import unalcol.evolution.GrowingFunction;
import unalcol.evolution.Individual;
import unalcol.evolution.IndividualInstance;
import unalcol.evolution.haea.HAEA;
import unalcol.evolution.haea.HaeaOperators;
import unalcol.evolution.haea.SimpleHaeaOperators;
import unalcol.instance.InstanceProvider;
import unalcol.instance.InstanceService;
import unalcol.io.WriteService;
import unalcol.math.logic.Predicate;
import unalcol.optimization.OptimizationFunction;
import unalcol.optimization.PopulationOptimizer;
import unalcol.optimization.iterative.IterativePopulationOptimizer;
import unalcol.optimization.operators.ArityOne;
import unalcol.optimization.operators.ArityTwo;
import unalcol.optimization.operators.Operator;
import unalcol.optimization.selection.Selection;
import unalcol.optimization.selection.Tournament;
import unalcol.optimization.solution.Solution;
import unalcol.optimization.solution.SolutionInstance;
import unalcol.optimization.transformation.Transformation;
import unalcol.reflect.service.ServiceProvider;
import unalcol.reflect.util.ReflectUtil;
import unalcol.tracer.ConsoleTracer;
import unalcol.tracer.FileTracer;
import unalcol.tracer.Tracer;
import unalcol.tracer.TracerProvider;
import unalcol.types.collection.vector.Vector;
import unalcol.types.integer.array.IntArraySimplePersistent;
import unalcol.types.real.array.DoubleArraySimpleWriteService;

/**
 *
 * @author Jonatan
 */
public class QueenHaea {
    public static void main( String[] args ){
        // Reflection
        ServiceProvider provider = ReflectUtil.getProvider("services/");
        DoubleArraySimpleWriteService key = new DoubleArraySimpleWriteService(',');
        provider.register(key);
        provider.setDefault_service(WriteService.class,double[].class,key);

        IntArraySimplePersistent iskey = new IntArraySimplePersistent(',');
        provider.register(iskey);
        provider.setDefault_service(WriteService.class,int[].class,iskey);

        
        // Search Space 
        int DIMENSION = 8;
        InstanceService ikey = new IntArrayInstance(DIMENSION);
        provider.register(ikey);
        provider.setDefault_service(InstanceService.class,int[].class,ikey);

        // Solution Space
        int[] x = new int[DIMENSION];
        Solution<int[]> solution = new Individual<>(x,x);
        GrowingFunction<int[],int[]> grow = new GrowingFunction();
        SolutionInstance skey = new IndividualInstance( grow );
        provider.register(skey);
        provider.setDefault_service(InstanceService.class,Solution.class,skey);


        // Initial population
        int POPSIZE = 100;
        Vector<Solution<int[]>> pop = InstanceProvider.get(solution, POPSIZE);

        // Function being optimized
        OptimizationFunction function = new QueenFitness(); 
        
        // Evaluating the fitness of the initial population
        Solution.evaluate((Vector)pop, function);

        ArityOne mutation = new MutationIntArray(DIMENSION);
        ArityTwo xover = new XOverIntArray();
//        ArityOne transposition = new RTransposition();       
        
        // Genetic operators
        Operator[] opers = new Operator[]{mutation, xover};            
        HaeaOperators haeaOperators = new SimpleHaeaOperators(opers);

        // Extra parent selection mechanism
        Selection selection = new Tournament(4);

        // Genetic Algorithm Transformation
        Transformation transformation = new HAEA(haeaOperators, grow, selection );

        // Evolution generations
        int MAXITER = 1000;
        Predicate condition = new ForLoopCondition(MAXITER);

        // Evolutionary algorithm (is a population optimizer)
        PopulationOptimizer ea = new IterativePopulationOptimizer(condition,
                transformation, pop);

        boolean tracing = true;
        if( tracing){
            // A console set tracer
            Tracer tracer = new ConsoleTracer(ea);
            // Adding the tracer collection to the given population optimizer (evolutionary algorithm)
            provider.register(tracer);
            tracer = new FileTracer(ea, "haea-q.txt", true);
            provider.register(tracer);
        }

        // running the population optimizer (the evolutionary algorithm)
        pop = (Vector<Solution<int[]>>)ea.apply(function);
        Tracer tracer = new ConsoleTracer(pop.get(0).get());
        System.out.println(XOverIntArray.toStringInt(pop.get(0).get()));
        TracerProvider.close(ea);
    }     
}
