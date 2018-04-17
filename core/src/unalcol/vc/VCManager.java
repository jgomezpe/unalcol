package unalcol.vc;

public interface VCManager {
	public Controller controller( String id );
	public View view( String id );
	public boolean register(VCElement vc);
}