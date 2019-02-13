package unalcol.js.vc.mode.fx;

import unalcol.js.vc.JSModel;

public class IDE{
	public static String repairHostName( String host ){
		if( host.indexOf("http") < 0) host = "http://" + host;
		while( host.endsWith("/") ) host = host.substring(0, host.length()-1);
		if( !host.endsWith(".html" ) ){
			if( !host.endsWith("/") ) host = host + "/";
			host = host + "index.html";
		}
		return host;
	}
	
	public static void main(String[] args){
		// Here is an example of the arguments 
		//args = new String[2];
		//args[0] = "localhost";
		//args[1] = "pack=demo";

		// Getting the parameters 
		String host = repairHostName(args[0]); // An url with info of the host webpage: "http://localhost/index.html
		StringBuilder query = new StringBuilder();
		query.append("?mode=fx");
		for( int i=1; i<args.length; i++ )
			if( args[i].indexOf("mode=") < 0 ){
				query.append('&');
				query.append(args[i]);			
			}
		String url = host + query.toString();
		// For the example, the url will become something like: "http://localhost/index.html?mode=fx&pack=demo"
		new JSModel(url);
	}
}