//
//Unalcol JavaScript Service structure Pack 1.0 by Jonatan Gomez-Perdomo
//
/**
*
* splitPage.js
* <P>A split page component for unalcol  
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

var splitBar = new PlugIn('splitBar')
splitBar.ready = true;
unalcol.plugins.set.splitBar = splitBar

splitBar.show = function( container ){
	var x = container.parentElement.style.width
	vertical = ( parseInt(x,10) < 96 );
	if( vertical ) container.style.cursor = "col-resize";
	else container.style.cursor = "row-resize";
	dragging = false;
	container.onmousedown = function(e){
		dragging = true;
		sx = e.pageX;
		sy = e.pageY;
	}
	container = container.parentElement
	var parent = container.parentElement;
	var rect = parent.getBoundingClientRect();
	var sx = 0;
	var sy = 0;
	var idx = 0;
	while( parent.children[idx] != container ) idx++;
	var prev = parent.children[idx-1]
	var next = parent.children[idx+1]
	var rectPrev = prev.getBoundingClientRect()
	var rectNext = next.getBoundingClientRect()

	function sizes( p, start, end, prop, maxProp ){
		if( p < rectPrev[start] ) p = rectPrev[start]
		else if( p > rectNext[end] ) p = rectNext[end]
		var l = rect[end]-rect[start]
		var p = 100*(p-rectPrev[start])/l
		if( p>=99.9 ) p = 99
		value = p + '%'
		prev.style[prop] = value
		prev.style[maxProp] = value
		container.style[start] = value
		p++;
		next.style[start] = p + '%'
		p = 100 - p
		value = p + '%'
		next.style[prop] = value
		next.style[maxProp] = value
	}

	function dragmove(e){
		if( dragging ){
			if( vertical ) sizes(e.pageX, 'left', 'right', 'width', 'maxWidth')
			else sizes( e.pageY, 'top', 'bottom', 'height', 'maxHeight' )
			unalcol.resizer.apply();
		}
	}
	function dragend(){ if( dragging ) dragging = false; }
	window.addEventListener("mousemove", function(e) {dragmove(e);});
	window.addEventListener("mouseup", dragend);
}

splitBar.run = function( node ){ splitBar.show( vc.load(node) ) }

var splitPage = unalcol.plugins.set.splitPage

splitPage.run = function ( node ){
	function insertSplitBar( attribute ){
		var tableNode = xml.createNode( 'table' )
		tableNode.id = node.id
		xml.copy( node, tableNode, 'style')
		var a = node.getAttribute(attribute).split(' ')
		var s = ''
		var x = ''
		var child = node.children[a.length-1]
		tableNode.appendChild(child)
		for( var i=a.length-1; i>0; i-- ){
			a[i] = parseInt(a[i]);
			x = '1 ' + (a[i]-1) + s + x;
			s = ' ';
			var bar = xml.createNode('splitBar');
			bar.id = 'split-'+node.id+'-'+i;
			style = node.getAttribute('bar');
			if( style != null ) bar.setAttribute('style', style );
			tableNode.insertBefore( bar, child )
			child = node.children[i-1]
			tableNode.insertBefore( child, bar )
		}
		x = a[0] + ' ' + x; 
		tableNode.setAttribute(attribute,x);
		return tableNode
	}
	if( node.getAttribute('widths') != null ) node = insertSplitBar('widths')
	else node = insertSplitBar('heights')
	unalcol.plugins.load(node)
}
