package unalcol.agents;

/**
 * <p>Title: Visualizer</p>
 *
 * <p>Description: Abstract definition of a multi-agent visualizer</p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gomez
 * @version 1.0
 */
public interface Visualizer {
    /**
     * Show the state of the environment from the perspective of an agent
     * @param agent Agent that observes the environment
     * @param percept Perception received by the agent
     */
    public void show( Agent agent, Percept percept );
}
