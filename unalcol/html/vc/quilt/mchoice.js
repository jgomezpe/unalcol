trace('[mchoice]');

function unalcol_mchoice( container, node ){
	if( container.tagName != 'form' ){
		var c = document.createElement('form');
		container.parentElement.replaceChild( c, container); 
		container = c;
	}
	container.setAttribute('action','');
	vcl.setChild( container, 'div', node.id, 0, 0, 100, 100 );
	return container;
}

function radiobutton( container, group, id ){
	var rb = vcl.addChild(container, 'input', id, 0, 0, 100, 10 );
	rb.setAttribute('type','radio');
	rb.setAttribute('name', group);
	return rb;
}

function checkbox( container, id ){
	var cb = vcl.addChild(container, 'input', id, 0, 0, 100, 10 );
	cb.setAttribute('type','checkbox');
	return cb;
}

function buildCheckboxDiv( container, node ){
	checkbox( container, node.id );
	var c = vcl.addDiv( container, 'data-'+node.id, 0, 10, 100, 90 );
	vcl.gui(c,node);
	container.style.border = "solid #000000";
	return container;
}

function buildRadiobuttonDiv( container, node ){
	radiobutton( container, node.parentElement.id, node.id );
	var c = vcl.addDiv( container, 'data-'+node.id, 0, 10, 100, 90 );
	vcl.gui(c,node);
	container.style.border = "solid #000000";
	return container;
}

function addChildren_mchoice( comp, node ){
	var multiple = node.getAttribute('type');
	if( multiple == null ) multiple = 'single';
	var f = buildRadiobuttonDiv;
	if( multiple == 'multiple' ) f = buildCheckboxDiv;
	return vcl.genericAddChildrenTable(comp, node, f);
}
