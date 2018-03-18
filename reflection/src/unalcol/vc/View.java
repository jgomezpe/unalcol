package unalcol.vc;

public interface View {
	public boolean register( Controller c );
	public Object execute( String command );
}
