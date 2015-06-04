package service;

import unalcol.clone.Clone;
import unalcol.clone.CloneWrapper;
import unalcol.clone.ShallowClone;
import unalcol.reflect.Core;

/**
 * <p>Service infra-structure test.</p>
 *
 * <p>Copyright: Copyright (c) 2010</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class ServiceTest {
    /**
     * Test the clone service infrastructure
     */
    public static void cloneTest(){
        String s = "Comparing the two copy methods";
        String cs = (String)Clone.create(s);
        System.out.println("Value of cs:"+cs);
        System.out.println("Is it a Shallow copy?"+(cs==s));        
        Clone<?> shallow = ShallowClone.register();
        Clone<?> def = Clone.set(Object.class, shallow);
        System.out.println("Previously define default clone service:"+def.getClass().getName());
        cs = (String)Clone.create(s);
        System.out.println("Value of cs:"+cs);
        System.out.println("Is it a shallow copy?"+(cs==s));        
        def = Clone.set(Object.class, shallow);
        System.out.println("Previously define default clone service:"+def.getClass().getName());
    }
    
    /**
     * Test the clone service infrastructure
     */
    public static void coreTest(){    	
        Clone<Object> def = CloneWrapper.register();
        Clone<Object> shallow = ShallowClone.register();
        Core.set(Object.class, Clone.class, def);
        String s = "Comparing the two copy methods";
        Clone<Object> cl = (Clone<Object>)Core.get(String.class, Clone.class);
        System.out.println("clone service:"+cl);
        String cs = (String)cl.clone(s);
        System.out.println("Value of cs:"+cs);
        System.out.println("Is it a Shallow copy?"+(cs==s));        
        Core.set(String.class, Clone.class, shallow);
        cl = (Clone<Object>)Core.get(String.class, Clone.class);
        System.out.println("String clone service:"+cl);
        cs = (String)cl.clone(s);
        System.out.println("Value of cs:"+cs);
        System.out.println("Is it a shallow copy?"+(cs==s));        
    }
    
    public static void main( String[] args ){
//        cloneTest();
    	coreTest();
    }    
}
