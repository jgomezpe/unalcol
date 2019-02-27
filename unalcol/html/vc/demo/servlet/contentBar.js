addJavaScript( getUrl()+'vc/'+getPackage()+'contentBar.js', 'b-contentBar-js', null, null );

contentBar.trash=function (txt){ 
	loadServlet('contentBar.trash('+encode(txt)+')', eval ); 
};
