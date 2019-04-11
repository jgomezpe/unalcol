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
		
	set : [],
	
	size : 32,

	fa_style : 'opacity:0.6; color:RoyalBlue',
	img_style : 'opacity:0.6; background-size:cover; background_repeat:no-repeat; padding:16px 16px; border:1px solid #A4A4A4',
	
	resize: function(){
		for( var i=0; i<button.set.length; i++ ){
			b = button.set[i];
			parent = b.parentElement;
			w = parent.clientWidth;
			h = parent.clientHeight;
			s = w;
			if( s > h ) s = h;
			if( button.size < s ) s = button.size;
			if( b.tagName.toLowerCase()=='button'){
				s = s/2;
				b.style.padding = s+"px "+s+"px";
			}else{
				b.style.fontSize = s+"px";
			}
		}
	},

	init_fontawesome: function (){
		var fontAwesome = document.createElement('link');
		fontAwesome.rel="stylesheet";
		fontAwesome.href="https://use.fontawesome.com/releases/v5.7.0/css/all.css";
		fontAwesome.crossorigin="anonymous";		
		document.getElementsByTagName('head')[0].appendChild(fontAwesome);
	},
	
	make: function ( container, id, image, style ){
		var p = container.parentElement;
		var pId = p.id.substring(vc.js_tag.length);
		if( window[pId]==null ) window[pId] = { id:pId };

		if( image.substring(0,3) == 'fa ' ){ //|| image.substring(0,3) == 'fas ' ){
			var k = xml.childById(p, container.id);
			container = document.createElement('i');
			container.id = image;
			container.setAttribute('class',image);
			container.style=button.fa_style;

		}else{
			var k = xml.childById(p, container.id);
			container = vc.createChildFlow('button',id);
			container.style=button.img_style;
			container.style.backgroundImage = 'url('+image+')';
		}

		if( style != null ){
			var s = style.split(";");
			for( var i=0; i<s.length; i++ ){
				var x = s[i].split(":");
				container.style[x[0].trim()] = x[1].trim();
			}
		}

		var opacity = container.style.opacity;
		container.onmouseover=function(){ container.style.opacity=1; }
		container.onmouseout=function(){ container.style.opacity=opacity; }
		p.replaceChild(container,p.children[k]);
		button.set.push(container);
		button.resize();
		container.setAttribute('onclick', pId+'.'+id+'()');

		p.style.display='table-cell';
		p.style.verticalAlign='middle';

		return container;
	},
	
	load: function ( container, node ){ return button.make( container, node.id, node.getAttribute('image'), node.getAttribute('style') ); }
}

button.init_fontawesome();


// buttonbar

buttonbar={
	load: function ( container, node ){
		resizer.add(button.resize);
		return container; 
	}
}
