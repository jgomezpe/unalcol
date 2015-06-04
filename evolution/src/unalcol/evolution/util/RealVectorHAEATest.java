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
import unalcol.optimization.iterative.IterativePopulationOptimizer;
import unalcol.optimization.OptimizationFunction;
import unalcol.optimization.PopulationOptimizer;
import unalcol.optimization.generation.Repair;
import unalcol.optimization.operators.ArityOne;
import unalcol.optimization.operators.ArityTwo;
import unalcol.optimization.operators.Operator;
import unalcol.optimization.operators.real.*;
import unalcol.optimization.selection.Selection;
import unalcol.optimization.selection.Tournament;
import unalcol.optimization.solution.Solution;
import unalcol.optimization.solution.SolutionInstance;
import unalcol.optimization.testbed.real.*;
import unalcol.optimization.transformation.Transformation;
import unalcol.optimization.util.RealVectorScaleRepair;
import unalcol.random.real.*;
import unalcol.reflect.service.ServiceProvider;
import unalcol.reflect.util.ReflectUtil;
import unalcol.tracer.ConsoleTracer;
import unalcol.tracer.FileTracer;
import unalcol.tracer.Tracer;
import unalcol.tracer.TracerProvider;
import unalcol.types.collection.vector.Vector;
import unalcol.types.real.array.DoubleArrayInit;
import unalcol.types.real.array.DoubleArrayInstance;
import unalcol.types.real.array.DoubleArraySimpleWriteService;

/**
 *
 * @author jgomez
 */
public class RealVectorHAEATest {
    public static void main( String[] args ){
        // Reflection
        ServiceProvider provider = ReflectUtil.getProvider("services/");
        DoubleArraySimpleWriteService key = new DoubleArraySimpleWriteService(',');
        provider.register(key);
        provider.setDefault_service(WriteService.class,double[].class,key);

        // Search Space 
        int DIMENSION = 10;
        double[] min = DoubleArrayInit.create(DIMENSION, -5.12);
        double[] max = DoubleArrayInit.create(DIMENSION, 5.12);
        InstanceService ikey = new DoubleArrayInstance(min, max);
        provider.register(ikey);
        provider.setDefault_service(InstanceService.class,double[].class,ikey);
        Repair<double[]> rkey = new RealVectorScaleRepair(min, max); 
        provider.register(rkey);
        provider.setDefault_service(Repair.class,double[].class,rkey);

        // Solution Space
        double[] x = new double[DIMENSION];
        Solution<double[]> solution = new Individual<>(x,x);
        GrowingFunction<double[],double[]> grow = new GrowingFunction();
        SolutionInstance skey = new IndividualInstance( grow );
        provider.register(skey);
        provider.setDefault_service(InstanceService.class,Solution.class,skey);


        // Initial population
        int POPSIZE = 100;
        Vector<Solution<double[]>> pop = InstanceProvider.get(solution, POPSIZE);

        // Function being optimized
        OptimizationFunction function = new Rastrigin(); 
        
        // Evaluating the fitness of the initial population
        Solution.evaluate((Vector)pop, function);

        double sigma = 0.01;
        ArityOne mutation = new EllipticMutation(DIMENSION, sigma,
//                new StandardGaussianGenerator());
                    new SimplestSymmetricPowerLawGenerator());
        ArityTwo xover = new LinearXOver();
        ArityOne transposition = new RTransposition();       
        
        // Genetic operators
        Operator[] opers = new Operator[]{mutation, transposition, xover};            
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
            tracer = new FileTracer(ea, "haea-r.txt", true);
            provider.register(tracer);
        }

        // running the population optimizer (the evolutionary algorithm)
        pop = (Vector<Solution<double[]>>)ea.apply(function);
        
        TracerProvider.close(ea);
    }
}
