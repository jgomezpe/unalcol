addJavaScript( page.packPath()+'main.js', 's-main-js', null, null );

if( window['contentBar'] == null ) contentBar = {};

contentBar.trash = function (txt){ loadServlet('contentBar.trash('+encode(txt)+')', eval ); }

