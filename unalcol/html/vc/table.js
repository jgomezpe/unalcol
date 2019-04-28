//
//Unalcol JavaScript Service structure Pack 1.0 by Jonatan Gomez-Perdomo
//
/**
*
* table.js
* <P>A table component for unalcol  
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

var table = unalcol.plugins.set.table

table.positions = function ( n, s ){
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
}

table.sizes = function ( pos ){
	var s = [];
	var n = pos.length-1;
	for( var i=0; i<n; i++ ) s.push(pos[i+1] - pos[i]);
	return s;
}


table.layout = function ( node ){
	var n = xml.childCount(node)
	var c = vc.load(node)
	c.layout = 'table'
	var width = node.getAttribute('widths');
	if( width != null ) width = width.split(' ');
	var height = node.getAttribute('heights');
	if( height != null ) height = height.split(' ');
	if( width == null && height == null ) return c;

	var left = [0];
	var top = [0];
	if( width != null ){
		for( var i=0; i<width.length; i++ ){
			width[i] = parseInt(width[i]);
			left.push(left[i]+width[i]);
		}
	}else{
		if( height != null ){
			left = table.positions(n,height.length);
			width = table.sizes( left );
		}else width = [100];
	}


	if( height != null ){
		for( var i=0; i<height.length; i++ ){
			height[i] = parseInt(height[i]);
			top.push(top[i]+height[i]);
		}
	}else{
		top = table.positions( n, width.length );
		height = table.sizes(top);
	}

	var i=0;
	var j=0;
	for( var k=0; k<n; k++){
		var child = xml.child(node,k)
		var cell = vc.cell( c.id+'-'+k )
		vc.setStyle( cell, vc.size(left[j], top[i], width[j], height[i]) )
		c.appendChild(cell)
		cell.appendChild( vc.cell( child.id ) )
		j++;
		if( j>=width.length ){
			j=0
			i++
		}
	}
	return c;
}

table.buildChildren = function( node, buildChild ){
	var n = xml.childCount(node)
	if( n>=1 ) for( var k=0; k<n; k++) buildChild(xml.child( node, k ))
}

table.run = function ( node ){
	var c = table.layout( node )
	table.buildChildren( node, unalcol.plugins.load.bind(unalcol.plugins) )
}
