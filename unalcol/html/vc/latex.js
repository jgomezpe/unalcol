//
//Unalcol JavaScript Service structure Pack 1.0 by Jonatan Gomez-Perdomo
//
/**
*
* canvas.js
* <P>A latex render for unalcol   (based on MathJax)
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

if(window.MathJax==null || MathJax == null ){
	unalcol.script.addAsync( "text/x-mathjax-config", 
							 "MathJax.Hub.Config({tex2jax: {inlineMath: [['$','$'], ['\\\\(','\\\\)']],processClass: 'mathjax', ignoreClass: 'no-mathjax'}});",
							 'x-mathjax-config' );
	unalcol.script.addJS("https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.1/MathJax.js?config=TeX-AMS_CHTML", 'mathjax', null, null ); 
}else{	MathJax.Hub.Queue(["Typeset",MathJax.Hub]); }

var latex = unalcol.plugins.set.latex

latex.render = function ( container, latex_code ){
	latex.init( container );
	container.innerHTML = latex_code;
},

latex.init = function ( container ){
	document.getElementsByTagName('body')[0].setAttribute('class','no-mathjax')
	container.setAttribute('class','mathjax')
	container.style.overflowX = 'scroll'
	container.style.overflowY = 'scroll'
	container.style.fontSize = '1.3vw'
}	

latex.run = function ( node ){ latex.render( vc.load(node), node.childNodes[0].nodeValue ); }

