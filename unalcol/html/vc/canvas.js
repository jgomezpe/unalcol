//
//Unalcol JavaScript Service structure Pack 1.0 by Jonatan Gomez-Perdomo
//
/**
*
* canvas.js
* <P>A basic canvas for unalcol  
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

// Canvas functions
var canvas = unalcol.plugins.set.canvas

canvas.used = {}

canvas.clear = function ( id ){
	var c, ctx, w, h;
	c = vc.find(id);
	w = c.width;
	h = c.height;
	ctx = c.getContext("2d");
	ctx.strokeRect(0,0,w,h);
}
	
canvas.init = function ( container, canvasId ){
	container.id = 'container-'+canvasId;
	var c = vc.component('canvas', canvasId);
	c.style.border = "1px solid #DCDCDC";
	container.appendChild(c)
	
	// Just for testing the canvas
	//var json = JSON.parse('{"command":"compound", "commands":[{"command":"line","x":[0,150],"y":[0,150]},{"command":"fillStyle", "x":[0,150], "y":[0,150], "endcolor":{"red":0,"green":0,"blue":255}, "startcolor":{"red":255,"green":0,"blue":0}},{"command":"polygon","x":[0,50,50,100],"y":[0,0,50,50]}]}');
	var json = {} 
	var obj = {parent:container, width:100, height:100, JSON:json, canvas:c};
	
	canvas.used[canvasId] = obj;
	unalcol.resizer.add( canvas.resize );
	return container;
}
	
canvas.redraw = function (){ for (x in canvas.used) canvas.command(x, canvas.used[x].JSON ); }
	
canvas.update = function ( canvasId ){
	var used = canvas.used[canvasId]
	c = used.canvas;
	d = used.parent;
	c.width = d.offsetWidth; 
	c.height = d.offsetHeight; 
	wM = used.width;
	wC = c.width;
	hM = used.height;
	hC = c.height;
	if( wC/wM < hC/hM ) used.scale = wC/wM; else used.scale = hC/hM;
}
	
canvas.resize = function () {
	for (x in canvas.used) canvas.update(x);
	canvas.redraw();
}
	
canvas.fit = function ( id, width, height ){
	var c = canvas.used[id];
	if(c!=null){ 
		c.width = width;
		c.height = height;
		canvas.update(id);
	}
}
	
canvas.getContext = function ( id ){
	c = canvas.used[id].canvas;
	return c.getContext("2d");
}

canvas.run = function ( node ){	canvas.init(vc.load(node),node.id) }
	
// Drawing functions 

canvas.image = function ( canvasId, obj ){
	var img = vc.find(obj.id);
	if( img==undefined || img==null ){
		img = vc.create('img', obj.id);
		img.src = obj.src;
		img.alt = 'Undefined';
	}   
     
	if( obj.rotate!=undefined && obj.rotate!=null && obj.rotate!=0 ) rotate = obj.rotate;
	else rotate=0;

	s = canvas.used[canvasId].scale;

	x = obj.x * s;
	y = obj.y * s;
	width = obj.width * s;
	height = obj.height * s;

	ctx = getContext( canvasId );	
     
	if( rotate!=0 ){
		ctx.save(); 
 
		var rx = x + width/2;
		var ry = y + height/2;
		ctx.translate(rx, ry);

		ctx.rotate(rotate * Math.PI/2);
 
		ctx.drawImage(img, -width/2, -height/2, width, height);
 
		ctx.restore();      
	}else ctx.drawImage(img, x, y, width, height);
}

canvas.compound = function (id, obj){
	var objs = obj.commands;
	for( var i=0; i<objs.length; i++ ) canvas.command( id, objs[i] );
}

canvas.beginPath = function (id, obj){ canvas.getContext( id ).beginPath(); }

canvas.closePath = function (id, obj){ canvas.getContext( id ).closePath(); }

canvas.moveTo = function (id, obj){
	ctx = canvas.getContext( id ); 
	s = canvas.used[id].scale;
	x = obj.x * s;
	y = obj.y * s;
	ctx.moveTo(x,y);
}

canvas.lineTo = function (id, obj){
	ctx = canvas.getContext( id ); 
	s = canvas.used[id].scale;
	x = obj.x * s;
	y = obj.y * s;
	ctx.lineTo(x,y);
}

canvas.quadTo = function ( id, obj ){
	ctx = canvas.getContext( id ); 
	s = canvas.used[id].scale;
	cp1x = obj.x[0] * s;
	cp1y = obj.y[0] * s;
	x = obj.x[1] * s;
	y = obj.y[1] * s;
	ctx.quadraticCurveTo(cp1x, cp1y, x, y); 
}

canvas.curveTo = function ( id, obj ){
	ctx = canvas.getContext( id ); 
	s = canvas.used[id].scale;
	cp1x = obj.x[0] * s;
	cp1y = obj.y[0] * s;
	cp2x = obj.x[1] * s;
	cp2y = obj.y[1] * s;
	x = obj.x[2] * s;
	y = obj.y[2] * s;
	this.ctx,bezierCurveTo(cp1x, cp1y, cp2x, cp2y, x, y); 
}

canvas.line = function( id, obj ){
	canvas.beginPath(id, obj);
	canvas.moveTo(id, {x:obj.x[0],y:obj.y[0]});
	canvas.lineTo(id, {x:obj.x[1],y:obj.y[1]});
	canvas.stroke(id,obj);
}

canvas.poly = function( id, obj ){
	canvas.beginPath(id, obj);
	px = obj.x;
	py = obj.y;
	canvas.moveTo(id, {x:px[0],y:py[0]});
	for( i=1; i<px.length; i++) canvas.lineTo(id, {x:px[i],y:py[i]});		
}

canvas.polyline = function( id, obj ){
	canvas.poly(id, obj);
	canvas.stroke(id,obj);
}

canvas.polygon = function( id, obj ){
	canvas.poly(id, obj);
	canvas.fill(id,obj);
}

canvas.rgb = function( obj ){ return "rgb("+obj.red+","+obj.green+","+obj.blue+")"; }

canvas.style = function( id, obj ){
	if( obj.color != null )	return canvas.rgb(obj.color);
	if( obj.startcolor == null ) return null;
	c1 = canvas.rgb(obj.startcolor);
	c2 = canvas.rgb(obj.endcolor);
	s = canvas.used[id].scale;
	ctx = canvas.getContext( id );
	if( obj.r != null ){
		r = obj.r * s;
		x = obj.x * s;
		y = obj.y * s;
		gradient = ctx.createRadialGradient(x, y, 1, x, y, r);
	}else{
		x1 = obj.x[0] * s;
		y1 = obj.y[0] * s;
		x2 = obj.x[1] * s;
		y2 = obj.y[1] * s;
		gradient = ctx.createLinearGradient(x1, y1, x2, y2);	
	}
	gradient.addColorStop("0", c1);			
	gradient.addColorStop("1", c2);
	return gradient;
}

canvas.strokeStyle = function( id, obj ){
	ctx = canvas.getContext( id ); 
	ctx.strokeStyle = canvas.style(id, obj);
	if( obj.lineWidth != null )	ctx.lineWidth = obj.lineWidth;
}

canvas.fillStyle = function( id, obj ){
	ctx = canvas.getContext( id ); 
	ctx.fillStyle = canvas.style(id, obj);
}

canvas.stroke = function( id, obj ){ canvas.getContext( id ).stroke(); }

canvas.fill = function( id, obj ){
	ctx = canvas.getContext( id );
	ctx.closePath();
	ctx.fill(); 
}

canvas.command = function ( id, obj ){
	var type = obj.command;
	if( type != null ) canvas[type](id, obj);
}

canvas.draw = function ( id, objText ){
	canvas.used[id].JSON = JSON.parse(objText); 
	canvas.command(id, canvas.used[id].JSON); 
}

/*

function arc( ctx, centerX, centerY, radius, startAngle, endAngle ){
	ctx.arc( centerX, centerY, radius, startAngle*Math.PI/180, endAngle*Math.PI/180, startAngle > endAngle ); 
}

function fillRect( ctx, x, y, width, height, color ){
	fill( ctx, color );
	ctx.fillRect(x, y, width, height); 
}

function rect( ctx, x, y, width, height ){ ctx.strokeRect(x, y, width, height); }

function ring( ctx, x, y, radius, thickness, startAngle, endAngle ){
	ctx.beginPath();
	arc( ctx, x, y, radius, startAngle, endAngle );
	arc( ctx, x, y, radius-thickness, endAngle, startAngle );
	ctx.closePath();
}

function fillRing( ctx, x, y, radius, thickness, startAngle, endAngle, color ){
	ring( ctx, x, y, radius, thickness, startAngle, endAngle );
	fill( ctx, color );
}

//Drawing commands as JSON objects
function drawArc( canvasId, obj ){
	s = canvas.set[canvasId].scale;
	ctx = getContext( canvasId );	
	ctx.beginPath();
	x = obj.x * s;
	y = obj.y * s;
	radius = obj.radius * s;
	arc( ctx, x, y, radius, obj.start, obj.end);
	stroke( ctx, obj.color );
}

function drawRect( canvasId, obj ){
	s = canvas.set[canvasId].scale;
	ctx = getContext( canvasId );	
	stroke( ctx, obj.color );
	x = obj.x * s;
	y = obj.y * s;
	width = obj.width * s;
	height = obj.height * s;
	rect( ctx, x, y, width, height );
}

function drawFillRect( canvasId, obj ){
	s = canvas.set[canvasId].scale;
	ctx = getContext( canvasId );	
	fill( ctx, obj.color );
	x = obj.x * s;
	y = obj.y * s;
	width = obj.width * s;
	height = obj.height * s;
	fillRect( ctx, x, y, width, height );
}

function drawRing( canvasId, obj ){
	s = canvas.set[canvasId].scale;
	x = scale(obj.x, s);
	y = scale(obj.y, s);
	radius = scale(obj.radius, s);
	thickness = scale(obj.thickness, s);
	ctx = getContext( canvasId );	
	ring( ctx, x, y, radius, thickness, obj.start, obj.end );
	stroke(ctx, obj.color);
}

function drawFillRing( canvasId, obj ){
	s = canvas.set[canvasId].scale;
	x = scale(obj.x, s);
	y = scale(obj.y, s);
	radius = scale(obj.radius, s);
	thickness = scale(obj.thickness, s);
	ctx = getContext( canvasId );	
	fillRing( ctx, x, y, radius, thickness, obj.start, obj.end, obj.color );
}
*/
