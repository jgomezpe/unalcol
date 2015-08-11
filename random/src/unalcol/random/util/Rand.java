package unalcol.random.util;

import unalcol.random.raw.JavaGenerator;
import unalcol.random.raw.RawGenerator;

public class Rand {
	protected static RawGenerator raw = new JavaGenerator();
	
	public static double next(){
		return raw.next();
	}
	
	public static int nextInt( int max ){
		return raw.integer(max);
	}
	
	public static boolean nextBool( double falseProbability ){
		return raw.bool(falseProbability);
	}
	
	public static boolean nextBool( ){
		return nextBool(0.5);
	}
}
