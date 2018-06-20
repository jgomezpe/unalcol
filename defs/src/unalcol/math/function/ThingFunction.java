package unalcol.math.function;

import unalcol.types.object.dynamic.DynObj;

public interface ThingFunction<S,T> extends DynObj, RunnableFunction<S,T> {
	public static final String input="input"; 
	public static final String output="output"; 

	@Override
	default void setInput(S in){ set(input, in); }

	@Override
	default void setOutput(T out) { set(output, out); }

	@SuppressWarnings("unchecked")
	@Override
	default S input() { return (S)get(input); }

	@SuppressWarnings("unchecked")
	@Override
	default T output() { return (T)get(output); }
}