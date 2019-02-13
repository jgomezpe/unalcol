package optimization;

import unalcol.collection.Vector;
import unalcol.descriptors.WriteDescriptors;
import unalcol.exception.ParamsException;
import unalcol.json.JSON;
import unalcol.json.JSONParser;
import unalcol.optimization.OptimizationFunction;
import unalcol.optimization.binary.BinarySpace;
import unalcol.optimization.binary.mutation.BitMutation;
import unalcol.optimization.method.AdaptOperatorOptimizationFactory;
import unalcol.optimization.method.OptimizationFactory;
import unalcol.optimization.real.HyperCube;
import unalcol.optimization.real.mutation.IntensityMutation;
import unalcol.optimization.real.mutation.OneFifthRule;
import unalcol.optimization.real.mutation.PermutationPick;
import unalcol.optimization.real.mutation.PickComponents;
import unalcol.search.Search;
import unalcol.search.population.PopulationDescriptors;
import unalcol.search.space.Space;
import unalcol.search.variation.Variation_1_1;
import unalcol.services.ProvidersSet;
import unalcol.services.Service;
import unalcol.testbed.optimization.real.basic.BasicFunction;
import unalcol.tracer.Tracer;
import unalcol.object.Tagged;

public class MethodTest {
	public static final String EVALS="evals";  
	public static final String FUNCTION="f";  
	public static final String DIMENSION="dim";  
	public static final String SIGMA="sigma";
	public static final String MUTATION="mutation";
	public static final String ADAPT="adapt";
	public static final String NEUTRAL="neutral";

	public static final String METHOD="method";
	public static final String HC="hc";
	public static final String SA="sa";
	
	public static final String PACKAGE="package";  
	public static final String REAL="real";  
	public static final String BINARY="binary";  
	public static final String LSGO="lsgo";  

	protected OptimizationFunction<?> f;
	protected Space<?> space;
	protected String pack;
	protected String function;
	
	
	// ******* any ******** //
	public MethodTest(){}

	protected Object load( String className ){
	    ClassLoader classLoader = this.getClass().getClassLoader();
	    try {
			Class<?> aClass = classLoader.loadClass(className);
	        return aClass.newInstance();
	    } catch (Exception e){ e.printStackTrace(); }
		return null;
	}
	
	public OptimizationFunction<?> f( JSON json ) throws ParamsException{
		String path=null; 
		if( pack.equals(REAL) ) path = "unalcol.testbed.optimization.real.basic.";
		else if( pack.equals(LSGO) ) path = "unalcol.testbed.optimization.real.lsgo.";
		else if( pack.equals(BINARY) ) path = "unalcol.testbed.optimization.binary.";
		else ; // Add your package here
		if( pack.equals(LSGO) ){
			lsgo.Function lf = (lsgo.Function)load(path+(String)json.get(FUNCTION));
			int D = lf.getDimension();
			double minL = lf.getMin();
			double maxL = lf.getMax();
			double[] max = unalcol.real.Array.create(D, maxL);
			double[] min = unalcol.real.Array.create(D, minL);
			space = new HyperCube(min, max);					
			return	new OptimizationFunction<double[]>(){
						@Override
						public java.lang.Double compute(double[] x) { return lf.compute(x); }
					};
		}
		return (OptimizationFunction<?>)load(path+(String)json.get(FUNCTION));
	}
	
	public Space<?> space( JSON json ){
		if( pack.equals(REAL) ){
			int D = json.getInt(DIMENSION);
			BasicFunction bf = (BasicFunction)f;
			double[] max = unalcol.real.Array.create(D, bf.limit());
			double[] min = unalcol.real.Array.create(D, -bf.limit());
			return new HyperCube(min, max);			
		}else if( pack.equals(LSGO) ) return space; 
		else if( pack.equals(BINARY) ) return new BinarySpace(json.getInt(DIMENSION));
		else ; // add your pack here
		return null;
	}

	public Variation_1_1<?> mutation(JSON json) throws ParamsException{
		if( pack.equals(REAL) || pack.equals(LSGO) ){
			double sigma = json.getReal(SIGMA);
			int D = ((double[])space.pick()).length;
			unalcol.real.Rand random=(unalcol.real.Rand)load("unalcol.real."+(String)json.get(MUTATION));
	    	PickComponents pick = new PermutationPick(6); // It can be set to null if the mutation operator is applied to every component of the Tagged array
	    	return new IntensityMutation( sigma, random, pick );
		}else  if( pack.equals(BINARY) ){
			return new BitMutation();
		}else ; // Add your package here
		return null;
	}

	@SuppressWarnings("unchecked")
	public Search<?,Double> search(JSON json) throws ParamsException{
		String method = (String)json.get(METHOD);
		int MAXEVALS = json.getInt(EVALS);
		Search<?,Double> search = null;
		if( method.equals(HC)){
			 boolean adapt_operator = json.getBool(ADAPT);			
			 boolean neutral = json.getBool(NEUTRAL);			
			 if( adapt_operator ){
				 OneFifthRule adapt = new OneFifthRule(20, 0.9); // One Fifth rule for adapting the mutation parameter
				 AdaptOperatorOptimizationFactory<Object,Double> factory = new AdaptOperatorOptimizationFactory<Object,Double>();
				 search = factory.hill_climbing( (OptimizationFunction<Object>)f, (Variation_1_1<Object>)mutation(json), adapt, neutral, MAXEVALS );
			 }else{
				 OptimizationFactory<Object> factory = new OptimizationFactory<Object>();
				 search = factory.hill_climbing( (OptimizationFunction<Object>)f, (Variation_1_1<Object>)mutation(json), neutral, MAXEVALS );
			 }
		}else if( method.equals(SA)){
			 boolean adapt_operator = json.getBool(ADAPT);			
			 if( adapt_operator ){
				 OneFifthRule adapt = new OneFifthRule(20, 0.9); // One Fifth rule for adapting the mutation parameter
				 AdaptOperatorOptimizationFactory<Object,Double> factory = new AdaptOperatorOptimizationFactory<Object,Double>();
				 search = factory.simulated_annealing( (OptimizationFunction<Object>)f, (Variation_1_1<Object>)mutation(json), adapt, MAXEVALS );
			 }else{
				 OptimizationFactory<Object> factory = new OptimizationFactory<Object>();
				 search = factory.simulated_annealing( (OptimizationFunction<Object>)f, (Variation_1_1<Object>)mutation(json), MAXEVALS );
			 }
		}
		return search;
	}

	
	public String toString(OptimizationFunction<?> function, boolean keepBest){
		StringBuilder sb = new StringBuilder();
		try{
			ProvidersSet tracers = Service.providers(Tracer.class, function);
			Tracer s = (Tracer)tracers.get("VectorTracer");
			@SuppressWarnings("unchecked")
			Vector<Object[]> v = (Vector<Object[]>)s.get();
			Object[] f = (Object[])v.get(0);
			// The fitness value is located as the second element in the array (the first one is the object)
			double bf = (Double)(f[1]);
			for( int i=0; i<v.size(); i++ ){
				f = (Object[])v.get(i);
				double cf = (Double)(f[1]);
				if( !keepBest || function.order().compare(bf, cf) < 0 ) bf = cf;
				sb.append((Integer)f[0]+" "+bf+"\n");
			}
		}catch(Exception e){ e.printStackTrace(); }
		return sb.toString();
	}	
	
	// ******* any ******** //
	public Tagged<?> apply(JSON json) throws ParamsException{
		pack = (String)json.get(PACKAGE);
		function = (String)json.get(FUNCTION);
		this.f = f(json);
		this.space = space(json);

		@SuppressWarnings("unchecked")
		Search<Object,Double> search = (Search<Object,Double>)search(json);
		Tracer t = new unalcol.tracer.Vector(Math.max(1, json.getInt(EVALS)/1000));
		t.start();
		Service.register(t, search);

		// Apply the search method
        @SuppressWarnings("unchecked")
		Tagged<?> sol = search.solve((Space<Object>)space);        
		System.out.print(toString(search));	
		return sol;
	}

	public void population_service(OptimizationFunction<?> function ){
		@SuppressWarnings("rawtypes")
		PopulationDescriptors pd= new PopulationDescriptors();
		//pd.setGoal(function);
		Service.register(pd, Tagged[].class);
		Service.register(new WriteDescriptors(), Tagged[].class);
	}	
	
	public String toString(Search<Object,Double> search){
		StringBuilder sb = new StringBuilder();
		try{
			ProvidersSet tracers = Service.providers(Tracer.class, search);
			Tracer s = (Tracer)tracers.get("VectorTracer");
			@SuppressWarnings("unchecked")
			Vector<Object[]> v = (Vector<Object[]>)s.get();
			Object[] obj = (Object[])v.get(0);
			//@TODO Check how it is done with search method...
			// The fitness value is located as the second element in the array (the first one is the object)
			for( int i=0; i<v.size(); i++ ){
				obj = (Object[])v.get(i);
				@SuppressWarnings("unchecked")
				Tagged<Object> t = (Tagged<Object>)obj[1];
				double cf = (Double)t.getTag(f);
				sb.append((Integer)obj[0]+" "+cf+"\n");
			}
		}catch(Exception e){ e.printStackTrace(); }
		return sb.toString();
	}	
	
	public static void main(String[] args){
		String method = "\"method\":\"hc\"";
		String pack = "\"package\":\"real\"";
		String func = "\"f\":\"Rastrigin\"";
		String d = "\"dim\":10";
		String s = "\"sigma\":0.1";
		String m = "\"mutation\":\"FastPowerLaw\"";
		String e = "\"evals\":100";
		String jsonTxt = '{'+method+','+pack+','+func+','+d+','+m+','+s+','+e+'}'; 
		// String jsonTxt = args[0];
		JSONParser parser = new JSONParser();
		try {
			JSON json = (JSON)parser.parse(jsonTxt);
			MethodTest me = new MethodTest();
			me.apply(json);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}