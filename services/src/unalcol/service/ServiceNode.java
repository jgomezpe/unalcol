package unalcol.service;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * <p>A node in the Service Hierarchy infra-structure.</p>
 * <p>Copyright: Copyright (c) 2015</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class ServiceNode {
    /**
     * The class of services this node maintains
     */
    protected Class<?> service_class = null;

    /**
     * Instances of the service class maintained by this node
     */
    protected ArrayList<Service> instances = new ArrayList<Service>();

    /**
     * The collection of services sub-classes (specialized services) that has been
     * defined for the service class maintained by the node
     */
    protected ArrayList<ServiceNode> sub_classes = new ArrayList<ServiceNode>();

    /**
     * The collection of super classes the class maintained by this node is defining
     */
    protected ArrayList<ServiceNode> super_classes = new ArrayList<ServiceNode>();

    /**
     * Currently used service for the class this node is maintaining
     */
    protected Hashtable<Object, Service> default_service = new Hashtable<Object, Service>();

    /**
     * The type of service the node is maintaining
     * @param service Service type
     */
    public ServiceNode( Class<?> service ){
        this.service_class = service;
        try{
            Service instance = (Service)service.newInstance();
            this.instances.add(instance);
        }catch( InstantiationException | IllegalAccessException e ){
        }
    }

    /**
     * Adds a service instance to the node
     * @param s Service instance to add
     * @return <i>true</i> if the service can be added to the node, <i>false</i> otherwise
     */
    public boolean add( Service s ){
        return instances.add( s );
    }

    /**
     * Adds a node service to the node
     * @param s_node Service node to add
     * @return <i>true</i> if the service node can be added to the node, <i>false</i> otherwise
     */
    public boolean add( ServiceNode s_node ){
        s_node.super_classes.add(this);
        return sub_classes.add( s_node );
    }

    /**
     * Gets the node that maintains the type of service <i>service</i> if any
     * @param service Type of service to locate
     * @return The service node that maintains the type of service <i>service</i> if any, 
     * <i>null</i> if there is not such service node.
     */
    public ServiceNode locate( Class<?> service ){
        if( this.service_class != service ){
            int k=0;
            while( k<sub_classes.size() &&
                   !sub_classes.get(k).service_class.isAssignableFrom(service) ){
                k++;
            }
            if( k < sub_classes.size() ){
                return sub_classes.get(k).locate( service );
            }else{
                return null;
            }
        }else{
            return this;
        }
    }

    /**
     * Determines if the object type <i>owner</i> can use the service <i>service</i>, traversing the 
     * class hierarchy (checking the parent classes of <i>owner</i>) if necessary.
     * @param service Service to be analyzed
     * @param owner Possible type that is owner of the service
     * @return <i>true</i> if the service <i>service</i> can be used by the object type <i>owner</i>
     * <i>false</i> otherwise.
     */
    public boolean canUse( Service service, Class<?> owner ){
        return owner != null &&
               ( service.owner() == owner ||
                 canUse( service, owner.getSuperclass() ) );
    }

    /**
     * Determines if the object <i>owner</i> can use the service <i>service</i>, traversing the 
     * class hierarchy (checking the parent classes of <i>owner</i>) if necessary.
     * @param service Service to be analyzed
     * @param owner Possible owner of the service
     * @return <i>true</i> if the service <i>service</i> can be used by the object <i>owner</i>
     * <i>false</i> otherwise.
     */
    public boolean canUse( Service service, Object owner ){
        return owner != null &&
               ( service.owner() == owner ||
                 canUse( service, owner.getClass() ) );
    }

    /**
     * Builds a list <i>collection</i> of services that are available for an object 
     * (services that are owned or can be used by object <i>owner</i>) 
     * @param owner Object to be analyzed
     * @param collection list <i>collection</i> of services that are available for the object <i>owner</i>
     */
    protected void available_services( Object owner, ArrayList<Service> collection ){
        for (Service instance : instances) {
            if (canUse(instance, owner)) {
                collection.add(instance);
            }
        }
        for (ServiceNode sub_classe : sub_classes) {
            sub_classe.available_services(owner, collection);
        }
    }

    /**
     * Converts a list of services to an array of services 
     * @param col List of services
     * @return Array of services
     */
    protected Service[] convert( ArrayList<Service> col ){
        Service[] scol = new Service[col.size()];
        for( int i=0; i<col.size(); i++ ){
            scol[i] = col.get(i);
        }
        col.clear();
        return scol;
    }

    /**
     * Builds an array of services that are available for an object 
     * (services that are owned or can be used by object <i>owner</i>) 
     * @param owner Object to be analized
     * @return Array of services that are available for the object <i>owner</i>
     */
    public Service[] available_services( Object owner ){
        ArrayList<Service> col = new ArrayList<Service>();
        available_services(owner, col);
        return convert(col);
    }

    protected void owned_services( Object owner, ArrayList<Service> collection ){
        for (Service instance : instances) {
            if (instance.owner() == owner) {
                collection.add(instance);
            }
        }
        for (ServiceNode sub_classe : sub_classes) {
            sub_classe.owned_services(owner, collection);
        }
    }

    public Service[] owned_services( Object owner ){
        ArrayList<Service> col = new ArrayList<Service>();
        owned_services(owner, col);
        return convert(col);
    }

    public boolean remove_owned_service( Object owner, Service service ){
        boolean flag = false;
        int i=0;
        while( i<instances.size() ){
            if( instances.get(i).owner() == owner && instances.get(i) == service ){
                instances.remove(i);                
                flag = true;
            }else{
                i++;
            }
        }
        for( i=0; i<sub_classes.size(); i++ ){
           flag |= sub_classes.get(i).remove_owned_service(owner, service);
        }
        return flag;
    }


    public Service defaultService( Object owner ){
        Service s = null;
        if( owner != null ){
            s = default_service.get(owner);
            if( s == null ){
                if( Class.class.isInstance(owner) ){
                    Class<?> own = (Class<?>)owner;
                    s = defaultService(own.getSuperclass());
                    int i=0;
                    Class<?>[] super_interfaces = own.getInterfaces();
                    while( i<super_interfaces.length && s == null ){
                        s = defaultService(super_interfaces[i]);
                        i++;
                    }
                }else{
                    s = defaultService(owner.getClass());
                }
            }
        }
        return s;
    }

    public Service setDefaultService( Object owner, Service service ){
        Service old = default_service.put(owner, service);
        for (ServiceNode super_classe : super_classes) {
            super_classe.setDefaultService(owner, service);
        }
        return old;
    }

    protected void serviceClasses( ArrayList<Class<?>> classes ){
        classes.add(service_class);
        for (ServiceNode sub_classe : sub_classes) {
            sub_classe.serviceClasses(classes);
        }
    }

    public Class<?>[] serviceClases(){
        ArrayList<Class<?>> classes = new ArrayList<Class<?>>();
        this.serviceClasses(classes);
        Class<?>[] array = new Class[classes.size()];
        for( int i=0; i<classes.size(); i++){
            array[i] = classes.get(i);
        }
        return array;
    }
    
    public Service defaultInstance(){
        if( instances.size() > 0 ) return instances.get(0);
        return null;
    }
}