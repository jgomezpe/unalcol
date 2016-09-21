package unalcol.search.population;

import unalcol.search.Goal;
import unalcol.search.solution.Solution;

public class Population<T> extends Solution<Solution<T>[]>{
	public Population(Solution<T>[] object) {
		super(object);
	}

	public Population(Solution<T>[] object,  @SuppressWarnings("rawtypes") Goal goal ) {
		super(object, goal);
	}

	public int size(){ return object.length; }

	public Solution<T> get(int index){
		return object[index];
	}
	
	
	//@Override
	//public Population<T> instance( Solution<T>[] object ){
	//	return new Population<T>(object);
	//}
	
	@Override
	public void set( String key, Object value ){
		super.set(key, value);
		String gName = Goal.class.getName();
		if( key.equals(gName) ){
	    	for( Solution<T> x: object ) x.set(gName, value);
	    }
	}
}