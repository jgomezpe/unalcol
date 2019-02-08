package unalcol.js.vc.mode.server;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import unalcol.js.Util;
import unalcol.js.vc.JSFrontEnd;
import unalcol.js.vc.JSPreModel;
import unalcol.types.collection.vector.Vector;
import unalcol.vc.BackEnd;
import unalcol.vc.Controller;
import unalcol.vc.VCModel;

public class JSServerManager extends JSFrontEnd {
	public static final String pull="pull_server";
	protected PullServerController serverc = null; 
	protected Vector<String> commands_queue = new Vector<String>();
	
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
	
	public Object[] args( String arg ){
		StringBuilder sb;
		Vector<Object> parse = new Vector<Object>();
		int i=0;
		while( i<arg.length() ){
			switch( arg.charAt(i) ){
				case ',': case ' ': i++; break;
				case '"':
					sb = new StringBuilder();
					i++;
					while( arg.charAt(i) != '"' ){
						if( arg.charAt(i) == '\\' )	i++;
						sb.append(arg.charAt(i));
						i++;
					}
					parse.add( sb.toString() );
					i++;
				break;
				case '\'':
					i++;
					if( arg.charAt(i) == '\\' )	i++;
					parse.add(arg.charAt(i));
					i+=2;
				break;
				default:
					sb = new StringBuilder();
					while(i<arg.length() && arg.charAt(i)!=' ' && arg.charAt(i)!=',' ){ 
						sb.append(arg.charAt(i));
						i++; 
					}
					String number = sb.toString();
					try{ parse.add(Integer.parseInt(number)); }
					catch( NumberFormatException e ){ parse.add(Double.parseDouble(number)); }
			}
		}
		Object[] args = new Object[parse.size()];
		for( int k=0; k<args.length; k++ ) args[k] = parse.get(k); 
		return args;
	}
	
	public String setParams(Object[] args){
		String[] pars = new String[args.length];
		for( int i=0; i<pars.length; i++ ) pars[i] = (String)args[i];
		this.url = Util.value(pars, "url");
		String pack = Util.value(pars,"pack");
		if( pack==null ) pack="";
		String file = Util.value(pars,"file");
		if( file==null ) file="main.xml";
		
		JSPreModel m = JSPreModel.get(url,pack);
		BackEnd backend = m.backend();
		this.init(m.frontend());
		new VCModel(backend, this);	
		System.out.println("[JSServerManager]"+backend());
		return null;
	}
	
	public String javacall( String command ){
		System.out.println("[JSServerManager]"+command);
		int i=command.indexOf('.'); 
		String id = command.substring(0,i);
		command = command.substring(i+1);
		i=command.indexOf('(');
		String method = command.substring(0,i);
		String arg = command.substring(i+1,command.length()-1);
		Object[] args = args(arg);
		
		if( id.equals(PullServerController.SERVER) ){
			if(method.equals("setParams")){
				return setParams(args);
			}
		}

		if( serverc==null ) serverc = new PullServerController(backend());
		if( id.equals(serverc.id())) return serverc.pull();
		Controller c = backend().controller(id);
		if( c== null ) return null;
		
		@SuppressWarnings("rawtypes")
		Class[] types = new Class[args.length];
		for( int k=0; k<types.length; k++ ) types[k] = args[k].getClass();
		try{
			Object obj;
			Method m ;
			m = c.getClass().getMethod(method, types);
			obj = m.invoke(c, args);
			if( obj == null ) return "";
			return obj.toString();	
		}catch(NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e){
			e.printStackTrace();
		}	
		return null;
	}

	public String timer( Controller e, String type, int delay ){
		StringBuilder sb = new StringBuilder();
		sb.append(type);
		sb.append("Timer('");
		sb.append(e.id().replace(',', 'X'));
		sb.append("',");
		sb.append(delay);
		sb.append(')');
		System.out.println("[JSServerManager]"+sb.toString());
		return sb.toString();
	} 
	
	@Override
	public String addTimer( Controller c, int delay ){ return timer(c, "add", delay); }
	
	@Override
	public String delTimer( Controller c, int delay ){ 
		String cmd = timer(c, "del", delay);
		execute(cmd);
		return cmd;
	}		
}