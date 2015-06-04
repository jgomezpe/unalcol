package unalcol.service;

/**
 * <p>The Service Hierarchy infra-structure. Provides services for
 * objects and classes</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class ServiceProvider {
    /**
     * Root node of the service hierarchy infra-structure
     */
    protected ServiceNode root;

    /**
     * Creates a service tree (any kind of service)
     */
    public ServiceProvider(){
        this.root = new ServiceNode( Service.class );
    }

    /**
     * Registers a service in the service hierarchy infra-structure
     * @param service Service to be registered
     * @return <i>true</i> if the service was registered, <i>false</i> otherwise
     */
    public boolean register( Service service ){
        if( service != null ){
            ServiceNode sn = root.locate(service.getClass());
            if( sn == null ){
                sn = add( service.getClass() );
            }
            return sn.add(service);
        }
        return false;
    }

    protected ServiceNode add( Class<?> cl ){
        if( cl != null && Service.class.isAssignableFrom(cl) ){
            ServiceNode sn = root.locate(cl);
            if( sn == null ){
                ServiceNode pSn;
                sn = new ServiceNode(cl);
                if( cl.getSuperclass() != null ){
                    pSn = add( cl.getSuperclass() );
                    if( pSn != null ){
                        pSn.add(sn);
                    }
                }
                Class<?>[] interfaces = cl.getInterfaces();
                for (Class<?> intf : interfaces) {
                    if (intf != null) {
                        pSn = add(intf);
                        if( pSn != null ){
                            pSn.add(sn);
                        }
                    }
                }
            }
            return sn;
        }
        return null;
    }

    /**
     * Registers a service class in the service hierarchy infra-structure
     * @param cl Service class to be registered
     * @return A service <i>instance</i> if the class of service was registered, <i>null</i> otherwise
     */
    public Service register( Class<?> cl ){
        ServiceNode sn = add(cl);
        if( sn != null ){
           return sn.defaultInstance();
        }
        return null;
    }

    /**
     * Set of services of type <i>service</i>, available for a given object <i>owner</i>
     * @param service Type of service that will be returned
     * @param owner Object that owns or can use the services to be returned
     * @return Set of services of type <i>service</i>, available for a given object <i>owner</i>
     */
    public Service[] available_services( Class<?> service, Object owner ){
        ServiceNode  sn = root.locate(service);
        if( sn != null ){
            return sn.available_services(owner);
        }
        return new Service[0];
    }

    /**
     * Set of services of type <i>service</i>, available for a given object <i>owner</i>
     * @param service Type of service that will be returned
     * @return Set of services of type <i>service</i>, available for a given object <i>owner</i>
     */
    public Class<?>[] available_service_classes( Class<?> service ){
        ServiceNode  sn = root.locate(service);
        if( sn != null ){
            return sn.serviceClases();
        }
        return new Class[0];
    }


    /**
     * Set of services of type <i>service</i> owned by a given object <i>owner</i>
     * @param service Type of service that will be returned
     * @param owner Object that owns the services to be returned
     * @return Set of services of type <i>service</i> owned by the given object <i>owner</i>
     */
    public Service[] owned_services( Class<?> service, Object owner ){
        ServiceNode  sn = root.locate(service);
        if( sn != null ){
            return sn.owned_services(owner);
        }
        return new Service[0];
    }

    /**
     * Gets the default service for the given service class and owner object.
     * The default service is the one used by the <i>owner</i> object when the
     * <i>service</i> is required by the <i>owner</i> object
     * @param service Service type used by the owner
     * @param owner Object that owns the service
     * @return The service that is defined as default service by the given owner object
     */
    public Service default_service( Class<?> service, Object owner ){
        ServiceNode  sn = root.locate(service);
        if( sn != null ){
            return sn.defaultService(owner);
        }
        return null;
    }

    /**
     * Sets the default service for the given service class and owner object.
     * The <i>def_service</i> is used by the <i>owner</i> object when the 
     * <i>service</i> is required by the <i>owner</i> object
     * @param service Class of service the owner object will define as default
     * @param owner Object that owns the service
     * @param def_service Default service used by the owner object when the service is required
     * @return Previously defined default service
     */
    public Service setDefault_service( Class<?> service, Object owner, Service def_service ){
        ServiceNode  sn = root.locate(service);
        if( sn != null ){
            return sn.setDefaultService(owner, def_service);
        }
        return null;
    }
    
    /**
     * Removes a service associated to a given object 
     * @param service_class Type of service to be removed
     * @param owner Object from which the service will be removed
     * @param service Service to be removed
     * @return  <i>true</i> if the service was removed from the object owning it, <i>false</i> 
     *  the service cannot removed from the object 
     */
    public boolean remove_owned_service( Class<?> service_class, Object owner, 
                                         Service service ){
        ServiceNode  sn = root.locate(service_class);
        if( sn != null ){
            return sn.remove_owned_service(owner, service);
        }        
        return false;
    }    
    
    
}