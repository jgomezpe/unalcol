package unalcol.js.vc.mode.server;

import unalcol.collection.Vector;

public class Command {
	protected String id;
	protected String method;
	protected Object[] args;
	
	public Command( String command ){
		int i=command.indexOf('.'); 
		id = command.substring(0,i);
		command = command.substring(i+1);
		i=command.indexOf('(');
		method = command.substring(0,i);
		args = args(command.substring(i+1,command.length()-1));
	}
	
	protected Object[] args( String arg ){
		System.out.println("[Command]"+arg);
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
		args = new Object[parse.size()];
		try{ for( int k=0; k<args.length; k++ ) args[k] = parse.get(k); }catch(Exception e){} 
		return args;
	}
	
	public String id(){ return id; }
	public String method(){ return method; }
	public Object[] args(){ return args; }
}