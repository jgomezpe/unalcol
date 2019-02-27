//
//Unalcol JavaScript Service structure Pack 1.0 by Jonatan Gomez-Perdomo
//
/**
*
* youtube.js
* <P>A youtube video player for unalcol  
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

youtube={
	set:[],
	state:'unloaded',

	load: function ( container, node ){ return youtube.play(container,node.id); },
	
	play: function ( container, id ){
		vcl.setChild(container, 'div', 'player'+id, 1, 1, 98, 98);
		window[vcl.jsId('player'+id)] = 'video';
		youtube.set.push(id);
		if( youtube.state=='unloaded' ){
			youtube.state = 'loading';
			script.add(null,"https://www.youtube.com/iframe_api", null, true, null, null);
		}else if( youtube.state=='loaded') unalcol.loadVector( youtube.set, youtube.loadSingleVideo );
		return container;
	},

	loadSingleVideo: function ( id ){
		window[vcl.jsId('player'+id)] = new YT.Player(vcl.jsId('player'+id), {
	  		videoId: id,
	  		playerVars: {rel: 0, fs:0, modestbranding:1},
	  		events: {
	 	   		'onReady': youtube.onPlayerReady,
	 	   		'onStateChange': youtube.onPlayerStateChange
	 	 	}
		});
	},

	onPlayerReady: function (event){},

	onPlayerStateChange: function (event){ if (event.data == YT.PlayerState.PLAYING) {} }
}

function onYouTubeIframeAPIReady(){ 
	youtube.state='loaded';
	unalcol.loadVector( youtube.set, youtube.loadSingleVideo ); 
}