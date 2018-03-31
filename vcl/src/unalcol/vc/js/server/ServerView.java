package unalcol.vc.js.server;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Vector;

import unalcol.reflect.plugin.PlugInManager;
import unalcol.vc.Controller;
import unalcol.vc.PlugInController;
import unalcol.vc.js.JSView;

public class ServerView implements JSView {
	public static final String pull="pull_server";
	
	protected Vector<String> commands_queue = new Vector<String>();
	protected HashMap<String, Controller> controllers = new HashMap<String,Controller>();
	
	public ServerView( String url ){
		register( new PullServerController() );
		PlugInManager manager = new PlugInManager(url+"plugins/");
		manager.addRepository(JSView.unalcol_url+"plugins/");
		register( new PlugInController(manager) ); 
	}
	
	@Override
	public Object execute(String command) {
		commands_queue.add(command);
		return null;
	}

	@Override
	public boolean register(Controller c) {
		if( c.view() != this ) c.set(this);
		for( String id:c.id()){
			controllers.put(id, c);
		}
		return true;
	}
	
	protected int counter=0;
	
	public String queue(){
		if( commands_queue.size() == 0 ) return "";
		StringBuilder sb = new StringBuilder();
		for(String c:commands_queue ){
			sb.append(c);
			sb.append('\n');
		}
		commands_queue.clear();
		return sb.toString();
	}
	
	public String javacall( BufferedReader reader ) throws IOException {
		  StringBuilder sb = new StringBuilder();
		  String line = reader.readLine();
		  while( line != null ){
			  sb.append(line);
			  line = reader.readLine();
		  };
		  return javacall(sb.toString());		
	}	
	
	public String javacall( String command ){
		int i=command.indexOf('.'); 
		String id = command.substring(0,i);
		command = command.substring(i+1);
		Controller c = controllers.get(id);
		if( c== null ) return null;
		
		i=command.indexOf('(');
		String method = command.substring(0,i);
		String arg = command.substring(i+1,command.length()-1);
		try{
			Object obj;
			Method m ;
			if( arg.length() > 0 ){
				m = c.getClass().getMethod(method, new Class[]{String.class});
				obj = m.invoke(c, new Object[]{arg});
			}else{
				m  = c.getClass().getMethod(method, new Class[]{});
				obj = m.invoke(c, new Object[]{});
			}
			if( obj == null ) return "";
			return obj.toString();	
		}catch(NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e){
			e.printStackTrace();
		}	
		return null;
	}	
}