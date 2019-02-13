package unalcol.agents.simulate.util;

public interface Language {
    public static int getIndex( String[] codes_array, String code ){
	int i=0;
	while( i<codes_array.length && !codes_array[i].equals(code) ){ i++; }
	return i;
    }

  public int getActionIndex( String action );

  public int getPerceptIndex( String percept );

  public String getAction( int index );

  public String getPercept( int index );

  public int getActionsNumber();
  public int getPerceptsNumber();
}