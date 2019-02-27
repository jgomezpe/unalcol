var fileOperation;
var tprogram = 'FunProgram';
var tcommand = 'FunCommand';
var jsProgram = vcl.jsId(tprogram);
var jsCommand = vcl.jsId(tcommand);

function readSingleFile(evt){
	var f = evt.target.files[0]; 
	if(f){
		var r = new FileReader();
		r.onload = function(e){ 
			var contents = e.target.result;
			fileOperation(f, contents);
		}
		r.readAsText(f);
	}
}

var fileupload = document.getElementById("FileUploader");

function open_button(){
	fileOperation = function( f, contents ){
trace('[toolBar] fileOperation open 1');
		var edit = ace.edit(jsProgram); 
		edit.setValue(contents, -1);
trace('[toolBar] fileOperation open 2');
		var anchor = document.getElementById('savefile');
		anchor.download = f.name;
	}
trace('[toolBar] fileOperation open 0');
	fileupload.addEventListener('change', readSingleFile, false);
trace('[toolBar] fileOperation open 0.1');
	fileupload.setAttribute('accept','.qmp');
trace('[toolBar] fileOperation open 0.2');
	fileupload.click();
trace('[toolBar] fileOperation open 0.3');
}

function new_button(){
	editor.setText(jsProgram, ""); 
	var anchor = document.getElementById('savefile');
	anchor.download = fileName;
}

function save_button(){
	var anchor = document.getElementById('savefile');
	anchor.href="data:text/plain;charset=utf-8,"+editor.getText(jsProgram);
	anchor.click();
}

function compile_button(){ FunProgram.text(editor.getText(jsProgram)); }

function run_button(){ FunCommand.text(editor.getText(jsCommand)); }

function tools_button(){ toolBar.primitives(); }

function remnants_button(){ toolBar.values(); }

function machine_button(){
	var edit = ace.edit(jsProgram); 
	window['quilt_ace_editor_rules'] = rules(['0','1','2','3']); // @TODO: Get the remnants 
	function callback(){ edit.getSession().setMode({path:"ace/mode/quilt", v:Date.now()}); }
	script.addVC('ace/mode-quilt',callback);
}