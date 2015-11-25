package unalcol.types.collection.tree.bplus;

import unalcol.types.collection.tree.bplus.immutable.ImmutableBPlus;

public abstract class BPlusNode<T> implements MutableBPlusNode<T>{
    @Override 
    public int underFillSize(){
        return size()/3;
    }
    
    @Override
    public boolean underFill(){
        return n() < underFillSize();
    }

	@Override
	public boolean isFull() {
		return n()==size();
	}

	@SuppressWarnings("unchecked")
	public void checkFull(ImmutableBPlus<T> tree){
		if( this.isFull() ){
	    	BPlusInnerNode<T> parent = (BPlusInnerNode<T>)this.parent();
			BPlusNode<T> right = this.split();
			if( parent==null ){
				System.out.println("Root deal..");
				parent = (BPlusInnerNode<T>)this.newInstance(this.size());
				parent.add(this);
			}
			System.out.println("Pre-hellooooo");
			System.out.println(tree);
			parent.add(right, tree);
			System.out.println("hellooooo");
			System.out.println(tree);
			((BPlusNode<T>)parent).checkFull( tree );
		}
	}
	
    @SuppressWarnings("unchecked")
	public void checkEmpty( ImmutableBPlus<T> tree ){
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
