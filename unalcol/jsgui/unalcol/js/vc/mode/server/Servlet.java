package unalcol.js.vc.mode.server;

import java.io.BufferedReader;
import java.io.IOException;

import unalcol.js.vc.JSModel;
import unalcol.vc.FrontEnd;

public interface Servlet {
	
	JSServerManager frontend();
	void set( JSServerManager frontend );
	
	default String command( BufferedReader reader ) throws IOException {
		  StringBuilder sb = new StringBuilder();
		  String line = reader.readLine();
		  while( line != null ){
			  sb.append(line);
			  line = reader.readLine();
			  if( line!=null ) sb.append('\n');
		  };
		  return sb.toString();		
	}
	
	default String doPost( BufferedReader reader ) throws IOException {
		return doPost( command(reader) );
	}
	
	default String doPost( String cmd ) throws IOException {
		Command command = new Command(cmd);
		System.out.println("[Servlet]"+command.id());
		System.out.println("[Servlet]"+command.method());
		
		if( command.id().equals("servlet") && command.method().equals("setParams") ){
			JSModel model = new JSModel((String)command.args()[0]);
			set( (JSServerManager)model.side(FrontEnd.FRONTEND) );
			cmd = "";
		}else{ cmd = frontend().javacall(command); }
		return cmd;
	}
}