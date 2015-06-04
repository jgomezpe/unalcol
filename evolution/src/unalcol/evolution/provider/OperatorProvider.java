package unalcol.evolution.provider;
import unalcol.optimization.operators.Operator;
import unalcol.reflect.util.*;
import unalcol.reflect.service.*;

/**
 * <p>Title: OperatorProvider</p>
 * <p>Description: Operator provider. It is for compatibility with
 * the service provider infra-structure</p>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: Kunsamu</p>
 * @author Jonatan Gomez
 * @version 1.0
 */
public class OperatorProvider{
    public static Operator[] get( Object obj ){
           Service[] services = ReflectUtil.getProvider().owned_services(Operator.class, obj);
           Operator[] opers = new Operator[services.length];
           for( int i=0; i<services.length; i++ ){
               System.out.println("Opers..." + services[i].getClass().getName());
               opers[i] = (Operator)services[i];
           }
           return opers;
    }
}