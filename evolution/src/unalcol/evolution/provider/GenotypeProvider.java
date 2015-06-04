/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package unalcol.evolution.provider;
import unalcol.evolution.Genotype;
import unalcol.reflect.util.*;

/**
 * <p>Title: GenotypeProvider</p>
 * <p>Description: Genotype provider. It is for compatibility with
 * the service provider infra-structure</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class GenotypeProvider{
    public static Genotype getService( Object obj ){
        try{
            return ((Genotype)ReflectUtil.getProvider().default_service(Genotype.class,obj));
        }catch( Exception e ){
        }
        return null;
    }

    public static Object get( Object obj ){
        Genotype service = getService(obj);
        if( service != null ){
            return service.get();
        }
        return null;
    }
}