package com.computepatterns.apppatterns.osgi.servicelistener.impl;

import com.computepatterns.apppatterns.osgi.servicelistener.LogService;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Error logging service - Sample LogService to demonstrate Service Listener pattern.
 */
@Component(label = "Compute Patterns - Error log service",
        description = "Sample service to log the error messages. Purpose of this service is to demonstrate OSGi service listener pattern.",
        metatype = true
)
@Service
public class ErrorLogServiceImpl implements LogService {
    private static final Logger log = LoggerFactory.getLogger(ErrorLogServiceImpl.class);

    @Override
    public void log(String msg) {
        log.info("ErrorLogServiceImpl - {}", msg);
    }
}
