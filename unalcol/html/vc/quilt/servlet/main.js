script.addJS( page.packPath()+'main.js', 's-main-js', null, null );

if( window['toolBar'] == null ) toolBar = {};
toolBar.primitives = function (){ servlet.load('toolBar.primitives()', eval ); }
toolBar.values =function (){ servlet.load('toolBar.values()', eval ); }

if( window['FunCommand'] == null ) FunCommand = {};
FunCommand.text = function(txt){ servlet.load('FunCommand.text('+servlet.encode(txt)+')', eval); }

if( window['FunProgram'] == null ) FunProgram = {};
FunProgram.text = function(txt){ servlet.load('FunProgram.text('+servlet.encode(txt)+')', eval); }
