package unalcol.types.collection.tree.bplus;

import unalcol.types.collection.tree.bplus.immutable.ImmutableBPlus;

public abstract class BPlusNode<T> implements MutableBPlusNode<T>{
    @Override 
    public int underFillSize(){
        return size()/3;
    }
    
    @Override
    public boolean underFill(){
        return n() <= underFillSize();
    }

	@Override
	public boolean isFull() {
		return n()==size();
	}

    
    @SuppressWarnings("unchecked")
	public void balance( ImmutableBPlus<T> tree ){
    	BPlusInnerNode<T> parent = (BPlusInnerNode<T>)this.parent();
    	if( this.underFill() ){
    		if( parent==null ) return;
    		BPlusNode<T> left = (BPlusNode<T>)this.left(); 
    		if(left != null ){
    			if( left.n()+this.n() <= (this.underFillSize()<<1)){    		
	    			((BPlusNode<T>)left).merge();    			
	    			if( parent != null ){
	    				parent.remove(this, tree);
	    				((BPlusNode<T>)parent).balance( tree );
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
		    				((BPlusNode<T>)parent).balance( tree );
		    			}
	    			}else{
	    				right.leftShift();
	    			}	
	    		}
    		}
    	}else{
    		if( this.isFull() ){
    			if( this.parent()==null ){
    				parent = (BPlusInnerNode<T>)this.newInstance(this.size());
    				this.setParent(parent);
    				parent.add(this, tree);
    			}
    			BPlusNode<T> right = this.split();
    			parent.add(right, tree);
    			((BPlusNode<T>)parent).balance( tree );
    		}
    	}
	}	

    // Balance
    public abstract void leftShift();
    public abstract void rightShift();
    public abstract void merge();
    public abstract BPlusNode<T> split();    
    
}
