package com.computepatterns.apppatterns.osgi.servicelistener.impl;

import com.computepatterns.apppatterns.osgi.servicelistener.LogService;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Request logging service - Sample LogService to demonstrate Service Listener pattern.
 */
@Component(label = "Compute Patterns - Request log service",
        description = "Sample service to log the request messages. Purpose of this service is to demonstrate OSGi service listener pattern.",
        metatype = true
)
@Service
public class RequestLogServiceImpl implements LogService {
    private static final Logger log = LoggerFactory.getLogger(RequestLogServiceImpl.class);

    @Override
    public void log(String msg) {
        log.info("RequestLogServiceImpl - {}", msg);

    }
}
