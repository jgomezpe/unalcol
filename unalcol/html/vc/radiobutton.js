radiobutton = unalcol.plugins.set.radiobutton

radiobutton.show = function ( container, group, id ){
	container.setAttribute('class', 'container')
	var c = vc.component('input', 'rb-'+id)
	container.appendChild(c)
	c.style.fontSize = '1.3vw'
	c.setAttribute('type','radio')
	c.setAttribute('name', group)
	var span = document.createElement('span')
	span.setAttribute('class', 'checkmark')
	container.appendChild(span)
}
	
radiobutton.run = function ( node ){ radiobutton.show( vc.load(node), node.getAttribute('group'), node.id ) }
