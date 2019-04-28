//
//Unalcol JavaScript Service structure Pack 1.0 by Jonatan Gomez-Perdomo
//
/**
*
* button.js
* <P>A button for unalcol  
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
var button = unalcol.plugins.set.button

button.group = []
	
button.size = 32

button.fa_style = 'position:initial; opacity:0.6; color:RoyalBlue; margin-top:3px'

button.img_style = 'position:initial; opacity:0.6; background-size:cover; background_repeat:no-repeat; padding:16px 16px; margin-top:2px; border:1px solid #A4A4A4'
	
button.resize = function(){
	for( var i=0; i<button.group.length; i++ ){
		b = button.group[i];
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
}

button.run = function ( node ){
	var image = node.getAttribute('image')
	var id = node.id
	var style = node.getAttribute('style')
	var container = vc.find(id)
	var bar = container.parentElement;
	var barId = vc.id(bar.id);
	if( window[barId]==null ) window[barId] = { id:barId };

	var newC

	if( image.substring(0,3) == 'fa ' || image.substring(0,4) == 'fas ' ){
		newC = vc.raw('i', image)
		vc.setStyle( newC, button.fa_style );
		newC.setAttribute('class',image);

	}else{
		newC = vc.raw('button',id)
		vc.setStyle( newC, button.img_style);
		newC.style.backgroundImage = 'url('+image+')';
	}

	vc.setStyle(newC, style);

	var opacity = newC.style.opacity;
	newC.onmouseover = function(){ newC.style.opacity=1; }
	newC.onmouseout = function(){ newC.style.opacity=opacity; }
	bar.replaceChild(newC,container);
	button.group.push(newC);
	button.resize();
	newC.setAttribute('onclick', barId+'.'+id+'()');

	bar.style.display = 'table-cell';
	bar.style.verticalAlign = 'middle';
}
	

button.init_fontawesome = function (){
	var fontAwesome = document.createElement('link');
	fontAwesome.rel="stylesheet";
	fontAwesome.href="https://use.fontawesome.com/releases/v5.7.0/css/all.css";
	fontAwesome.crossorigin="anonymous";		
	document.getElementsByTagName('head')[0].appendChild(fontAwesome);
}
	
button.init_fontawesome()
unalcol.resizer.add(button.resize)
