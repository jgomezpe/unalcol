package unalcol.gui.js.mode.server;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Vector;

import unalcol.gui.Controller;
import unalcol.gui.js.JSView;

public class ServerView extends JSView {
	public static final String pull="pull_server";

	protected HashMap<String, Controller> extraControllers = new HashMap<String,Controller>();

	protected Vector<String> commands_queue = new Vector<String>();
	
	public ServerView( String unalcol_url, String url ){
		super(unalcol_url, url);
		this.ready(); 
		this.register(new PullServerController());
	}
	
	public void register(){
		for( int i=registered; i<toRegister.size(); i++ ){
			Controller x = toRegister.get(i);
			if( x != null ){
				if( x.view() != this ) x.set(this);
				for( String s:x.id() ) extraControllers.put(s, x);
			}
		}
		super.register();
	}
		
	@Override
	public void execute(String command){ commands_queue.add(command); }
	
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
		Controller c = controller(id);
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

	@Override
	public Controller controller(String id) {
		Controller c = null;
		if(controller!=null) c = (Controller)controller.get(id);
		if(c==null)	c = extraControllers.get(id);
		return c;
	}	
}