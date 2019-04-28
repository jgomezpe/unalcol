mchoice = unalcol.plugins.set.mchoice

mchoice.run = function ( node ){
	var type = node.getAttribute('type')
	var single = ( type == null || type == 'single' )
	var tableNode = xml.createNode( 'table' )
	tableNode.id = node.id

	xml.copy(node, tableNode, 'style')
	xml.copy(node, tableNode, 'widths')
	xml.copy(node, tableNode, 'heights')

	var n = node.children.length
	for( var i=0; i<n; i++ ){
		var xchild = node.children[0]
		var child = xml.createNode('table')
		child.id = 'cell-' + xchild.id

		child.setAttribute('style','border:1px solid #DCDCDC; font-size:1.3vw')
		child.setAttribute('heights','10 90')
		if( single ){
			c = xml.createNode('radiobutton')
			c.setAttribute('group', node.id )
		}else c = xml.createNode('checkbox')
		c.id = xchild.id
		xchild.id = 'mchoice-' + xchild.id
		child.appendChild(c)
		child.appendChild(xchild)
		tableNode.appendChild(child)
	}
	unalcol.plugins.load(tableNode)
}
