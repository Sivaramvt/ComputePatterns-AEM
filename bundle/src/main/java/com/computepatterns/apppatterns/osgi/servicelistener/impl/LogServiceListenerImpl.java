package com.computepatterns.apppatterns.osgi.servicelistener.impl;

import com.computepatterns.apppatterns.osgi.servicelistener.LogService;
import com.computepatterns.apppatterns.osgi.servicelistener.LogServiceListener;
import org.apache.felix.scr.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Service Listener pattern  implementation - Sample implementation for demonstrating Service listener pattern.
 * This service listens for OSGi services which implement LogService interface.
 * When a LogService is available, Service Component Runtime (SCR) calls the bind method and when it goes unavailable,
 * unbind method will be called.
 */
@Component(label = "Compute Patterns - Log Service Listener",
        description = "Sample service listener pattern demonstration.")
@Reference(name = LogServiceListenerImpl.METHOD_NAME_TO_BIND,
        referenceInterface = LogService.class,
        cardinality = ReferenceCardinality.OPTIONAL_MULTIPLE,
        policy = ReferencePolicy.DYNAMIC
)
@Service
public class LogServiceListenerImpl implements LogServiceListener {
    private static final Logger log = LoggerFactory.getLogger(LogServiceListenerImpl.class);

    /**
     * Name of the local method to be bound when a service is available in Service Component Runtime.
     */
    protected static final String METHOD_NAME_TO_BIND = "logService";

    /**
     * Thread safe hash map to contain the registered LogService references.
     */
    private static ConcurrentHashMap<String, LogService> serviceMap = new ConcurrentHashMap<>();

    @Override
    public void invokeLogServices() {
        for (Map.Entry<String, LogService> entry : serviceMap.entrySet()) {
            entry.getKey();
            entry.getValue().log("Hello from LogService Listener.");
        }
    }

    @Activate
    protected void activate(final Map<String, String> config) {
        log.info("LogServiceListenerImpl - ACTIVATED");
    }

    @Deactivate
    protected void deactivate(Map<String, String> config) {
        log.info("LogServiceListenerImpl - DEACTIVATED");
    }

    private void bindLogService(LogService logService, Map<Object, Object> props) {
        serviceMap.put(logService.getClass().toString(), logService);
        log.debug("Service bound - {}", logService.getClass().toString());
        log.debug("Total number of services in registry - {}", serviceMap.size());
    }

    private void unbindLogService(LogService logService, Map<Object, Object> props) {
        if (serviceMap.containsKey(logService.getClass().toString())) {
            serviceMap.remove(logService.getClass().toString());
            log.debug("Service unbound - {}", logService.getClass().toString());
            log.debug("Total number of services in registry - {}", serviceMap.size());
        }
    }
}
