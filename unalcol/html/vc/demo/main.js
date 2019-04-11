if( window['contentBar'] == null ) contentBar = {};

contentBar.trash =  function (){
//	var editor = ace.edit(vc.jsId('right-1'));
//	contentBar.trash(editor.getValue());
	var p = document.getElementById(vc.jsId('contentBar'));
	trace(p.tagName+'.'+p.id+'.'+p.style.verticalAlign);
}

contentBar.home = function (){
trace('[contentBar]'+'home_button....');
	var content = document.getElementById(vc.jsId('q-100'));
trace('[contentBar]'+content);
	var notes = document.getElementById(vc.jsId('right-1'));
	var l = content.style.left;
	if( l=='0%' ){
		notes.style.left = '0%';
		content.style.left = notes.style.width;
	}else{
		notes.style.left = content.style.width;
		content.style.left = '0%';
	}
}
