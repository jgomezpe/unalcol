/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.solution;

import unalcol.reflect.tag.TaggedObject;
import unalcol.search.Goal;

/**
 *
 * @author Jonatan
 */
public class Solution<T> extends TaggedObject<T>{
	public Solution(T object) {
		super(object);
	}
	
	public Solution(T object, @SuppressWarnings("rawtypes") Goal g ) {
		super(object);
		set(Goal.class.getName(), g);
	}
	
	@Override
	public Solution<T> instance( T object ){
		return new Solution<T>(object);
	}	
}