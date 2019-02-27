mchoice={
	addCSS: function (){
		var link = document.createElement('link');
		link.id = 'mchoiceCSS';
		link.rel = 'stylesheet';
		link.href = 'css/mchoice.css';
		document.head.appendChild(link);
	},
	
	load: function ( container, node ){
		mchoice.addCSS();
		if( container.tagName != 'form' ){
			var c = document.createElement('form');
			container.parentElement.replaceChild( c, container); 
			container = c;
		}
		container.setAttribute('action','');
		vcl.setChild( container, 'div', node.id, 0, 0, 100, 100 );
		return container;
	},
	
	radiobutton: function ( container, group, id ){
		container.setAttribute('class', 'container');
		var rb = vcl.addChild(container, 'input', id, 0, 0, 100, 10 );
		rb.setAttribute('type','radio');
		rb.setAttribute('name', group);
		var span = document.createElement('span');
		span.setAttribute('class', 'checkmark');
		container.appendChild(span);
		return rb;
	},
	
	checkbox: function ( container, id ){
		var cb = vcl.addChild(container, 'input', id, 0, 0, 100, 10 );
		cb.setAttribute('type','checkbox');
		//container.setAttribute('class', 'container');
		return cb;
	},
	
	buildCheckboxDiv: function ( container, node ){
		mchoice.checkbox( container, node.id );
		var c = vcl.addDiv( container, 'data-'+node.id, 0, 10, 100, 90 );
		vcl.gui(c,node);
		container.style.border = "solid #DCDCDC";
		return container;
	},
	
	buildRadiobuttonDiv: function ( container, node ){
		mchoice.radiobutton( container, node.parentElement.id, node.id );
		var c = vcl.addDiv( container, 'data-'+node.id, 0, 10, 100, 90 );
		vcl.gui(c,node);
		container.style.border = "solid #DCDCDC";
		return container;
	},
	
	addChildren: function ( comp, node ){
		var multiple = node.getAttribute('type');
		if( multiple == null ) multiple = 'single';
		var f = mchoice.buildRadiobuttonDiv;
		if( multiple == 'multiple' ) f = mchoice.buildCheckboxDiv;
		return vcl.genericAddChildrenTable(comp, node, f);
	}
}