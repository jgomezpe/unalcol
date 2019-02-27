function xhb_notes( latex, node ){
	latex.innerHTML = node.childNodes[0].nodeValue;
	return latex;
}
