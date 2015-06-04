package unalcol.evolution.provider;
import unalcol.evolution.*;
import unalcol.reflect.service.*;
import unalcol.reflect.util.*;

/**
 * <p>Title: GrowingFunctionProvider</p>
 * <p>Description: Growing Function provider. It is for compatibility with
 * the service provider infra-structure</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez
 * @version 1.0
 */

public class GrowingFunctionProvider {

    public static GrowingFunction[] get(){
        try{
           Service[] services = ReflectUtil.getProvider().
                   owned_services(GrowingFunction.class, Object.class);
           GrowingFunction[] tr = new GrowingFunction[services.length];
           for( int i=0; i<services.length; i++ ){
               tr[i] = (GrowingFunction)services[i];
           }
           return tr;
        }catch( Exception e ){
            e.printStackTrace();
        }
        return new GrowingFunction[0];
    }

    public static GrowingFunction get( Object obj ){
        try{
            return ((GrowingFunction)
                    ReflectUtil.getProvider().
                    default_service(GrowingFunction.class,obj));
        }catch( Exception e ){
        }
        return null;
    }
}
