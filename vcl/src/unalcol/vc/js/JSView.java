package unalcol.vc.js;

import unalcol.vc.Controller;
import unalcol.vc.View;

public interface JSView extends View{
	public static String unalcol_url="http://localhost/unalcol/";
	
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
