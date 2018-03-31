package unalcol.vc;

public interface View {
	public boolean register( Controller c );
	public Object execute( String command );
	public String addTimer( Controller c, int delay );
	public String delTimer( Controller c, int delay );
}