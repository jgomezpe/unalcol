function trash_button(){
	var editor = ace.edit(vcl.jsId('right-1'));
	contentBar.trash(editor.getValue());
}

function home_button(){
trace('[contentBar]'+'home_button....');
	var content = document.getElementById(vcl.jsId('q-100'));
trace('[contentBar]'+content);
	var notes = document.getElementById(vcl.jsId('right-1'));
	var l = content.style.left;
	if( l=='0%' ){
		notes.style.left = '0%';
		content.style.left = notes.style.width;
	}else{
		notes.style.left = content.style.width;
		content.style.left = '0%';
	}
}
