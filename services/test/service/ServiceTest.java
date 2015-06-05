package service;

import unalcol.clone.Clone;
import unalcol.clone.ShallowClone;
import unalcol.tracer.ConsoleTracer;
import unalcol.tracer.Tracer;

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
    public static void coreTest(){    	
        Clone<Object> shallow = new ShallowClone();
        String s = "Comparing the two copy methods";
        String cs = (String)Clone.create(s);
        
        ConsoleTracer tracer = new ConsoleTracer();
        Tracer.addTracer(String.class, tracer);
        Tracer.trace(cs, "Value of cs:"+cs);
        Tracer.trace(cs, "Is it a Shallow copy?"+(cs==s));        
        Clone.set(String.class, shallow);
        cs = (String)Clone.create(s);
        Tracer.trace(cs, "Value of cs:"+cs);
        Tracer.trace(cs, "Is it a shallow copy?"+(cs==s));        
    }
    
    public static void main( String[] args ){
//        cloneTest();
    	coreTest();
    }    
}
