package com.computepatterns.apppatterns.servlets;

import com.day.cq.dam.api.DamConstants;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.OptingServlet;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * Custom asset-download servlet. Overrides the existing asset download servlet of AEM {@link com.day.cq.dam.core.impl.servlet.AssetDownloadServlet}.
 * This is for demonstrating how OOTB servlet in AEM can be overriden and hence asset download specific implementation not supplied.
 * OOTB {@link com.day.cq.dam.core.impl.servlet.AssetDownloadServlet}'s OSGi properties have been copied down to this custom servlet with the
 * resource type pointed to dam:Asset to get preference over the OOTB servlet in the servlet resolution process implemented in {@link org.apache.sling.servlets.resolver.internal.SlingServletResolver}.
 * This servlet accepts download requests for single asset. In case of multiple asset download, it doesn't accept the request.
 *
 * More on this, please visit {@link ComputePatterns.com http://www.computepatterns.com/153/overriding-out-of-the-box-servlet-in-aem-sling/}
 */
@Component(metatype = false)
@Service
@Properties(value = {
        @Property(name = "sling.servlet.resourceTypes", value = DamConstants.NT_DAM_ASSET),
        @Property(name = "sling.servlet.methods", value = {"GET", "POST"}),
        @Property(name = "sling.servlet.selectors", value = "assetdownload")
})
public class CustomAssetDownloadServlet extends SlingAllMethodsServlet implements OptingServlet {
    private static final Logger log = LoggerFactory.getLogger(CustomAssetDownloadServlet.class);

    protected void doGet(SlingHttpServletRequest request,
                         SlingHttpServletResponse response)
            throws ServletException, IOException {
        log.info("Custom download servlet being invoked. Path(s) requested for asset download - {}", request.getResource().getPath());
    }

    /**
     * Examines the request, and return <code>true</code> if this servlet is
     * willing to handle the request. If <code>false</code> is returned, the
     * request will be ignored by this servlet, and may be handled by other
     * servlets.
     *
     * @param request The request to examine
     * @return <code>true</code> if this servlet will handle the request,
     * <code>false</code> otherwise
     */
    @Override
    public boolean accepts(SlingHttpServletRequest request) {
        if (null != request.getRequestParameter("path")) { //multiple asset download case
            log.info("Custom download servlet being invoked. Multiple assets chosen and hence not accepting.");
            return false;
        }
        return true;
    }
}