package unalcol.collection.tree.bplus;

import unalcol.collection.tree.bplus.immutable.ImmutableBPlus;
import unalcol.collection.tree.bplus.immutable.ImmutableInnerNode;
import unalcol.collection.tree.bplus.immutable.ImmutableNode;

public interface BPlusNode<T> extends ImmutableNode<T>{    
	// Size 
    public abstract void setn( int n );
    
    // Parent
    public abstract void setParent( ImmutableInnerNode<T> node );
    
    // Siblings    
    public abstract void setLeft( ImmutableNode<T> node );
    public abstract void setRight( ImmutableNode<T> node );

	public default void checkFull(ImmutableBPlus<T> tree){
		if( this.isFull() ){
	    	BPlusInnerNode<T> parent = (BPlusInnerNode<T>)this.parent();
			BPlusNode<T> right = this.split();
			if( parent==null ){
				parent = (BPlusInnerNode<T>)this.newInstance(this.size());
				parent.add(this);
			}
			parent.add(right, tree);
			((BPlusNode<T>)parent).checkFull( tree );
		}
	}
	
	public default void checkEmpty( ImmutableBPlus<T> tree ){
    	if( this.underFill() ){
        	BPlusInnerNode<T> parent = (BPlusInnerNode<T>)this.parent();
    		if( parent==null && n()==0){
    			((BPlus<T>)tree).clear();
    			return;
    		} 
    		BPlusNode<T> left = (BPlusNode<T>)this.left(); 
    		if(left != null ){
    			if( left.n() + this.n() <= (this.underFillSize()<<1) ){    		
	    			((BPlusNode<T>)left).merge();    			
	    			if( parent != null ){
	    				parent.remove(this, tree);
	    				((BPlusNode<T>)parent).checkEmpty( tree );
	    			}
    			}else{
    				left.rightShift();
    			}	
    		}else{
	    		BPlusNode<T> right = (BPlusNode<T>)this.right(); 
	    		if(right != null ){
	    			if( right.n()+this.n() <= (this.underFillSize()<<1)){		    		
		    			this.merge();
		    			parent = (BPlusInnerNode<T>)right.parent();
		    			if( parent != null){
		    				parent.remove(right, tree);
		    				((BPlusNode<T>)parent).checkEmpty( tree );
		    			}
	    			}else{
	    				right.leftShift();
	    			}	
	    		}
    		}
    	}
	}	

    // Balance
    public abstract void leftShift();
    public abstract void rightShift();
    public abstract void merge();
    public abstract BPlusNode<T> split();       
}