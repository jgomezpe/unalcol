//
//Unalcol JavaScript Service structure Pack 1.0 by Jonatan Gomez-Perdomo
//
/**
*
* unalcol.js
* <P>The unalcol javascript library. Every connection to javascript uses unalcol() method
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

function trace( data ){	document.getElementById('tracer').innerHTML = data; }

// Global unalcol object
var unalcol = {
	load : function (url_string){
		page.init(url_string);
		script.addVC('main', null);
		if( page.mode=='servlet/' ) servlet.load('servlet.setParams("'+url_string+'")', eval);
		xml.load(page.file, vc.load);
		window.addEventListener('resize', resizer.apply, false);
	},
	
	// vector load
	loadVector: function ( v, f ){
		while( v.length>0 ){
			var x = v[0];
			v.shift();
			f(x);
		}
	}
}

var page = {
	url: '',
	port:'',
	mode: 'js',
	pack: '',
	file: 'main.xml',

	init: function(url_string) {
		var k = url_string.indexOf('?');
		var url = url_string.substring( 0, k );
		if( url.indexOf('index.html') >= 0 ) url = url.substring(0,k-10);
		var colon = url.lastIndexOf(':');
		if( colon > 5 ){
			page.port = url.substring(colon+1, url.length-1);
			url = url.substring(0,colon) + '/';
		}
		page.url = url;
		// Convert query string to object
		url_string = url_string.substring( k+1 );
		var queries = url_string.split('&');
		for( i = 0; i < queries.length; i++ ) {
			split = queries[i].split('=');
			page[split[0]] = split[1];
		}
		page.mode += '/';
		if( page.pack.length > 0 ) page.pack += '/';
		page.file = 'xml/' + page.pack + page.file; 	
	},

	vc: function(){ return page.url+'vc/'; },

	packPath: function(){ return page.vc()+page.pack; }
}

// Script functions 
var script ={
	set:[],
	
	get : function ( id ){
		var n = script.set.length;
		for( i=0; i<n && script.set[i].id != id; i++ ){}	
		if( i<n ) return script.set[i];
		return null;
	},

	add: function ( type, src, theId, async, callback, errorback ){
		var myScript = script.get(theId);
		if( myScript!=null ){ 
			var state = myScript.state;
			var queue = myScript.queue;
			if( state==1 ) callback();
			if( state==2 ) errorback();
			if( state==0 ) queue.push( callback );
		}else{
			theScript = document.createElement( 'script' );
			if( theId!=null ){ theScript.id = theId; }
			if( type!=null ) theScript.type = type;
			if( async!=null ) theScript.async = async;
			if( async ){ theScript.src = src; }else{ theScript.innerHTML = src; }
			theScript.charset="utf-8";
			theScript.onreadystatechange = null;
			script.set.push({ id:theId, state:0, queue:[callback] });
			myScript = script.get(theId);
			
			function onload_callback(){
				myScript.state = 1;
				var queue = myScript.queue;
				while( queue.length>0 ){
					var f = queue[0];
					if( f!=undefined && f!=null ) f();
					queue.shift();
				} 
			}

			function onerror_callback(){
				//trace('[unalcol]error'+theId);
				myScript.state = 2;
				if( errorback!=null ) errorback();
			}

			theScript.onload = onload_callback;
			theScript.onerror = onerror_callback;	
			var b = document.getElementsByTagName('script')[0];
			b.parentNode.insertBefore(theScript, b);
		}
	},

	addAsync: function ( type, src, id ){ script.add( type, src, id, false, null, null ); },
	
	addJS: function ( src, id, callback, errorback ){ script.add( 'text/javascript', src, id, true, callback, errorback ); },

	addVC: function ( id, callback ){
		var file = id+'.js';
		var path = page.vc();
		var pack = page.packPath();
		var mode = page.mode;
		function nothree(){ script.addJS( path+file, id+'-unc-js', callback, null ); }
		function notwo(){ script.addJS( pack+file, id+'-js', callback, nothree ); }
		function noone(){ script.addJS( path+mode+file, id+'-unc-mode-js', callback, notwo ); }
		this.addJS( pack+mode+file, id+'-mode-js', callback, noone );
	}
};

// XML processing methods used by an unalcol javascript client
var xml = {
	load : function ( xmlFile, fn ){
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function (){ if (xhttp.readyState==4 && xhttp.status == 200){ fn(xhttp.responseXML); } }
		xhttp.open('GET', xmlFile, true);
		xhttp.setRequestHeader("Cache-Control", "max-age=0");
		xhttp.send(); 
	},

	child : function ( node, k ){
		var j=-1;
		var n=-1;
		var m = node.childNodes.length; 
		for( var i=0; i<m && j==-1; i++ )
			if(node.childNodes[i].nodeType == 1){
				n++;
				if(n==k) j=i;
			}
		return node.childNodes[j];
	},
	
	childById: function (node, id){
		var n = node.children.length;  
		for( var i=0; i<n; i++ ) if(node.children[i].id == id) return i;
		return -1;
	},
	
	childCount: function ( node ){ 
		var m = node.childNodes.length; 
		var n=0;
		for( var i=0; i<m; i++ ) if(node.childNodes[i].nodeType == 1) n++;
		return n;
	},
	
	createNode: function ( tag ){
		parser = new DOMParser();
		xmlDoc = parser.parseFromString("<"+tag+"></"+tag+">","text/xml");
		return xmlDoc.getElementsByTagName(tag)[0];
	}, 	
	
	send: function ( xmlDoc ){ servlet.send( xmlDoc ); }
};

// Collection of methods for gui component managment
var vc = {
	js_tag:'JS',
	jsId: function (id){ return vc.js_tag+id; },

	find: function (id){ return document.getElementById(vc.jsId(id)); },
	
	// HTML functions
	createChildFlow: function (tag, id){
		var c = document.createElement(tag);
		if( id != null ){ c.id = id; }
		return c;
	},
	
	create: function (tag, id, left, top, width, height){
		var c = document.createElement(tag);
		if( id != null ){ c.id = vc.jsId(id); }
		c.style.position = 'absolute';
		c.style.left = left+'%';
		c.style.top = top+'%';
		c.style.maxHeight = c.style.height = (height)+'%';
		c.style.maxWidth = c.style.width = (width)+'%';
		return c;
	},

	div: function (id, left, top, width, height){ return vc.create('div', id, left, top, width, height ); },
	
	addChild: function (container, tag, id, left, top, width, height){
		var c = vc.create(tag, id, left, top, width, height);
		container.appendChild(c);
		return c;
	},

	addChildFlow: function (container, tag, id){
		var c = document.createElement(tag);
		if( id != null ){ c.id = vc.jsId(id); }
		container.appendChild(c);
		return c;
	},

	addDiv: function (container, id, left, top, width, height){ return vc.addChild(container, 'div', id, left, top, width, height); },

	replaceChild: function (container, k, tag, id, left, top, width, height){
		var c = vc.create(tag, id, left, top, width, height);
		container.replaceChild( c, container.children[k]); 
		return c;
	},

	setChild: function (container, tag, id, left, top, width, height){
		container.innerHTML = "";
		return vc.addChild(container, tag, id, left, top, width, height); 
	},

	addOrReplaceChild: function (container, tag, id, left, top, width, height){
		var k = xml.childById(container, id);
		if(k>=0){ return vc.replaceChild(container, k, tag, id, left, top, width, height); }
		else{ return vc.addChild(container, tag, id, left, top, width, height); }
	},

	// Children functions
	// As Flow
	addChildrenFlow: function ( comp, node ){
		var n = xml.childCount(node);
		for( var i=0; i<n; i++){
			var node_child = xml.child( node, i );
			var cChild = vc.addChildFlow(comp, node_child.tagName, node_child.id );
			vc.init(cChild, node_child);
		} 
		return comp;
	},

	  // As Table
	positions: function ( n, s ){
		var pos	= [0];
		var m = Math.ceil(n/s);
		var w = 100.0/m;
		var prev;
		var x = 0.0;
		for( var i=0; i<m; i++ ){
			x = x + w;
			pos.push(Math.round(x));
		}
		return pos;
	},

	sizes: function ( pos ){
		var s = [];
		var n = pos.length-1;
		for( var i=0; i<n; i++ ) s.push(pos[i+1] - pos[i]);
		return s;
	},

	genericAddChildrenTable: function ( comp, node, buildChild ){
		var n = xml.childCount(node);
		if( n>=1 ){
			comp.innerHTML = '';
			var width = node.getAttribute('widths');
			if( width != null ) width = width.split(' ');
			var height = node.getAttribute('heights');
			if( height != null ) height = height.split(' ');
			var left = [0];
			var top = [0];
			if( width != null ){
				for( var i=0; i<width.length; i++ ){
					width[i] = parseInt(width[i]);
					left.push(left[i]+width[i]);
				}
			}else{
				if( height != null ){
					left = vc.positions(n,height.length);
					width = vc.sizes( left );
				}else width = [100];
			}

			if( height != null ){
				for( var i=0; i<height.length; i++ ){
					height[i] = parseInt(height[i]);
					top.push(top[i]+height[i]);
				}
			}else{
				top = vc.positions( n, width.length );
				height = vc.sizes(top);
			}

			var i=0;
			var j=0;
			for( var k=0; k<n; k++){
				var node_child = xml.child( node, k );
				var cell = vc.addDiv(comp, node_child.id, left[j], top[i], width[j], height[i]);
				buildChild(cell, node_child);
				j++;
				if( j>=width.length ){
					j=0; 
					i++;
				}
			}
		}
		return comp;
	},

	addChildrenTable: function ( comp, node ){ return vc.genericAddChildrenTable(comp, node, vc.init); },

	init: function ( container, node ){ return plugin.run( container, node, plugin.unknown ); },
	
	// Loading Functions

	load: function ( xmlDoc ){
		root = xmlDoc.documentElement.tagName;
		var node = xml.child(xmlDoc.getElementsByTagName(root)[0], 0);
		var root_container = vc.find(root);
		root_container.innerHTML = "";
		var cmain = vc.setChild( root_container, 'div', node.tagName, 0, 0, 100, 100 );
		vc.init( cmain, node );
	},

	loadTxt: function ( xmlTxt ){
	   parser = new DOMParser();
	   xmlDoc = parser.parseFromString(xmlTxt,"text/xml");
	   vc.load( xmlDoc );
	}	
};

// Collection of methods for loading javascript components dynamically 
var plugin={
	set:[],
	
	fn: function ( comp, isNotDefined ){ 
		var g = window[comp]; 
		trace('[plugin...]'+g);
		if( g==null ) g = isNotDefined; 
		else g = g.load; 
		return g;
	},

	fnAddChildren: function ( comp ){ 
		var g = window[comp]; 
		if( g==null || g.addChildren==null) g = vc.addChildrenTable; 
		else g = g.addChildren; 
		return g;
	},

	run: function ( container, node, isNotDefined ){
		var e = node.tagName;
		var f = plugin.fn(e, isNotDefined);
		var aC = plugin.fnAddChildren( e ); 
		return aC( f(container, node), node );
	},


	execute: function ( pIn ){
		var used = pIn.defined;
		while(used.length>0){
			var container = used[0][0];
			var node = used[0][1];
			used.shift();
			plugin.run(container, node, plugin.bydefault); 
		}
	},

	find: function ( id ){
		var i=0;
		while( i<plugin.set.length && plugin.set[i].id!=id ){ i++; }
		if( i<plugin.set.length ) return plugin.set[i]; else return null;
	},

	bydefault: function ( container, node ){ return container; },

	unknown: function ( container, node ){
		function unk_callback(){ plugin.execute( plugin.find(node.tagName) ); }
	
		var pIn=plugin.find(node.tagName);
		if( pIn==null ){
			pIn = {id:node.tagName,defined:[]};
			plugin.set.push(pIn);
			script.addVC( node.tagName, unk_callback );
		}
		
		pIn.defined.push([container,node]); 
		return container;
	}
}

var resizer = {
	set:[],
	
	add: function ( method ){
		resizer.set.push( method );
	},
	
	apply: function(){
		for( var i = 0; i < resizer.set.length; i++ ){
			resizer.set[i]();
		}
	}
}

// Collection of methods for connecting to server from an unalcol javascript client
var servlet = {
	id:'unalcol',
	MAX:100000000,
	min_time:100000000,
	timer:[],
	intervalId:null,
	
	init: function (){
		servlet.min_time = servlet.MAX;
		servlet.timer = [];
		if(servlet.intervalId!=null) window.clearInterval(servlet.intervalId);
		servlet.intervalId=null;
	},
	
	load: function(params, fn){
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function (){ if (xhttp.readyState==4 && xhttp.status == 200) fn(xhttp.responseText); }
		var url = page.url;
		if( page.port.length>0 ) url = url.substring(0,url.length-1)+':' + page.port + '/';
		xhttp.open('POST', url + servlet.id , true);
		xhttp.setRequestHeader("Cache-Control", "max-age=0");
		xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		//xhttp.setRequestHeader("Content-length", params.length);
		xhttp.send(params); 
	},
	
	pull: function(){ servlet.load('servlet.pull()', eval); },

	setTimer: function ( time_delay ){
		servlet.min_time = time_delay;
		if(servlet.intervalId!=null) window.clearInterval(servlet.intervalId);
		if( servlet.min_time != servlet.MAX ) servlet.intervalId = window.setInterval(servlet.pull, servlet.min_time); else servlet.init();
	},

	addTimer: function ( caller_id, time_delay ){
		var n = servlet.timer.length;
		var i=0;
		while( i<n && servlet.timer[i].caller != caller_id ){ i++; }
		if( i<n ){
			var obj = servlet.timer[i];
			n=obj.delay.length;
			i=0;
			while( i<n && obj.delay[i] != time_delay ){ i++; }
			if(i<n) obj.counter[i]++;
			else{
				obj.delay.push(time_delay);
				obj.counter.push(1);
			} 
		}else{
			servlet.timer.push({caller:caller_id, delay:[time_delay], counter:[1]});
		}
		if( time_delay < servlet.min_time ) servlet.setTimer( time_delay );
	},

	delTimer: function( caller_id, time_delay ){
		var n = servlet.timer.length;
		if( n>0 ){
			var i=0;
			while( i<n && servlet.timer[i].caller != caller_id ){ i++; }
			if( i<n ){
				var k = i;
				var obj = servlet.timer[i];
				n=obj.delay.length;
				i=0;
				while( i<n && obj.delay[i] != time_delay ){ i++; }
				if(i<n){
					obj.counter[i]--;
					if(obj.counter[i]==0){
						obj.delay.splice(i, 1);
						obj.counter.splice(i,1);
						if( obj.delay.length==0 ) servlet.timer.splice(k,1);
						if( time_delay==servlet.min_time ){
							var m = servlet.MAX;
							for( i=0; i<servlet.timer.length; i++ ){
								obj = servlet.timer[i];
								for( k=0; k<obj.delay.length; k++ )	if( obj.delay[k] < m ) m = obj.delay[k];
							}
							if( m != servlet.min_time ) servlet.setTimer( m );	
						}
					}
				} 
			}
		}
	},
	
	send : function ( xmlDoc ){ servlet.load('xml.load('+xmlDoc+')', eval ); },
	
	encode: function ( str ){ return '"' + str.replace(/\\/g, '\\\\').replace(/"/g,'\\"') +'"'; }
}
