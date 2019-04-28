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

function trace( data ){
	var tracer = document.getElementById('tracer') 
	if( tracer!= null ) tracer.innerHTML += data; 
}

function Timer( unalcol ){
	this.MAX = 100000000
	this.min = this.MAX
	this.set = []
	this.intervalId = null
	this.fix = function ( time_delay ){
		this.min = time_delay;
		if(this.intervalId!=null) window.clearInterval(this.intervalId);
		if( this.min != this.MAX ) this.intervalId = window.setInterval(unalcol.pull, this.min)
		else{
			this.set = []
			this.intervalId = null
		}
	}

	this.add = function ( caller_id, time_delay ){
		var n = this.set.length;
		var i=0;
		while( i<n && this.set[i].caller != caller_id ){ i++; }
		if( i<n ){
			var obj = this.set[i];
			n=obj.delay.length;
			i=0;
			while( i<n && obj.delay[i] != time_delay ){ i++; }
			if(i<n) obj.counter[i]++;
			else{
				obj.delay.push(time_delay);
				obj.counter.push(1);
			} 
		}else this.set.push({caller:caller_id, delay:[time_delay], counter:[1]})
		if( time_delay < this.min ) this.fix( time_delay );
	}

	this.del = function( caller_id, time_delay ){
		var n = this.set.length;
		if( n>0 ){
			var i=0;
			while( i<n && this.set[i].caller != caller_id ){ i++; }
			if( i<n ){
				var k = i;
				var obj = this.set[i];
				n=obj.delay.length;
				i=0;
				while( i<n && obj.delay[i] != time_delay ){ i++; }
				if(i<n){
					obj.counter[i]--;
					if(obj.counter[i]==0){
						obj.delay.splice(i, 1);
						obj.counter.splice(i,1);
						if( obj.delay.length==0 ) this.set.splice(k,1);
						if( time_delay==this.min ){
							var m = servlet.MAX;
							for( i=0; i<this.set.length; i++ ){
								obj = this.set[i];
								for( k=0; k<obj.delay.length; k++ )	if( obj.delay[k] < m ) m = obj.delay[k];
							}
							if( m != this.min ) this.fix( m );	
						}
					}
				} 
			}
		}
	}
}

function Resizer(){
	this.set =[]
	
	this.add = function ( method ){
		var i = 0; 
		while( i < this.set.length && this.set[i]!=method ) i++
		if( i==this.set.length) this.set.push( method );
	}
	
	this.apply = function(){ for( var i = 0; i < this.set.length; i++ ) this.set[i](); }
}

// Global unalcol object
function Unalcol(url_string){
	this.id='Unalcol'
	this.page = new Page(url_string)
	this.script = new Script( this.page  )
	this.script.addMain()
	this.timer = new Timer( this )
	this.resizer = new Resizer()

	this.servlet = {}
	this.addServlet = function( servlet ){ this.servlet[servlet.id] = servlet }
	if( this.page.mode=='servlet/' ){
		servlet = new Servlet(this.page, 'unalcol')
		servlet.load('servlet.setParams("'+url_string+'")', eval)
		this.addServlet(servlet)
	}	
	this.pull = function (){ for (var s in this.servlet) this.servlet[s].pull()  }

	this.plugins = new PlugIns(this)

	// @TODO : Check resize for all components and removed
	window.addEventListener('resize', this.resizer.apply.bind(this.resizer), false)
}

// vector load
var vector = {
	apply: function ( v, f ){
		while( v.length>0 ){
			var x = v[0]
			v.shift()
			f(x)
		}
	}
}

function Page(url_string){
//	document.body.innerHTML += '<div id="tracer">Hellooxxxooooo.......</div> <div style = "position:absolute;width:99%;height:95%;"> <div id="JSmain" ></div> </div>'
	document.body.innerHTML += '<div style = "position:absolute;width:99%;height:99%"> <div id="JSmain"></div> </div>';

	var k = url_string.indexOf('?')
	var _url = url_string.substring( 0, k )
	if( _url.indexOf('index.html') >= 0 ) _url = _url.substring(0,k-10)
	var colon = _url.lastIndexOf(':')

	this.port=''
	if( colon > 5 ){
		this.port = _url.substring(colon+1, _url.length-1)
		_url = _url.substring(0,colon) + '/'
	}

	this.url = _url

	// Convert query string to object
	this.mode='servlet'
	this.pack=''
	this.file='main.xml'
	url_string = url_string.substring( k+1 )
	var queries = url_string.split('&');
	for( i = 0; i < queries.length; i++ ) {
		split = queries[i].split('=')
		this[split[0]] = split[1]
	}
	this.mode += '/'
	if( this.pack.length > 0 ) this.pack += '/'
	this.file = 'xml/' + this.pack + this.file 	

	this.vc = function(){ return this.url+'vc/' }
	this.packPath = function(){ return this.vc()+this.pack }
}

// Script functions 
function Script( page ){
	this.page = page
	this.set = []
	
	this.get = function ( id ){
		var n = this.set.length
		for( i=0; i<n && this.set[i].id != id; i++ ){}	
		if( i<n ) return this.set[i]
		return null
	}

	this.add = function ( type, src, theId, async, callback, errorback ){
		var myScript = this.get(theId)
		if( myScript!=null ){ 
			var state = myScript.state
			var queue = myScript.queue
			if( state==1 ) callback()
			if( state==2 ) errorback()
			if( state==0 ) queue.push( callback )
		}else{
			theScript = document.createElement( 'script' )
			if( theId!=null ){ theScript.id = theId }
			if( type!=null ) theScript.type = type
			if( async!=null ) theScript.async = async
			if( async ){ theScript.src = src; }else{ theScript.innerHTML = src }
			theScript.charset="utf-8"
			theScript.onreadystatechange = null
			this.set.push({ id:theId, state:0, queue:[callback] })
			myScript = this.get(theId)
			
			function onload_callback(){
				myScript.state = 1
				var queue = myScript.queue
				while( queue.length>0 ){
					var f = queue[0]
					if( f!=undefined && f!=null ) f()
					queue.shift()
				} 
			}

			function onerror_callback(){
				myScript.state = 2
				if( errorback!=null ) errorback()
			}

			theScript.onload = onload_callback
			theScript.onerror = onerror_callback	
			var b = document.getElementsByTagName('script')[0]
			b.parentNode.insertBefore(theScript, b)
		}
	}

	this.addAsync = function ( type, src, id ){ this.add( type, src, id, false, null, null ) }
	
	this.addJS = function ( src, id, callback, errorback ){ this.add( 'text/javascript', src, id, true, callback, errorback ) }

	this.addMain = function(){	
		var pack = this.page.packPath()
		var mode = this.page.mode
		this.addJS( pack+mode+'main.js', 'main-js', null, null )
	}

	this.addVC = function ( id, callback ){
		var file = id+'.js'
		var path = this.page.vc()
		var pack = this.page.packPath()
		var caller = this
		function noone(){ caller.addJS( pack+file, id+'-pack', callback, null ) }
		this.addJS( path+file, id+'-core', callback, noone )
	}
}

// XML processing methods used by an unalcol javascript client
var xml = {
	load: function ( xmlFile, fn ){
		var xhttp = new XMLHttpRequest()
		xhttp.onreadystatechange = function (){ if (xhttp.readyState==4 && xhttp.status == 200){ fn(xhttp.responseXML) } }
		xhttp.open('GET', xmlFile, true)
		xhttp.setRequestHeader("Cache-Control", "max-age=0")
		xhttp.send()
	},

	parse: function ( xmlTxt ){ return new DOMParser().parseFromString(xmlTxt,"text/xml") },

	child: function ( node, k ){
		var j=-1
		var n=-1
		var m = node.childNodes.length
		for( var i=0; i<m && j==-1; i++ )
			if(node.childNodes[i].nodeType == 1){
				n++
				if(n==k) j=i
			}
		return node.childNodes[j]
	},
	
	childById: function (node, id){
		var n = node.children.length
		for( var i=0; i<n; i++ ) if(node.children[i].id == id) return i
		return -1
	},
	
	childCount: function ( node ){ 
		var m = node.childNodes.length 
		var n=0
		for( var i=0; i<m; i++ ) if(node.childNodes[i].nodeType == 1) n++
		return n
	},
	
	createNode: function ( tag ){ return xml.parse("<"+tag+"></"+tag+">").getElementsByTagName(tag)[0] },
	
	copy: function ( source, target, attribute ){
		var value = source.getAttribute(attribute)
		if( value != null ) target.setAttribute(attribute, value)
	},

	send: function ( xmlDoc ){ servlet.send( xmlDoc ) }
}

// Collection of methods for connecting to server from an unalcol javascript client
function Servlet( page, id ){
	this.page = page
	this.id = id
	
	this.load = function(params, fn){
		var xhttp = new XMLHttpRequest()
		xhttp.onreadystatechange = function (){ if (xhttp.readyState==4 && xhttp.status == 200) fn(xhttp.responseText) }
		var url = this.page.url
		if( this.page.port.length>0 ) url = url.substring(0,url.length-1)+':' + this.page.port + '/'
		xhttp.open('POST', url + this.id , true)
		xhttp.setRequestHeader("Cache-Control", "max-age=0")
		xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded")
		xhttp.send(params)
	}
	
	this.pull = function(){ this.load('servlet.pull()', eval) }

	this.send = function ( xmlDoc ){ this.load('xml.load('+xmlDoc+')', eval ) }

	this.encode = function ( str ){ return '"' + str.replace(/\\/g, '\\\\').replace(/"/g,'\\"') +'"' }
}

// Collection of methods for loading javascript components dynamically 
function PlugIn( type ){
	this.type = type
	this.set = []
	this.ready = false
	this.process = function(){
		this.ready = true
		vector.apply( this.set, this.run.bind(this.run) ) 
	}
	this.add = function(node){
		this.set.push(node)
		if( this.ready ) this.process()
	}
}

function PlugIns( unalcol ){
	this.unalcol = unalcol
	this.set = {}
	this.init = function( type ){
		var pIn=this.set[type]
		if( pIn==null ){
			pIn = new PlugIn(type)
			this.set[type] = pIn
			this.unalcol.script.addVC( type, pIn.process.bind(pIn) )
		}
		return pIn
	}
	this.load = function( node ){ this.init( node.tagName ).add(node) }
	this.loadDoc = function( xmlDoc ){ this.load(xmlDoc.documentElement) }
	this.loadTxt = function ( xmlTxt ){ this.load( xml.parse(xmlTxt) ) }
}

// Collection of methods for gui component managment
var vc = {
	js_tag:'JS',
	jsId: function (id){ return vc.js_tag+id; },
	id: function (js_id){ return js_id.substring(vc.js_tag.length); }, 

	find: function (id){ return document.getElementById(vc.jsId(id)); },
	
	container: function (id){ return vc.find(id).parentElement; },

	setStyle: function( component, style ){
		if( style != null ){
			var s = style.split(";");
			for( var i=0; i<s.length; i++ ){
				var x = s[i].split(":");
				component.style[x[0].trim()] = x[1].trim();
			}
		}		
	},

	size: function ( left, top, width, height ){ return 'left:'+left+'%; top:'+top+'%; max-width:'+width+'%; width:'+width+'%; max-height:'+height+'%; height:'+height+'%'; },
	
	fill: function(){ return vc.size(0,0,100,100); },

	raw: function( tag, id ){
		var c = document.createElement(tag)
		c.id = vc.jsId(id)
		return c
	},

	component: function( tag, id ){
		var c = vc.raw(tag,id)
		vc.setStyle( c, 'position:absolute; ' + vc.fill() )
		return c
	},

	cell: function( id ){ return vc.component( 'div', id ) },

	create: function( node ){
		var c = document.createElement('div')
		c.id = vc.jsId(node.id)
		var style = 'position:absolute'
		if( node.getAttribute('style')!=null ) style += ';' + node.getAttribute('style')
		vc.setStyle( c, style )
		return c
	},

	replace: function ( newC ){
		var oldC = vc.find( vc.id(newC.id) )
		var parent = oldC.parentElement
		if( parent.layout != 'flow' ){ vc.setStyle(newC, vc.fill()) }
		parent.replaceChild(newC,oldC)
		return newC
	},

	load: function( node ){ return vc.replace(vc.create(node)) }
}

// Global Unalcol object
unalcol = new Unalcol(window.location.href)
xml.load(unalcol.page.file, unalcol.plugins.loadDoc.bind(unalcol.plugins))
