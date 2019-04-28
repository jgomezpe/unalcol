checkbox = unalcol.plugins.set.checkbox

checkbox.show = function ( container, id ){
	var c = vc.component( 'input', 'cb-'+id )
	c.setAttribute('type','checkbox')
	c.style.fontSize = '1.3vw'
	container.appendChild(c)
}

checkbox.run = function ( node ){ checkbox.show( vc.load(node), node.id ) }
