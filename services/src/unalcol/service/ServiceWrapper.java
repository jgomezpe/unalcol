package unalcol.service;

/**
 * <p>A Service Wrapper for existing or defined class methods. If a
 * class has defined a method that can be used as service, then such method can
 * be wrapped as a Service for the Service infra-structure</p>
 *
 * @author Jonatan Gomez Perdomo
 * @version 1.0
 */
public class ServiceWrapper extends Service {
    protected String method_name;

    /**
     * Creates a wrapped service for a given method name
     * @param method_name The method that will be wrapped as service
     */
    public ServiceWrapper(String method_name) {
        this.method_name = method_name;
    }

    /**
     * Returns Object class as owner of the service (can be used for any object that
     * contains a method matching this method name
     * @return Object class 
     */
    @Override
    public Object owner() {
        return Object.class;
    }
}