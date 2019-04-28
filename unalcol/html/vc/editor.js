//
//Unalcol JavaScript Service structure Pack 1.0 by Jonatan Gomez-Perdomo
//
/**
*
* editor.js
* <P>A basic editor for unalcol (based on ACE editor)  
*
* <P>
*
* <h3>License</h3>
*
* Copyright (c) 2019 by Jonatan Gomez-Perdomo. <br>
* All rights reserved. <br>
*
* <p>Redistribution and use in source and binary forms, with or without
* modification, are permitted provided that the following conditions are met:
* <ul>
* <li> Redistributions of source code must retain the above copyright notice,
* this list of conditions and the following disclaimer.
* <li> Redistributions in binary form must reproduce the above copyright notice,
* this list of conditions and the following disclaimer in the documentation
* and/or other materials provided with the distribution.
* <li> Neither the name of the copyright owners, their employers, nor the
* names of its contributors may be used to endorse or promote products
* derived from this software without specific prior written permission.
* </ul>
* <p>THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
* AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
* IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
* DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNERS OR CONTRIBUTORS BE
* LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
* CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
* SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
* INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
* CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
* ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
* POSSIBILITY OF SUCH DAMAGE.
*
*
*
* @author <A HREF="http://disi.unal.edu.co/profesores/jgomezpe"> Jonatan Gomez-Perdomo </A>
* (E-mail: <A HREF="mailto:jgomezpe@unal.edu.co">jgomezpe@unal.edu.co</A> )
* @version 1.0
*/

// Editor functions
editor = unalcol.plugins.set.editor

editor.done = function (){
	editor.state='loaded';
	vector.apply( editor.view, editor.loadSingleEditor );
}
	
editor.state ='unloaded'
	
unalcol.script.addJS("https://ace.c9.io/build/src/ace.js", 'ace-js', editor.done);

editor.ace_define = function ( lang ){
	var id = lang.mode+'_'+lang.flavor;
	ace.define(
		"ace/mode/"+id+"_highlight_rules",
		["require","exports","module","ace/lib/oop","ace/mode/text_highlight_rules"], 
		function(require, exports, module) {
			"use strict";

			var oop = require("../lib/oop");
			var TextHighlightRules = require("./text_highlight_rules").TextHighlightRules;

			var EditorHighlightRules = function() {
				this.$rules = lang.tokens();
				this.normalizeRules();
			};

			EditorHighlightRules.metaData = {
				fileTypes: lang.fileTypes,
      			foldingStartMarker: '(%\\s*region \\w*)|([a-z]\\w*.*:- ?)',
				foldingStopMarker: '(%\\s*end(\\s*region)?)|(?=\\.)',
				keyEquivalent: '^~P',
				name: lang.qName,
				scopeName: 'source.'+lang.mode
			};


		oop.inherits(EditorHighlightRules, TextHighlightRules);

		exports.EditorHighlightRules = EditorHighlightRules;
		}
	);

	ace.define(
		"ace/mode/folding/cstyle",
		["require","exports","module","ace/lib/oop","ace/range","ace/mode/folding/fold_mode"], 
		function(require, exports, module) {
			"use strict";

			var oop = require("../../lib/oop");
			var Range = require("../../range").Range;
			var BaseFoldMode = require("./fold_mode").FoldMode;

			var FoldMode = exports.FoldMode = function(commentRegex) {
				if (commentRegex) {
					this.foldingStartMarker = new RegExp( this.foldingStartMarker.source.replace(/\|[^|]*?$/, "|" + commentRegex.start) );
	        			this.foldingStopMarker = new RegExp( this.foldingStopMarker.source.replace(/\|[^|]*?$/, "|" + commentRegex.end) );
				}
			};
			oop.inherits(FoldMode, BaseFoldMode);

			(function() {
    	
				this.foldingStartMarker = /([\{\[\(])[^\}\]\)]*$|^\s*(\/\*)/;
				this.foldingStopMarker = /^[^\[\{\(]*([\}\]\)])|^[\s\*]*(\*\/)/;
				this.singleLineBlockCommentRe= /^\s*(\/\*).*\*\/\s*$/;
				this.tripleStarBlockCommentRe = /^\s*(\/\*\*\*).*\*\/\s*$/;
				this.startRegionRe = /^\s*(\/\*|\/\/)#?region\b/;
				this._getFoldWidgetBase = this.getFoldWidget;
				this.getFoldWidget = function(session, foldStyle, row){
					var line = session.getLine(row);
    	
					if (this.singleLineBlockCommentRe.test(line)) { if (!this.startRegionRe.test(line) && !this.tripleStarBlockCommentRe.test(line)) return ""; }
    
					var fw = this._getFoldWidgetBase(session, foldStyle, row);
	    
					if (!fw && this.startRegionRe.test(line)) return "start"; // lineCommentRegionStart	
					return fw;
				};

				this.getFoldWidgetRange = function(session, foldStyle, row, forceMultiline) {
					var line = session.getLine(row);
        
					if (this.startRegionRe.test(line)) return this.getCommentRegionBlock(session, line, row);
        
					var match = line.match(this.foldingStartMarker);
					if (match) {
						var i = match.index;

						if (match[1]) return this.openingBracketBlock(session, match[1], row, i);
                
						var range = session.getCommentFoldRange(row, i + match[0].length, 1);
            
						if (range && !range.isMultiLine()){ 
							if (forceMultiline) { range = this.getSectionRange(session, row); }
							else if (foldStyle != "all") range = null;
						}

						return range;
					}

					if (foldStyle === "markbegin") return;

					var match = line.match(this.foldingStopMarker);
				        if (match) {
							var i = match.index + match[0].length;

							if (match[1]) return this.closingBracketBlock(session, match[1], row, i);
	
							return session.getCommentFoldRange(row, i, -1);
					}
				};
    	
				this.getSectionRange = function(session, row) {
					var line = session.getLine(row);
					var startIndent = line.search(/\S/);
					var startRow = row;
					var startColumn = line.length;
					row = row + 1;
					var endRow = row;
					var maxRow = session.getLength();
					while (++row < maxRow) {
						line = session.getLine(row);
						var indent = line.search(/\S/);
						if (indent === -1) continue;
						if  (startIndent > indent) break;
						var subRange = this.getFoldWidgetRange(session, "all", row);
            
						if (subRange) {
							if (subRange.start.row <= startRow) { break; }
							else if (subRange.isMultiLine()) { row = subRange.end.row; } 
								 else if (startIndent == indent) { break; }
						}
						endRow = row;
			        }
        
					return new Range(startRow, startColumn, endRow, session.getLine(endRow).length);
				};
	
				this.getCommentRegionBlock = function(session, line, row) {
					var startColumn = line.search(/\s*$/);
					var maxRow = session.getLength();
					var startRow = row;
        	
					var re = /^\s*(?:\/\*|\/\/|--)#?(end)?region\b/;
					var depth = 1;
					while (++row < maxRow) {
						line = session.getLine(row);
						var m = re.exec(line);
						if (!m) continue;
						if (m[1]) depth--;
						else depth++;
						if (!depth) break;
					}

					var endRow = row;
					if (endRow > startRow) { return new Range(startRow, startColumn, endRow, line.length); }
				};

			}).call(FoldMode.prototype);

		}
	);

	ace.define(
		"ace/mode/"+id,
		["require","exports","module","ace/lib/oop","ace/mode/text","ace/mode/"+id+"_highlight_rules","ace/mode/folding/cstyle"],
		function(require, exports, module) {
			"use strict";

			var oop = require("../lib/oop");
			var TextMode = require("./text").Mode;
			var EditorHighlightRules = require("./"+id+"_highlight_rules").EditorHighlightRules;
			var FoldMode = require("./folding/cstyle").FoldMode;

			var Mode = function() {
				this.HighlightRules = EditorHighlightRules;
				this.foldingRules = new FoldMode();
				this.$behaviour = this.$defaultBehaviour;
			};
			oop.inherits(Mode, TextMode);

			(function() {
				this.lineCommentStart = "%";
				this.blockComment = {start: "/*", end: "*/"};
				this.$id = "ace/mode/"+id;
			}).call(Mode.prototype);

			exports.Mode = Mode;
		}
	);
}


editor.view = []

editor.loadSingleEditor = function ( lang ){
	var flavor = lang.flavor;
	var mode = lang.mode;
	var container = lang.cid;
	if( flavor==undefined || flavor==null ){
		function callbacksimple(){
			var e = ace.edit(container);
			e.setTheme("ace/theme/chrome");
			e.session.setMode("ace/mode/"+mode);
			e.setShowPrintMargin(false);
		}
		unalcol.script.addVC('ace/'+lang.mode,callbacksimple);		
	}else{
		var id = mode+'_'+flavor;
		function callback(){
			lang = window['get_'+id](container);
			editor.ace_define(lang);
			var e = ace.edit(container);
			e.setTheme("ace/theme/chrome");
			e.session.setMode("ace/mode/"+id);
			e.setShowPrintMargin(false);
		}
		unalcol.script.addVC('ace/'+id,callback);		
	}
}
	
editor.open = function ( lang ){
	editor.view.push(lang);
	if( editor.state=='loaded') editor.done();
}
	
editor.run = function ( node ){ 
	var container = vc.load(node)
	container.style.position = 'absolute'
	editor.open( {cid: container.id, mode:node.getAttribute('mode'), flavor:node.getAttribute('flavor'), fileTypes:null, qName:null, tokens:null, parser:null, meaner:null} );
	return container;
}
	
editor.setText = function ( editorId, txt ){
	var edit = ace.edit(editorId);
	edit.setValue(txt, -1);
}
	
editor.getText = function ( editorId ){
	var edit = ace.edit(editorId);
	return edit.getValue();
}

editor.locateCursor = function ( editorId, row, column ){
	var edit = ace.edit(editorId);
	edit.moveCursorTo(row, column);
	edit.focus();
}
	
editor.highlight = function ( editorId, row ){ editor.locateCursor( editorId, row,1 ); }
