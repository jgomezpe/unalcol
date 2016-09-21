/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unalcol.search.solution;

import java.util.Hashtable;

import unalcol.reflect.tag.TaggedObject;
import unalcol.search.Goal;
import unalcol.search.space.Space;

/**
 *
 * @author Jonatan
 */
public class Solution<T> extends TaggedObject<T>{
	public Solution(T object) {
		super(object);
	}
	
	public Solution(T object, Hashtable<String, Object> tags, boolean copyAllTags ){
		super( object, tags, copyAllTags );
	}
	
	public Solution(T object, @SuppressWarnings("rawtypes") Goal g ) {
		super(object);
		set(Goal.class.getName(), g);
	}
	
	public void repair(Space<T> space){
		object = space.repair(object);
		this.removeNonTaggedMethods();
	}	
}