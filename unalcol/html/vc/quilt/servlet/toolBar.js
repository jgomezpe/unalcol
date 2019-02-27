script.addJS( unalcol.url+'vc/'+unalcol.pack+'/toolBar.js', 'b-toolBar-js', null, null );

toolBar={
	primitives:function (){ servlet.load('toolBar.primitives()', eval ); },
	values:function (){ servlet.load('toolBar.values()', eval ); }
}

FunCommand={
		text: function(txt){ servlet.load('FunCommand.text('+servlet.encode(txt)+')', eval); }
}

FunProgram={
		text: function(txt){ servlet.load('FunProgram.text('+servlet.encode(txt)+')', eval); }
}