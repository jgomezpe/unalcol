package unalcol.service;

/**
 * <p>A Service definition. It is used for defining instance or class methods without
 * modifying or extending the class
 * (for example persistency, comparison, etc). See the ServiceLoader class</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class Service {
    /**
     * Service hierarchy
     */
    protected static ServiceProvider provider = new ServiceProvider();
    
    /**
     * Owner of the service
     */
    protected Object owner;
    
    /**
     * Returns the "Object" that owns the Service
     * @return The "Object" that owns the Service
     */
    public Object owner(){ return owner; };
    
    /**
     * Registers the service <i>service</i> to the object <i>owner</i>
     * @param owner Object that will own the service
     * @param service  Service to be associated to the <i>owner</i> object 
     */
    public static void register( Object owner, Service service ){
        service.owner = owner;
        provider.register(service);
    }
    
    /**
     * Registers the service type <i>cl</i> in the Service infra-structure
     * @param cl Type of service to be register
     * @return An <i>instance</i> of the registered class if possible, <i>null</i> if the type
     * of service cannot be registered
     */
    public static Service register( Class<?> cl ){
        return provider.register(cl);
    }
    
    /**
     * Sets the default service <i>service</i>, of type <i>cl</i>, to object <i>owner</i>
     * @param cl Type of service to be configured
     * @param owner Object that owns the service
     * @param service Default service that will be used by the object <i>owner</i>
     * @return An instance of the previously defined default service of such type
     */
    public static Service set( Class<?> cl, Object owner, Service service ){
        return provider.setDefault_service(cl, owner, service);
    }

    /**
     * Gets the instance of the service of type <i>cl</i> being used by object <i>owner</i>
     * @param cl Type of service to be returned
     * @param owner Object that owns the service
     * @return An instance of the service of the service of type <i>cl</i> being used by object <i>owner</i>
     * <i>null</i> if not available.
     */
    public static Service get( Class<?> cl, Object owner ){
        return provider.default_service(cl, owner);
    }
}