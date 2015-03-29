package com.computepatterns.apppatterns.osgi.servicelistener;

/**
 * Sample service interface for listening OSGi services implementing LogService.
 */
public interface LogServiceListener {
    /**
     * Invoke all the registered log services.
     */
    void invokeLogServices();
}
