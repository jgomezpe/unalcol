var fileOperation;
var tprogram = 'FunProgram';
var tcommand = 'FunCommand';
var jsProgram = vc.jsId(tprogram);
var jsCommand = vc.jsId(tcommand);

if( window['FunCommand'] == null ) FunCommand = {};
if( window['FunProgram'] == null ) FunProgram = {};
if( window['toolBar'] == null ) toolBar = {};

toolBar.clear = function (){
	editor.setText(jsProgram, ""); 
	var anchor = document.getElementById('savefile');
	anchor.download = fileName;
}

toolBar.open=function (){
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

toolBar.save = function(){
	var anchor = document.getElementById('savefile');
	anchor.href="data:text/plain;charset=utf-8,"+editor.getText(jsProgram);
	anchor.click();
}

toolBar.compile = function (){ FunProgram.text(editor.getText(jsProgram)); }

toolBar.machine = function (){
	var edit = ace.edit(jsProgram); 
	window['quilt_ace_editor_rules'] = rules(['0','1','2','3']); // @TODO: Get the remnants 
	function callback(){ edit.getSession().setMode({path:"ace/mode/quilt", v:Date.now()}); }
	script.addVC('ace/mode-quilt',callback);
}

if( window['cmdBar'] == null ) cmdBar = {};
cmdBar.run = function(){ FunCommand.text(editor.getText(jsCommand)); }

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
