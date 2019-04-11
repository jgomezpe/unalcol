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

splitBar={
	show: function( container, id ){
		var jsId = vc.jsId(id);
		var x = container.style.width;
		vertical = ( parseInt(x,10) < 96 );
		if( vertical ) container.style.cursor = "col-resize";
		else container.style.cursor = "row-resize";
		dragging = false;
		var parent = container.parentElement;
		var rect = parent.getBoundingClientRect();
		var sx = 0;
		var sy = 0;
		var idx = parent.children.length - 1;
		

		container.onmousedown = function(e){
			dragging = true;
			sx = e.pageX;
			sy = e.pageY;
		}
		function dragmove(e){
			var left = parent.children[idx-1];
			var right = parent.children[idx+1];
			var rectLeft = left.getBoundingClientRect();
			var rectRight = right.getBoundingClientRect();
			if( dragging ){
				if( vertical ){
					var px = e.pageX;
					if( px < rectLeft.left ) px = rectLeft.left
					else if( px > rectRight.right ) px = rectRight.right;
					var l = rect.right-rect.left;
					var p = 100*(px-rectLeft.left)/l;
					trace(p); 
					if( p>=99.9 ) p = 99;
					left.style.width = p + '%';
					left.style.maxWidth = p + '%';
					container.style.left = left.style.width;
					p++;
					right.style.left = p + '%';
					p = 100 - p;
					right.style.width = p + '%';
					right.style.maxWidth = p + '%';
				}else{
					var dy = sy - e.pageY;
				}
			}
		}
		function dragend(){
			if( dragging ){
				trace('released'); 
				dragging = false;
			}
		}
		window.addEventListener("mousemove", function(e) {dragmove(e);});
		window.addEventListener("mouseup", dragend);
		container.style.border = '1px solid #A4A4A4';
		return container;
	},

	load: function( container, node ){
		return splitBar.show( container, node.id ); 
	}
}

splitPage={
	show: function ( container, id ){
		container.id = vc.jsId(id);
		return container;
	},

	addChildren: function ( comp, node ){
		var width = node.getAttribute('widths');
		var s = '';
		var x = '';
		if( width != null ){
			width = width.split(' ');
			for( var i=width.length-1; i>0; i-- ){
				width[i] = parseInt(width[i]);
				x = '1 ' + (width[i]-1) + s + x;
				s = ' ';
				var newNode = xml.createNode('splitBar');
				newNode.id = 'split-'+node.id+'-'+i;
				node.insertBefore( newNode, node.children[i] );
			}
			x = width[0] + ' ' + x; 
//			x = width;
			node.setAttribute('widths',x);
		}else{
			var height = node.getAttribute('heights');
			height = height.split(' ');
			for( var i=height.length-1; i>0; i-- ){
				height[i] = parseInt(height[i]);
				x = '1 ' + (height[i]-1) + s + x;
				s = ' ';
				var newNode = xml.createNode('splitBar');
				newNode.id = 'split-'+node.id+'-'+i;
				node.insertBefore( newNode, node.children[i] );
			}
			x = height[0] + ' ' + x;
			node.setAttribute('heights',x);			
		}
		return vc.addChildrenTable(comp, node);		
	},

	load: function ( container, node ){
		return splitPage.show( container, node.id ); 
	}
}
