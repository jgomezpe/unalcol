//
//Unalcol JavaScript Service structure Pack 1.0 by Jonatan Gomez-Perdomo
//
/**
*
* buttonbar.js
* <P>A buttonbar for unalcol  
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
button={
	init_fontawesome: function (){
		var fontAwesome = document.createElement('link');
		fontAwesome.rel="stylesheet";
		fontAwesome.href="fontawesome/css/font-awesome.css";
		document.getElementsByTagName('head')[0].appendChild(fontAwesome);
		var css = '.btn { background-color: DodgerBlue; border: none; color: white; padding: 12px 16px;	font-size: 16px; cursor: pointer; } .btn:hover { background-color: RoyalBlue;	}';
		var style = document.createElement('style');
		if (style.styleSheet) style.styleSheet.cssText = css;
		else style.appendChild(document.createTextNode(css));
		document.getElementsByTagName('head')[0].appendChild(style);
	},
	
	make: function ( container, id, image ){
		var p = container.parentElement;
		var pId = p.id.substring(vcl.js_tag.length);
		if( window[pId]==null ) window[pId] = { id:pId };
		if( image.substring(0,3) == 'fa ' ){
			var c = vcl.createChildFlow('button',id);
			var k = xml.childById(p, container.id);
			p.replaceChild(c,p.children[k]);
			container = c;
			container.setAttribute('class','btn');
			var i  = document.createElement('i');
			i.setAttribute('class',image);
			i.id = image;
			container.appendChild(i); 
		}else{
			var c = vcl.createChildFlow('button',id);
			var k = xml.childById(p, container.id);
			p.replaceChild(c,p.children[k]);
			container = c;
			c.setAttribute('class','btn');
			
			c.style.backgroundImage = 'url('+image+')';
			c.style.backgroundSize = 'cover';
			c.style.backgroundRepeat='no-repeat';
			c.style.padding = '16px 16px';
			c.style.border = '1px solid #A4A4A4';

//				var i  = document.createElement('i');
//				container.appendChild(i); 

//				var xn = createXMLNode('image');
//				xn.setAttribute('id','img-'+id);
//				xn.setAttribute('src',image);
//				gui(container,xn);
		}
		container.setAttribute('onclick', id+'_button()');

		return container;
	},
	
	load: function ( container, node ){ return button.make( container, node.id, node.getAttribute('image') ); }
}

button.init_fontawesome();


// buttonbar

buttonbar={
	show: function ( container, id ){
		script.addVC( id, null);
		return container;
	},

	load: function ( container, node ){ return buttonbar.show( container, node.id ); }
}