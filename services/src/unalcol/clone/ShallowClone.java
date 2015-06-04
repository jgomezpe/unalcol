package unalcol.clone;

/**
 * <p>Shallow Cloning service. Just returns the same object reference</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class ShallowClone extends Clone<Object>{
    static {
       register(Object.class, new ShallowClone());
    }
    
    @SuppressWarnings("unchecked")
	public static Clone<Object> register(){
        return (Clone<Object>)register(ShallowClone.class);
    }
    
   /**
     * Returns the same object (shallow copy)
     * @param obj Object from which the CloneService will be retrieved
     * @return A Clone (the same object) of the object
     */
    public Object clone(Object obj){
    	System.out.println("###");
        return obj;
    }

    public Object owner(){
        return Object.class;
    }
}
