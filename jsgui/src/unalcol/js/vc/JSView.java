package unalcol.js.vc;
import unalcol.util.ParseUtil;
import unalcol.vc.View;
public interface JSView extends View{
	default void execute( String command, Object... args ){
		StringBuilder sb = new StringBuilder();
		sb.append(command);
		sb.append('(');
		sb.append(encode(jsId()));
		for( Object obj : args ){
			sb.append(',');
			if( obj instanceof String ){
				sb.append(encode((String)obj));
			}else sb.append(obj.toString());
		}
		sb.append(')');
		execute(sb.toString());
	}
	default void execute( String js_command ){ ((JSFrontEnd)front()).execute(js_command); }
	default String jsId(){ return "JS"+id(); }
	default String encode( String txt ){ return ParseUtil.encode(txt); }
}