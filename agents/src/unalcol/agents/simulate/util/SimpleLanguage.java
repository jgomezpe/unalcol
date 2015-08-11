package unalcol.agents.simulate.util;

public class SimpleLanguage implements Language{
  protected String[] percepts = null;
  protected String[] actions = null;

  public SimpleLanguage( String[] _percepts, String[] _actions ){
    percepts = _percepts;
    actions = _actions;
  }

  protected int getIndex( String[] codes_array, String code ){
    int i=0;
    while( i<codes_array.length && !codes_array[i].equals(code) ){ i++; }
    return i;
  }

  public int getActionIndex( String action ){
    return getIndex(actions, action);
  }

  public int getPerceptIndex( String percept ){
    return getIndex(percepts, percept);
  }

  public String getAction( int index ){ return actions[index]; }

  public String getPercept( int index ){ return percepts[index]; }

  public int getActionsNumber(){ return actions.length; }
  public int getPerceptsNumber(){ return percepts.length; }
}
