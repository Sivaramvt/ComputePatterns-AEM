package com.computepatterns.apppatterns.servlets;

import com.computepatterns.apppatterns.osgi.servicelistener.LogServiceListener;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Created by sivt on 28/03/15.
 */
@SlingServlet(
        label = "Samples - Sling Safe Methods Servlet",
        description = "Sample implementation of a Sling All Methods Servlet.",
        paths = {"/bin/test/osgi/logservicelistener"},
        metatype = true
)
public class TestErrorLogService extends SlingAllMethodsServlet {

    @Reference
    private LogServiceListener logServiceListener;

    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        logServiceListener.invokeLogServices();
        response.getOutputStream().print("Request execute. Check the logs.");

    }
}
