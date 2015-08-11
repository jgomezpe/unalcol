package unalcol.agents;

/**
 * <p>Title: Agent</p>
 *
 * <p>Description: Abstract definition of an agent (it can be run in a thread!!)</p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gomez
 * @version 1.0
 */
public class Agent implements Runnable{
    /**
     * Thread used by the agent
     */
    protected Thread thread = null;
    /**
     * Component used for visualizing the agent in the environment
     */
    protected Visualizer visualizer=null;

    /**
     * Status of the execution of the agent. It can be DIE, ABORT or CONTINUE
     */
    public int status = Action.CONTINUE;

    /**
     * Agent Program
     */
    protected AgentProgram program = null;

    /**
     * Agent Architecture
     */
    protected AgentArchitecture architecture = null;

    /**
     * Creates an agent with the given agent architecture and agent program
     * @param _architecture Agent Architecture
     * @param _program Agent Program
     */
    public Agent(AgentArchitecture _architecture, AgentProgram _program){
      program = _program;
      architecture = _architecture;
    }

    /**
     * Creates an agent with the given agent architecture, the agent program is set to null
     * @param _architecture Agent Architecture
     */
    public Agent( AgentArchitecture _architecture ){
      architecture = _architecture;
    }

    /**
     * Creates an agent with the given program, the agent architecture is set to null
     * @param _program Agent Program
     */
    public Agent( AgentProgram _program ){
      program = _program;
    }

    /**
     * Sets the component used for visualizing the agent in the environment
     * @param _visualizer Component used for visualizing the agent in the environment
     */
    public void setVisualizer( Visualizer _visualizer ){
      visualizer = _visualizer;
    }

    /**
     * Sets the agent architecture
     * @param _architecture Agent Architecture
     */
    public void setArchitecture( AgentArchitecture _architecture ){
      architecture = _architecture;
    }

    /**
     * Sets the agent program
     * @param _program Agent Program
     */
    public void setProgram( AgentProgram _program ){
      program = _program;
    }

    /**
     * Gets the agent program
     * @return Agent Program
     */
    public AgentProgram getProgram(){ return program; }

    /**
     * Gets the agent architecture
     * @return Agent Architecture
     */
    public AgentArchitecture getArchitecture(){ return architecture; }

    /**
     * Runs the agent
     */
    public void run(){
      while( status != Action.DIE ){
        status = Action.CONTINUE;
        Percept p = architecture.sense(this);
        if( visualizer != null ){ visualizer.show(this,p); }
        Action action = program.compute(p);
        if( status != Action.ABORT ){
          architecture.act(this, action);
        }
      }
    }


    /**
     * Initializes the agent
     */
    public void init(){
      program.init();
    }

    /**
     * Kills the agent
     */
    public void die(){
      status = Action.DIE;
    }

    /**
     * Sets the agent status in CONTINUE, Ready for starting the execution
     */
    public void live(){
      status = Action.CONTINUE;
    }

    /**
     * Sets the thread used by the agent
     * @param _thread Thread used by the agent
     */
    public void setThread( Thread _thread ){
      thread = _thread;
    }

    /**
     * Sends to sleep the agent by the given amont of time
     * @param delay Amount of time (in millisecs) the agent will sleep
     */
    public void sleep( long delay ){
      try{
        Thread.sleep(delay);
      }catch(Exception e ){}
    }

}
