package unalcol.math.function;

public interface RunnableFunction<S,T> extends Function<S,T>, Runnable{
	public void setInput(S in);

	public void setOutput(T out);
	
	public S input();
	
	public T output();

	/**
	 * Executes the algorithm on the given input
	 */
	public default void run(){
		start();
		setOutput(this.apply(input()));
	}
	
	/**
	 * Stops the function computation
	 */
	public default void stop(){};

	/**
	 * Stars the possibility of computing the function
	 */
	public default void start(){};

	/**
	 * Determines if the function is running or not
	 * @return <i>true</i> if the function is running, <i>false</i> otherwise
	 */
	public default boolean running(){ return true; };
	
}
