package unalcol.agents.simulate.util;

public class SimpleLanguage implements Language{
  protected String[] percepts = null;
  protected String[] actions = null;

  public SimpleLanguage( String[] _percepts, String[] _actions ){
    percepts = _percepts;
    actions = _actions;
  }

  public int getActionIndex( String action ){
    return Language.getIndex(actions, action);
  }

  public int getPerceptIndex( String percept ){
    return Language.getIndex(percepts, percept);
  }

  public String getAction( int index ){ return actions[index]; }

  public String getPercept( int index ){ return percepts[index]; }

  public int getActionsNumber(){ return actions.length; }
  public int getPerceptsNumber(){ return percepts.length; }
}
