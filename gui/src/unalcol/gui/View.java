package unalcol.gui;

public interface View {
	public Controller controller( String id );
	public GUIComponent component( String id );
	public boolean register(Controller controller);
}