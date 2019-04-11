mchoice={
	addCSS: function (){
		var link = document.createElement('link');
		link.id = 'mchoiceCSS';
		link.rel = 'stylesheet';
		link.href = 'css/mchoice.css';
		document.head.appendChild(link);
	},
	
	load: function ( container, node ){
		//mchoice.addCSS();
		if( container.tagName != 'form' ){
			var c = document.createElement('form');
			container.parentElement.replaceChild( c, container); 
			container = c;
		}
		container.setAttribute('action','');
		vc.setChild( container, 'div', node.id, 0, 0, 100, 100 );
		return container;
	},
	
	radiobutton: function ( container, group, id ){
		container.setAttribute('class', 'container');
		var rb = vc.addChild(container, 'input', id, 0, 0, 100, 10 );
		rb.style.fontSize = '1.3vw';
		rb.setAttribute('type','radio');
		rb.setAttribute('name', group);
		var span = document.createElement('span');
		span.setAttribute('class', 'checkmark');
		container.appendChild(span);
		return rb;
	},
	
	checkbox: function ( container, id ){
		var cb = vc.addChild(container, 'input', id, 0, 0, 100, 10 );
		cb.setAttribute('type','checkbox');
		cb.style.fontSize = '1.3vw';
		//container.setAttribute('class', 'container');
		return cb;
	},
	
	buildCheckboxDiv: function ( container, node ){
		mchoice.checkbox( container, node.id );
		var c = vc.addDiv( container, 'data-'+node.id, 0, 10, 100, 90 );
		vc.init(c,node);
		container.style.border = "1px solid #DCDCDC";
		container.style.fontSize = '1.3vw';
		return container;
	},
	
	buildRadiobuttonDiv: function ( container, node ){
		mchoice.radiobutton( container, node.parentElement.id, node.id );
		var c = vc.addDiv( container, 'data-'+node.id, 0, 10, 100, 90 );
		vc.init(c,node);
		container.style.border = "1px solid #DCDCDC";
		container.style.fontSize = '1.3vw';
		return container;
	},
	
	addChildren: function ( comp, node ){
		var multiple = node.getAttribute('type');
		if( multiple == null ) multiple = 'single';
		var f = mchoice.buildRadiobuttonDiv;
		if( multiple == 'multiple' ) f = mchoice.buildCheckboxDiv;
		return vc.genericAddChildrenTable(comp, node, f);
	}
}
