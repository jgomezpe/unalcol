package evolution;

public class Node {
	protected String oper;
	protected Node left;
	protected Node right;
	protected Node parent;
	
	public Node( Node parent, int h ){
		this.parent = parent;
		if( h==0 ){
			//@TODO: oper = variable o numero
			left = null;
			right = null;
		}else{
			//@TODO: oper = + o *
			left = new Node( this, h-1 );
			right = new Node( this, h-1 );
		}
	}
	
	public Node( Node parent, String oper ){
		this.oper = oper;
		left = null;
		right = null;
		this.parent = parent;
	}
	
	public Node( Node parent, String oper, Node left, Node right ){
		this.oper = oper;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}	
	
	public Node clone( Node parent ){
		Node n = new Node(parent, oper);
		if( !isLeaf() ){
			n.left = left.clone(n);
			n.right = right.clone(n);
		}
		return n;
	}
	
	public boolean isLeaf(){
		return left == null; 
	}
	
	public int weight(){
		if( isLeaf() ) return 1;
		return 1 + left.weight() + right.weight();
	}
	
	public Node get( int index ){
		if( index == 0 ) return this;
		index--;
		int wLeft = left.weight();
		if( index < wLeft ){
			return left.get(index);
		}else{
			return right.get(index-wLeft);
		}
	}
	
	public int evaluate( int x, int y){
		switch( oper.charAt(0) ){
			case '*':
				return left.evaluate(x,y) * right.evaluate(x,y);
			case '+': 
				return left.evaluate(x,y) + right.evaluate(x,y);
			case 'X': 
				return x;
			case 'Y': 
				return y;
			default:
				return Integer.parseInt(oper);
		}
	}
}
