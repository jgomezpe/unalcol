package unalcol.agents;

/**
 * <p>Title: Action </p>
 *
 * <p>Description: Abstract definiton of an action</p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: Universidad Nacional de Colombia</p>
 *
 * @author Jonatan Gomez
 * @version 1.0
 */
public class Action {
    /**
     * Constant indicating the die action
     */
    public static final int DIE = 0;
    /**
     * Constant indicating the abort action
     */
    public static final int ABORT = 1;
    /**
     * Constant indicating the continue agent execution
     */
    public static final int CONTINUE = 2;
    /**
     * Code of the action
     */
    protected String code;
    /**
     * Default constructor
     * @param _code The code of the action
     */
    public Action( String _code ) {
      code = _code;
    }

    /**
     * Gets the code of the action
     * @return The code of the action
     */
    public String getCode(){ return code; }

    @Override
    public Action clone(){
        return new Action(code);
    }
}
