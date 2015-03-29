package com.computepatterns.apppatterns.osgi.servicelistener;

/**
 * Sample service for logging.
 */
public interface LogService {
    /**
     * Log the given message
     * @param msg Message
     */
    void log(String msg);
}
