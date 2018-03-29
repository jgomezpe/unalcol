package unalcol.vc;

public interface View {
	public boolean register( Controller c );
	public Object execute( String command );
	default String addTimer( Controller c, int delay ){
		StringBuilder sb = new StringBuilder();
		sb.append("addTimer('");
		sb.append(c.toString());
		sb.append("',");
		sb.append(delay);
		sb.append(')');
		return sb.toString();
	}
	default String delTimer( Controller c, int delay ){
		StringBuilder sb = new StringBuilder();
		sb.append("delTimer('");
		sb.append(c.toString());
		sb.append("',");
		sb.append(delay);
		sb.append(')');
		return sb.toString();
	}
}