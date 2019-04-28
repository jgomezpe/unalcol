package unalcol.js.vc;
import unalcol.string.Util;
import unalcol.vc.View;
public interface JSView extends View{
	default void execute( String command, Object... args ){
		StringBuilder sb = new StringBuilder();
		sb.append(command);
		sb.append('(');
		sb.append(Util.store(jsId()));
		for( Object obj : args ){
			sb.append(',');
			if( obj instanceof String ){
				sb.append(Util.store((String)obj));
			}else sb.append(obj.toString());
		}
		sb.append(')' );
		execute(sb.toString());
	}
	default void execute( String js_command ){ ((JSFrontEnd)front()).execute(js_command); }
	
	default String jsId(){ return id(); }
}